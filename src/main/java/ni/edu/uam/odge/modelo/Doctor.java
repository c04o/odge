package ni.edu.uam.odge.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.Required;

import javax.persistence.*;
import java.util.regex.Pattern;

@Entity
@Table(name = "doctores")
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String oid;

    @Column(name = "nombre_completo", length = 120, nullable = false)
    @Required(message = "El nombre del odontólogo no puede quedar vacío.")
    private String nombre;

    @Column(length = 30, unique = true, nullable = false)
    @Required(message = "El código del odontólogo no puede quedar vacío.")
    private String codigo; // código interno

    @Column(length = 60)
    private String especialidad;

    @Column(name = "disponibilidad", length = 200)
    private String disponibilidad; // texto simple con horario base (p. ej. "L-V 08:00-16:00")

    @Column(columnDefinition = "text")
    private String notas;

    // Patrón para formatos tipo:
    // "L-V 08:00-16:00"  o  "Lunes-Viernes 08:00-16:00"  o "Lunes 08:00-12:00"
    private static final Pattern DISPONIBILITY_PATTERN = Pattern.compile(
            "^[A-Za-zÀ-ÿñÑüÜ\\s\\-]+\\s+\\d{2}:\\d{2}-\\d{2}:\\d{2}$"
    );

    @PrePersist
    @PreUpdate
    private void validarDisponibilidad() {
        if (this.disponibilidad == null || this.disponibilidad.trim().isEmpty()) return;

        String text = this.disponibilidad.trim();
        if (!DISPONIBILITY_PATTERN.matcher(text).matches()) {
            throw new IllegalArgumentException("Formato de disponibilidad inválido. Ejemplos válidos: 'L-V 08:00-16:00' o 'Lunes 08:00-12:00'.");
        }
        String[] parts = text.split("\\s+", 2);
        if (parts.length == 2) {
            String horas = parts[1];
            String[] hh = horas.split("-");
            if (hh.length == 2) {
                try {
                    int hi = Integer.parseInt(hh[0].trim().substring(0, 2)) * 60
                            + Integer.parseInt(hh[0].trim().substring(3, 5));
                    int hf = Integer.parseInt(hh[1].trim().substring(0, 2)) * 60
                            + Integer.parseInt(hh[1].trim().substring(3, 5));
                    if (hi >= hf) {
                        throw new IllegalArgumentException("La hora de inicio debe ser anterior a la hora de fin en la disponibilidad.");
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("Error al validar las horas en la disponibilidad. Usa formato HH:MM-HH:MM.");
                }
            }
        }
    }
}