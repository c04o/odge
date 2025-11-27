package ni.edu.uam.odge.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.Required;

import javax.persistence.*;
import java.time.LocalDate;

@Entity // Indica que esta clase es una entidad JPA (tabla "pacientes")
@Table(name = "pacientes")
@Getter
@Setter
public class Paciente {

    @Id
    @GeneratedValue(generator = "system-uuid") // Genera un UUID para el paciente
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String oid;

    @Column(name = "nombre_completo", length = 120, nullable = false)
    @Required(message = "El nombre no puede quedar vacío") // Validación de OpenXava
    private String nombre; // Nombre completo del paciente

    @Column(name = "identificacion", length = 30, unique = true, nullable = true)
    private String identificacion;
    // Puede ser cédula, pasaporte, etc. (opcional pero único si se registra)

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento; // Fecha en formato YYYY-MM-DD

    @Column(length = 30)
    private String telefono; // Teléfono opcional

    @Column(length = 100)
    private String email; // Correo opcional

    @Column(columnDefinition = "text")
    private String notas; // Notas largas sobre el paciente

    @PrePersist
    @PreUpdate
    private void validarFechaNacimiento() {
        if (this.fechaNacimiento != null) {
            // No permitir fechas de nacimiento en el futuro
            if (this.fechaNacimiento.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha de nacimiento no puede ser mayor a la fecha actual.");
            }
        }
    }
}
