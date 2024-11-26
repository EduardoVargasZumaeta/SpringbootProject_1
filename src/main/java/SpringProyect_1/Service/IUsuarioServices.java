package SpringProyect_1.Service;

import SpringProyect_1.Model.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioServices {
    public List<Usuario> listAllUsers();
    public Optional<Usuario> getUserById(Long id);
    public String savedUser(Usuario usuario);
    public void  deleteUser(Long id);
}