package pe.edu.cibertec.ms.producto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.ms.pedido.model.Marca;
import pe.edu.cibertec.ms.pedido.service.MarcaService;

import java.util.List;

@RestController
@RequestMapping("/marcas")
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public List<Marca> listar() {
        return marcaService.getMarcas();
    }

    @GetMapping("/{id}")
    public Marca obtenerPorId(@PathVariable int idmarc) {
        return marcaService.obtenerMarcaPorId(idmarc);
    }

    @GetMapping("/select")
    public List<Object[]> select() {
        return marcaService.selectMarcas();
    }
}
