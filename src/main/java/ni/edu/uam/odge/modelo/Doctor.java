package ni.edu.uam.odge.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.Required;
import org.openxava.annotations.Stereotype;

import javax.persistence.*;
import java.util.regex.Pattern;

@Entity // Indica que esta clase es una entidad JPA (tabla "doctores")
@Table(name = "doctores")
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(generator = "system-uuid") // Genera un UUID como identificador
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String oid;

    @Column(name = "nombre_completo", length = 120, nullable = false)
    @Required(message = "El nombre del odontólogo no puede quedar vacío.") // validación UI/Modelo
    private String nombre;

    @Column(length = 30, unique = true, nullable = false)
    @Required(message = "El código del odontólogo no puede quedar vacío.")
    private String codigo; // Código interno único por odontólogo

    @Column(length = 60)
    private String especialidad; // Especialidad dental (opcional)

    @Column(name = "disponibilidad", length = 200)
    private String disponibilidad;
    // Ejemplo: "L-V 08:00-16:00" ? horario base del odontólogo

    @Stereotype("MEMO")
    @Column(length=500)
    private String notas;

    // Expresión regular para validar el formato del horario
    // Acepta: "L-V 08:00-16:00", "Lunes 08:00-12:00", "Lunes-Viernes 08:00-16:00", etc.
    private static final Pattern DISPONIBILITY_PATTERN = Pattern.compile(
            "^[A-Za-zÀ-ÿñÑüÜ\\s\\-]+\\s+\\d{2}:\\d{2}-\\d{2}:\\d{2}$"
    );

    @PrePersist
    @PreUpdate
    private void validarDisponibilidad() {
        // Si está vacío, no valida (el campo es opcional)
        if (this.disponibilidad == null || this.disponibilidad.trim().isEmpty()) return;

        String text = this.disponibilidad.trim();

        // Verifica que cumpla el formato basado en la expresión regular
        if (!DISPONIBILITY_PATTERN.matcher(text).matches()) {
            throw new IllegalArgumentException(
                    "Formato de disponibilidad inválido. Ejemplos válidos: 'L-V 08:00-16:00' o 'Lunes 08:00-12:00'."
            );
        }

        // Divide en dos partes: (1) texto de días y (2) rango de horas
        String[] parts = text.split("\\s+", 2);
        if (parts.length == 2) {
            String horas = parts[1]; // Ej: "08:00-16:00"
            String[] hh = horas.split("-"); // Separa inicio-fin
            if (hh.length == 2) {
                try {
                    // Convierte horas a minutos para comparar correctamente
                    int hi = Integer.parseInt(hh[0].trim().substring(0, 2)) * 60
                            + Integer.parseInt(hh[0].trim().substring(3, 5));

                    int hf = Integer.parseInt(hh[1].trim().substring(0, 2)) * 60
                            + Integer.parseInt(hh[1].trim().substring(3, 5));

                    // Verifica que la hora inicial sea menor a la final
                    if (hi >= hf) {
                        throw new IllegalArgumentException(
                                "La hora de inicio debe ser anterior a la hora de fin en la disponibilidad."
                        );
                    }
                } catch (Exception e) {
                    // Si algo falla al parsear las horas
                    throw new IllegalArgumentException(
                            "Error al validar las horas en la disponibilidad. Usa formato HH:MM-HH:MM."
                    );
                }
            }
        }
    }
}
