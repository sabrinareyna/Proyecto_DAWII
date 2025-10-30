package pe.edu.cibertec.ms.producto.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.ms.producto.dto.ProductoDTO;
import pe.edu.cibertec.ms.producto.model.Producto;

import java.util.List;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {


    @Query(value = "CALL USP_GET_PRODUCTO()", nativeQuery = true)
    List<ProductoDTO> getProductos();

    @Query(value = "CALL USP_GET_TOP5_PRODUCTOS_MAS_BARATOS()", nativeQuery = true)
    List<ProductoDTO> getTop5ProductosMasBaratos();

    @Query(value = "CALL USP_GET_PRODUCTOS_POR_PEDIDO(:CODPEDIDO)", nativeQuery = true)
    List<Object[]> getProductosByPedido(@Param("CODPEDIDO") Integer codPedido);

    @Query(value = "CALL USP_GET_PRODUCTO_POR_CATEGORIA(:CodCategoria)", nativeQuery = true)
    List<ProductoDTO> getProductosPorCategoria(@Param("CodCategoria") Integer codCategoria);

    @Query(value = "CALL USP_GET_ID_PRODUCTO(:CODPRODUCTO)", nativeQuery = true)
    ProductoDTO getProducto(@Param("CODPRODUCTO") Integer codProducto);

    @Modifying
    @Query(value = "CALL USP_MERGE_PRODUCTO(:CODPRODUCTO, :CODCATEGORIA, :IMGPRODUCTO, :NOMPRODUCTO, :DESCRIPCION, :PREUNI, :CODMARCA, :STOCK, :ESTPRODUCTO)", nativeQuery = true)
    void mergeProducto(
            @Param("CODPRODUCTO") Integer codProducto,
            @Param("CODCATEGORIA") Integer codCategoria,
            @Param("IMGPRODUCTO") String imgProducto,
            @Param("NOMPRODUCTO") String nomProducto,
            @Param("DESCRIPCION") String descripcion,
            @Param("PREUNI") Double preUni,
            @Param("CODMARCA") Integer codMarca,
            @Param("STOCK") Integer stock,
            @Param("ESTPRODUCTO") Boolean estProducto
    );

    @Modifying
    @Query(value = "CALL USP_DELETE_PRODUCTO(:CODPRODUCTO)", nativeQuery = true)
    void deleteProducto(@Param("CODPRODUCTO") Integer codProducto);

    @Modifying
    @Query(value = "CALL USP_ENABLE_PRODUCTO(:p_codproducto)", nativeQuery = true)
    void enableProducto(@Param("p_codproducto") Integer codProducto);


    @Modifying
    @Query(value = "CALL SP_ActualizarStock(:p_CODPRODUCTO, :p_CANTIDAD)", nativeQuery = true)
    void updateStock(
            @Param("p_CODPRODUCTO") Integer codProducto,
            @Param("p_CANTIDAD") Integer cantidad
    );

}
