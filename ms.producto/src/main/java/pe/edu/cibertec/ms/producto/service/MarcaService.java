package pe.edu.cibertec.ms.producto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.ms.producto.model.Marca;
import pe.edu.cibertec.ms.producto.repository.MarcaRepository;

import java.util.List;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    //USP_GET_MARCA
    public List<Marca> getMarcas() {
        try {
            List<Marca> listado = marcaRepository.getMarcas();
            if (listado == null || listado.isEmpty()) {
                throw new RuntimeException("No se encontraron marcas registradas.");
            }
            return listado;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las marcas: " + e.getMessage());
        }
    }

    // USP_SELECT_MARCAS
    public List<Object[]> selectMarcas() {
        try {
            List<Object[]> listado = marcaRepository.selectMarcas();
            if (listado == null || listado.isEmpty()) {
                throw new RuntimeException("No se encontraron marcas para el select.");
            }
            return listado;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el listado de marcas: " + e.getMessage());
        }
    }

    //USP_GET_ID_MARCA
    public Marca obtenerMarcaPorId(int codMarca) {
        try {
            Marca marca = marcaRepository.getMarcaById(codMarca);
            if (marca == null) {
                throw new RuntimeException("La marca con ID " + codMarca + " no existe.");
            }
            return marca;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la marca por ID: " + e.getMessage());
        }
    }
}
