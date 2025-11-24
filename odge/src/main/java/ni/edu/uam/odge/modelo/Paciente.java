package ni.edu.uam.odge.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.Required;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
public class Paciente {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String oid;

    @Column(name = "nombre_completo", length = 120, nullable = false)
    @Required(message = "El nombre no puede quedar vacío")
    private String nombre;

    @Column(name = "identificacion", length = 30, unique = true, nullable = true)
    private String identificacion;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento; // formato YYYY-MM-DD

    @Column(length = 30)
    private String telefono;

    @Column(length = 100)
    private String email;

    @Column(columnDefinition = "text")
    private String notas;

    @PrePersist
    @PreUpdate
    private void validarFechaNacimiento() {
        if (this.fechaNacimiento != null) {
            if (this.fechaNacimiento.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha de nacimiento no puede ser mayor a la fecha actual.");
            }
        }
    }
}
