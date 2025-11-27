package ni.edu.uam.odge.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.Required;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity // Marca esta clase como entidad JPA (tabla en la base de datos)
@Table(name = "citas",
        uniqueConstraints = {
                // Evita que un doctor tenga dos citas a la misma hora
                @UniqueConstraint(name = "UK_DOCTOR_DATETIME", columnNames = {"doctor_oid", "appointment_datetime"}),
                // Evita que un paciente tenga dos citas a la misma hora
                @UniqueConstraint(name = "UK_PATIENT_DATETIME", columnNames = {"paciente_oid", "appointment_datetime"})
        })
@Getter
@Setter
public class Cita {

    @Id
    @GeneratedValue(generator = "system-uuid") // Genera UUID como ID
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    @Hidden // Oculta el campo en la UI
    private String oid;

    @ManyToOne(optional = false) // Muchas citas pueden pertenecer a un paciente
    @JoinColumn(name = "paciente_oid")
    @Required // Campo obligatorio en OpenXava
    private Paciente paciente;

    @ManyToOne(optional = false) // Muchas citas pueden ser atendidas por un doctor
    @JoinColumn(name = "doctor_oid")
    @Required // Obligatorio
    private Doctor doctor;

    @Column(name = "appointment_datetime", nullable = false)
    @Required // Fecha/hora obligatoria
    private LocalDateTime appointmentDateTime;

    @Column(length = 20, nullable = false)
    @Required
    private String estado; // Estado de la cita (pendiente, confirmada, etc.)

    @Column(columnDefinition = "text") // Campo largo para texto libre
    private String observaciones;

    @OneToMany(mappedBy = "cita", cascade = CascadeType.ALL, orphanRemoval = true)
    @ListProperties("material.nombre, quantity") // Qué mostrar en la lista de materiales
    private List<MaterialCita> materiales;

    @PrePersist
    @PreUpdate
    private void normalizarFechaHora() {
        // Redondea la fecha a la hora exacta (quita minutos y segundos)
        if (this.appointmentDateTime != null) {
            this.appointmentDateTime = this.appointmentDateTime.truncatedTo(ChronoUnit.HOURS);
        }
    }
}
