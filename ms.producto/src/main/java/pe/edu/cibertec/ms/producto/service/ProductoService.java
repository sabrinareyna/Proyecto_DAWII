package pe.edu.cibertec.ms.producto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.ms.producto.dto.ProductoDTO;
import pe.edu.cibertec.ms.producto.model.Producto;
import pe.edu.cibertec.ms.producto.repository.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<ProductoDTO> getProductos() {
        return productoRepository.getProductos();
    }

    @Transactional(readOnly = true)
    public List<ProductoDTO> getTop5ProductosMasBaratos() {
        return productoRepository.getTop5ProductosMasBaratos();
    }

    @Transactional(readOnly = true)
    public List<Object[]> getProductosByPedido(Integer codPedido) {
        return productoRepository.getProductosByPedido(codPedido);
    }

    @Transactional(readOnly = true)
    public List<ProductoDTO> getProductosPorCategoria(Integer codCategoria) {
        return productoRepository.getProductosPorCategoria(codCategoria);
    }

    @Transactional(readOnly = true)
    public ProductoDTO getProducto(Integer codProducto) {
        return productoRepository.getProducto(codProducto);
    }

    @Transactional
    public String mergeProducto(Producto producto, String accion) {
        productoRepository.mergeProducto(
                producto.getCodProducto(),
                producto.getCategoria().getCodCategoria().intValue(),
                producto.getImgProducto(),
                producto.getNomProducto(),
                producto.getDescripcion(),
                producto.getPreUni().doubleValue(),
                producto.getMarca().getCodMarca().intValue(),
                producto.getStock(),
                producto.getEstProducto()
        );
        return "Se ha realizado la " + accion + " de producto.";
    }

    //_-----------------------
    @Transactional(readOnly = true)
    public Producto getProductoEntity(Integer codProducto) {
        return productoRepository.findById(codProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }



    @Transactional
    public String cambiarEstadoProducto(Integer codProducto) {
        Producto producto = getProductoEntity(codProducto);
        Boolean estActual = producto.getEstProducto();

        if (Boolean.TRUE.equals(estActual)) {
            productoRepository.deleteProducto(codProducto);
            return "Se ha desactivado el producto.";
        } else {
            productoRepository.enableProducto(codProducto);
            return "Se ha activado el producto.";
        }
    }
    @Transactional
    public String actualizarStock(Integer codProducto, Integer cantidad) {
        try {
            productoRepository.updateStock(codProducto, cantidad);
            return "Stock actualizado correctamente.";
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el stock: " + e.getMessage(), e);
        }
    }
}

