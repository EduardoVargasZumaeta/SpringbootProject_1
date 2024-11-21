package SpringProyect_1.Service;

import SpringProyect_1.Model.Usuario;
import SpringProyect_1.Repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioServices {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public List<Usuario> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Usuario> get(Integer id) {
        return Optional.empty();
    }

}
