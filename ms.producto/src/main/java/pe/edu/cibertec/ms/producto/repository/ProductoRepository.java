package pe.edu.cibertec.ms.producto.repository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.ms.producto.model.Producto;

import java.util.List;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {

    @Procedure("USP_GET_PRODUCTO")
    List<Producto> listarProductos();

    @Procedure("USP_GET_PRODUCTO_POR_CATEGORIA")
    List<Producto> listarPorCategoria(int CodCategoria);

    @Procedure("USP_GET_TOP5_PRODUCTOS_MAS_BARATOS")
    List<Producto> listarTop5MasBaratos();

    @Procedure("USP_GET_ID_PRODUCTO")
    Producto obtenerPorId(int CodProducto);

    @Procedure("USP_MERGE_PRODUCTO")
    void registrarOActualizarProducto(
            int CodProducto,
            int CodCategoria,
            String ImgProducto,
            String NomProducto,
            String Descripcion,
            double PreUni,
            int CodMarca,
            int Stock,
            boolean EstProducto
    );

    @Procedure("USP_DELETE_PRODUCTO")
    void eliminarProducto(int CodProducto);

    @Procedure("USP_ENABLE_PRODUCTO")
    void habilitarProducto(int CodProducto);
}
