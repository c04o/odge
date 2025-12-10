package ni.edu.uam.odge.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.Required;
import org.openxava.annotations.Stereotype;

import javax.persistence.*;

@Entity
@Table(name = "personal_limpieza")
@Getter
@Setter
public class PersonalLimpieza
{

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String oid;

    @Required(message = "El nombre del personal de limpieza no puede quedar vacío.")
    @Column(length = 120, nullable = false)
    private String nombre;

    // NUEVO: identificación propia del personal de limpieza
    @Column(length = 30, unique = true)
    private String identificacion;   // Ej. código interno, cédula, etc.

    @Column(length = 30)
    private String telefono;

    @Column(length = 100)
    private String correo;

    @Column(length = 80)
    private String turno;

    @Stereotype("MEMO")
    @Column(length = 500)
    private String notas;
}

