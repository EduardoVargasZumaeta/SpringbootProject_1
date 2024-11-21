package SpringProyect_1.Controller;

import SpringProyect_1.Model.Usuario;
import SpringProyect_1.Service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserControler {

    @Autowired
    private UsuarioService userService;

    @GetMapping("/ListUsers")
    public List<Usuario> getUserList() {
        return userService.getUserList();
    }

}
