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

@Entity
@Table(name = "citas",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_DOCTOR_DATETIME", columnNames = {"doctor_oid", "appointment_datetime"}),
                @UniqueConstraint(name = "UK_PATIENT_DATETIME", columnNames = {"paciente_oid", "appointment_datetime"})
        })
@Getter
@Setter
public class Cita {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    @Hidden
    private String oid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_oid")
    @Required
    private Paciente paciente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_oid")
    @Required
    private Doctor doctor;

    @Column(name = "appointment_datetime", nullable = false)
    @Required
    private LocalDateTime appointmentDateTime;

    @Column(length = 20, nullable = false)
    @Required
    private String estado; // pendiente, confirmada, atendida, cancelada

    @Column(columnDefinition = "text")
    private String observaciones;

    @OneToMany(mappedBy = "cita", cascade = CascadeType.ALL, orphanRemoval = true)
    @ListProperties("material.nombre, quantity")
    private List<MaterialCita> materiales;

    @PrePersist
    @PreUpdate
    private void normalizarFechaHora() {
        if (this.appointmentDateTime != null) {
            this.appointmentDateTime = this.appointmentDateTime.truncatedTo(ChronoUnit.HOURS);
        }
    }
}