package SpringProyect_1.Service;

import SpringProyect_1.Model.Usuario;
import SpringProyect_1.Repository.UsuarioRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioServices {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public List<Usuario> listAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de usuarios: " + e.getMessage());
        }
    }


    @Override
    public Optional<Usuario> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public String savedUser(Usuario usuario) {
        Usuario savedUser = userRepository.save(usuario);
        try {
            if (savedUser != null) {
                return "Usuario registrado con éxito: " + savedUser.getCodigo();
            } else {
                return "Error al registar usuario";
            }
        } catch (Exception e) {
            return "Ocurrió un error al guardar el usuario: " + e.getMessage();
        }
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Usuario con ID " + id + " no encontrado.");
        }
    }

    public ByteArrayInputStream exportExcel() throws IOException {
        Workbook workbook = new HSSFWorkbook(); // Para archivos .xls
        ByteArrayOutputStream flujo = new ByteArrayOutputStream();
        Sheet hoja = (Sheet) workbook.createSheet("Productos");

        // Titulo de la hoja
        Row filaTitulo = hoja.createRow(0);
        Cell celda = filaTitulo.createCell(0);
        celda.setCellValue("Lista de colaboradores");

        // Encabezados de columnas
        Row filaData = hoja.createRow(2);  // Cambié a la fila 1 porque la 0 es para el título
        String[] cols = {"Codigo", "Nombre", "Correo", "Fono"};
        for (int i = 0; i < cols.length; i++) {
            celda = filaData.createCell(i);
            celda.setCellValue(cols[i]);
        }

        // Datos de los colaboradores
        List<Usuario> lista = this.listAllUsers();
        int fila = 3;  // Comienza en la fila 2 porque la fila 0 es el título y la fila 1 son los encabezados
        for (Usuario user : lista) {
            filaData = hoja.createRow(fila);
            filaData.createCell(0).setCellValue(user.getCodigo());
            filaData.createCell(1).setCellValue(user.getNombre());
            filaData.createCell(2).setCellValue(user.getCorreo());
            filaData.createCell(3).setCellValue(user.getTelefono());
            fila++;
        }

        // Escribir al flujo y cerrar el workbook
        workbook.write(flujo);
        workbook.close();

        return new ByteArrayInputStream(flujo.toByteArray());
    }
}
