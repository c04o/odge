package ni.edu.uam.odge.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Required;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "cita_materiales")
@Getter
@Setter
public class MaterialCita {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    @Hidden
    private String oid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cita_oid")
    @Required
    private Cita cita;

    @ManyToOne(optional = false)
    @JoinColumn(name = "material_oid")
    @Required
    private Material material;

    @Min(value = 1, message = "La cantidad debe ser al menos 1.")
    @Column(nullable = false)
    private Integer quantity = 1;

    @Column(columnDefinition = "text")
    private String notes;

    @PrePersist
    @PreUpdate
    private void validate() {
        if (this.quantity == null || this.quantity < 1) {
            throw new IllegalArgumentException("La cantidad de material debe ser mayor o igual a 1.");
        }
    }
}