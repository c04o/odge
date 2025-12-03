package ni.edu.uam.odge.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Required;
import org.openxava.annotations.Stereotype;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity // Entidad que representa la relación Cita ? Material
@Table(name = "cita_materiales")
@Getter
@Setter
public class MaterialCita {

    @Id
    @GeneratedValue(generator = "system-uuid") // Genera UUID como clave primaria
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    @Hidden // Oculto en la UI
    private String oid;

    @ManyToOne(optional = false) // Muchas filas pueden pertenecer a una misma cita
    @JoinColumn(name = "cita_oid")
    @Required
    private Cita cita; // Cita asociada

    @ManyToOne(optional = false) // Muchas filas pueden usar el mismo material
    @JoinColumn(name = "material_oid")
    @Required
    private Material material; // Material utilizado en la cita

    @Min(value = 1, message = "La cantidad debe ser al menos 1.") // Validación a nivel de bean
    @Column(nullable = false)
    private Integer quantity = 1; // Cantidad de material consumido

    @Stereotype("MEMO")
    @Column(length=500)
    private String notas;


    @PrePersist
    @PreUpdate
    private void validate() {
        // Validación extra antes de guardar/actualizar
        if (this.quantity == null || this.quantity < 1) {
            throw new IllegalArgumentException("La cantidad de material debe ser mayor o igual a 1.");
        }
    }
}
