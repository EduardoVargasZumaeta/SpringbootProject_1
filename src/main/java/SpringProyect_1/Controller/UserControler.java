package SpringProyect_1.Controller;

import SpringProyect_1.Model.Usuario;
import SpringProyect_1.Service.UsuarioService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class UserControler {

    @Autowired
    private UsuarioService userService;

    @GetMapping("/listUsers")
    public List<Usuario> getUserList() {
        return userService.listAllUsers();
    }

    @GetMapping("/lista")
    public String listUser(Model model) {
        List<Usuario> usuarios = userService.listAllUsers();
        model.addAttribute("usuario", usuarios);
        return "index";
    }

    @GetMapping("/nuevo")
    public String newUser(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "formUsuario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Usuario usuario) {
        try {
            userService.savedUser(usuario);
        } catch (Exception e) {
            System.out.println("******" + e.getMessage());
            return "formUsuario";
        }
        return "redirect:/lista";
    }

    @GetMapping("/listarJSON")
    @ResponseBody
    public List<Usuario> listarJSON(){
        return userService.listAllUsers();
    }

    @GetMapping("/exportExcel")
    public ResponseEntity<InputStreamResource> exportExcel() throws IOException {
        ByteArrayInputStream flujo = userService.exportExcel();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.xls");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(flujo));
    }

}
