package ni.edu.uam.odge.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.Required;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "materiales")
@Getter
@Setter
public class Material {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String oid;

    @Column(length = 120, nullable = false)
    @Required(message = "El nombre del material no puede quedar vacío.")
    private String nombre;

    @Column(length = 80)
    private String categoria;
    // Ejemplos: "Anestesia", "Descartables", "Resinas", "Guantes", etc.

    @Min(value = 0, message = "El stock no puede ser negativo.")
    @Column(nullable = false)
    private Integer stockActual = 0;

    @Min(value = 0, message = "El stock mínimo no puede ser negativo.")
    @Column(nullable = false)
    private Integer stockMinimo = 0;

    @Column(columnDefinition = "text")
    private String notas;

    @PrePersist
    @PreUpdate
    private void validarStock() {
        if (stockActual < 0) {
            throw new IllegalArgumentException("El stock actual no puede ser negativo.");
        }
        if (stockMinimo < 0) {
            throw new IllegalArgumentException("El stock mínimo no puede ser negativo.");
        }
    }
}