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
    private Long codigo;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres.")
    private String nombre;

    @Column(nullable = false, unique = true, length = 15)
    @NotBlank(message = "El teléfono es obligatorio.")
    @Size(max = 9, message = "El teléfono no puede tener más de 15 caracteres.")
    private String telefono;

    @Column(nullable = false, unique = true, length = 100)
    @NotBlank(message = "El correo es obligatorio.")
    @Email(message = "El correo debe tener un formato válido.")
    @Size(max = 100, message = "El correo no puede tener más de 100 caracteres.")
    private String correo;
}

