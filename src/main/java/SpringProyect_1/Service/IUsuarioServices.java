package SpringProyect_1.Service;

import SpringProyect_1.Model.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioServices {
    public List<Usuario> getUserList();
    public Optional<Usuario> get(Integer id);
}
