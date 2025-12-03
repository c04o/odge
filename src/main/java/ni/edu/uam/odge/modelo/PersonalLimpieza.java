package ni.edu.uam.odge.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.Required;
import org.openxava.annotations.Stereotype;

import javax.persistence.*;

@Entity // Indica que esta clase es una tabla en la base de datos
@Table(name = "personal_limpieza")
@Getter
@Setter
public class PersonalLimpieza {

    @Id
    @GeneratedValue(generator = "system-uuid") // Genera un UUID único para el registro
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String oid; // Identificador principal

    @Required(message = "El nombre del personal de limpieza no puede quedar vacío.") // Validación en OpenXava
    @Column(length = 120, nullable = false)
    private String nombre; // Nombre completo del personal

    @Column(length = 30)
    private String telefono; // Número de contacto (opcional)

    @Column(length = 100)
    private String correo; // Correo electrónico (opcional)

    @Column(length = 80)
    private String turno; // Ej: "Mañana", "Tarde", "08:00-12:00"

    @Stereotype("MEMO")
    @Column(length=500)
    private String notas;

}
