package ni.edu.uam.odge.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.Required;

import javax.persistence.*;
import java.util.regex.Pattern;

@Entity
@Table(name = "personal_limpieza")
@Getter
@Setter
public class PersonalLimpieza {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy="uuid2")
    @Column(length = 36)
    private String oid;

    @Required(message = "El nombre del personal de limpieza no puede quedar vacío.")
    @Column(length = 120, nullable = false)
    private String nombre;

    @Column(length = 30)
    private String telefono;

    @Column(length = 100)
    private String correo;

    @Column(length = 80)
    private String turno; // "Mañana", "Tarde" o "HH:mm-HH:mm"

    @Column(columnDefinition = "text")
    private String notas;

    // -----------------------------
    // VALIDACIONES
    // -----------------------------
    @PrePersist
    @PreUpdate
    private void validarDatos() {

        // Validación de teléfono (solo dígitos, entre 8?15 caracteres)
        if (telefono != null && !telefono.trim().isEmpty()) {
            if (!telefono.matches("\\d{8,15}")) {
                throw new IllegalArgumentException("El número de teléfono debe contener solo dígitos (8 a 15).");
            }
        }

        // Validación de correo
        if (correo != null && !correo.trim().isEmpty()) {
            String regexCorreo = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!Pattern.matches(regexCorreo, correo)) {
                throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
            }
        }

        // Validación de turno
        if (turno != null && !turno.trim().isEmpty()) {

            // Acepta:
            //  "Mañana"
            //  "Tarde"
            //  "08:00-12:00"
            String regexTurno = "^(Mañana|Tarde|\\d{2}:\\d{2}-\\d{2}:\\d{2})$";

            if (!Pattern.matches(regexTurno, turno)) {
                throw new IllegalArgumentException(
                        "El turno debe ser 'Mañana', 'Tarde' o un horario con formato HH:mm-HH:mm (ejemplo: 08:00-12:00)."
                );
            }
        }
    }
}
