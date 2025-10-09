package pe.edu.cibertec.ms.producto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.ms.producto.repository.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    // USP_SELECT_CATEGORIAS
    public List<Object[]> selectCategorias() {
        List<Object[]> categorias = categoriaRepository.selectCategorias();

        if (categorias == null || categorias.isEmpty()) {
            throw new RuntimeException("No se encontraron categorías registradas.");
        }

        return categorias;
    }
}
