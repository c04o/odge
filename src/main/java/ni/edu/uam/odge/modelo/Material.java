package ni.edu.uam.odge.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.Required;
import org.openxava.annotations.Stereotype;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity // Indica que es una entidad JPA (tabla "materiales")
@Table(name = "materiales")
@Getter
@Setter
public class Material {

    @Id
    @GeneratedValue(generator = "system-uuid") // Genera un UUID como identificador
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String oid;

    @Column(length = 120, nullable = false)
    @Required(message = "El nombre del material no puede quedar vacío.") // Validación en la UI/modelo
    private String nombre;

    @Column(length = 80)
    private String categoria;
    // Ejemplos: "Anestesia", "Descartables", "Resinas", "Guantes"

    @Min(value = 0, message = "El stock no puede ser negativo.") // Validación a nivel de bean
    @Column(nullable = false)
    private Integer stockActual = 0; // Cantidad actual disponible

    @Min(value = 0, message = "El stock mínimo no puede ser negativo.")
    @Column(nullable = false)
    private Integer stockMinimo = 0; // Cantidad mínima recomendada

    @Stereotype("MEMO")
    @Column(length=500)
    private String notas;


    @PrePersist
    @PreUpdate
    private void validarStock() {
        // Validación extra por seguridad antes de guardar/actualizar
        if (stockActual < 0) {
            throw new IllegalArgumentException("El stock actual no puede ser negativo.");
        }
        if (stockMinimo < 0) {
            throw new IllegalArgumentException("El stock mínimo no puede ser negativo.");
        }
    }
}
