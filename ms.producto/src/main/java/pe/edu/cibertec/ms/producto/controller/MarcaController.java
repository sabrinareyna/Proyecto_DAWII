package pe.edu.cibertec.ms.producto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.ms.producto.model.Marca;
import pe.edu.cibertec.ms.producto.service.MarcaService;

import java.util.List;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/ListarMarcas")
    public List<Marca> listarMarcas() {
        return marcaService.getMarcas();
    }

    @GetMapping("/SelectMarcas")
    public List<Object[]> selectMarcas() {
        return marcaService.selectMarcas();
    }

    @GetMapping("/BuscarMarca/{codMarca}")
    public Marca buscarPorId(@PathVariable int codMarca) {
        return marcaService.obtenerMarcaPorId(codMarca);
    }
}
