package SpringProyect_1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Size(max = 45)
    private String nombre;
    @Size(max = 45)
    private String telefono;
    @Email
    private String correo;


}
