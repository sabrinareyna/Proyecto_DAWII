package pe.edu.cibertec.ms.pedido.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.ms.pedido.Dto.request.PedidoDetalleRequest;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoOneResponse;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoResponse;
import pe.edu.cibertec.ms.pedido.Dto.response.ProductoPedidoResponse;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PedidoRepositoryImpl  implements IPedidoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final DataSource dataSource;  //es la conexión a tu base de datos (configurada en `application.properties`).

    @Autowired
    public PedidoRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    @Override
    public List<PedidoResponse> getPedidos() {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                    .withProcedureName("USP_GET_PEDIDO")
                    .returningResultSet("pedidos",
                            (rs, rowNum) -> new PedidoResponse(
                                    rs.getInt("codPedido"),
                                    rs.getDate("fecPed"),
                                    rs.getString("cliente"),
                                    rs.getDouble("precioTotal"),
                                    rs.getString("nomEstado"),
                                    rs.getInt("cantidadTotal"),
                                    rs.getBoolean("estPed")
                            )
                    );

            Map<String, Object> result = jdbcCall.execute();
            return (List<PedidoResponse>) result.get("pedidos");

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener pedidos: " + e.getMessage(), e);
        }
    }

    @Override
    public String insertarPedido(PedidoDetalleRequest pedido) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                    .withProcedureName("USP_INSERT_PEDIDODETALLE");

            // Convertir la lista de detalle a JSON con los nombres correctos
            ObjectMapper objectMapper = new ObjectMapper();

            // Mapear cada detalle a las claves esperadas por MySQL
            List<Map<String, Object>> detalleMapeado = pedido.getDetallePedido().stream()
                    .map(d -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("CODPRODUCTO", d.getCodProducto());
                        map.put("PREUNI", d.getPrecioUnitario());
                        map.put("CANTIDAD", d.getCantidad());
                        return map;
                    })
                    .collect(Collectors.toList());

            String detalleJson = objectMapper.writeValueAsString(detalleMapeado);

            // Mapear parámetros para el stored procedure
            Map<String, Object> params = Map.of(
                    "p_CODUSUARIO", pedido.getCodUsuario(),
                    "p_FECPED", pedido.getFecPed(),
                    "p_PRECIOTOTAL", pedido.getPrecioTotal(),
                    "p_CODESTADO", 1,
                    "p_ESTPED", 1,
                    "p_DETALLE", detalleJson
            );

            // Ejecutar procedimiento
            jdbcCall.execute(params);

            // Imprimir JSON en consola
            System.out.println(params);
            System.out.println(detalleJson);

            return "Se ha registrado el pedido.";

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir detalle a JSON: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar pedido: " + e.getMessage(), e);
        }
    }

    @Override
    public PedidoOneResponse getOnePedido(int codPedido) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                    .withProcedureName("USP_GET_ONE_PEDIDO")
                    .returningResultSet("pedido",
                            (rs, rowNum) -> new PedidoOneResponse(
                                    rs.getInt("CodPedido"),
                                    rs.getString("Cliente"),
                                    rs.getDate("FecPed").toLocalDate(),
                                    rs.getDouble("PrecioTotal"),
                                    rs.getString("CodEstado"),
                                    rs.getBoolean("EstPed")
                            )
                    );

            Map<String, Object> params = Map.of("CODPEDIDO", codPedido);
            Map<String, Object> result = jdbcCall.execute(params);
            List<PedidoOneResponse> list = (List<PedidoOneResponse>) result.get("pedido");

            return list.isEmpty() ? null : list.get(0);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener pedido: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ProductoPedidoResponse> getProductosByPedido(int codPedido) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                    .withProcedureName("USP_GET_PRODUCTOS_POR_PEDIDO")
                    .returningResultSet("productos",
                            (rs, rowNum) -> new ProductoPedidoResponse(
                                    rs.getString("imgProducto"),
                                    rs.getString("nomProducto"),
                                    rs.getBigDecimal("preUni"),
                                    rs.getInt("cantidad")
                            )
                    );

            Map<String, Object> params = Map.of("CODPEDIDO", codPedido);
            Map<String, Object> result = jdbcCall.execute(params);
            return (List<ProductoPedidoResponse>) result.get("productos");

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener productos del pedido: " + e.getMessage(), e);
        }
    }

    @Override
    public String actualizarPedido(int codPedido, int codEstado) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                    .withProcedureName("USP_UPDATE_PEDIDO");

            Map<String, Object> params = Map.of(
                    "p_CODPEDIDO", codPedido,
                    "p_CODESTADO", codEstado
            );

            Map<String, Object> result = jdbcCall.execute(params);

            return "¡Éxito! Se ha actualizado el pedido.";

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar pedido: " + e.getMessage(), e);
        }
    }

    @Override
    public String eliminarPedido(int codPedido) {
        try {
            // Ejecutamos el procedimiento (si quieres mantener SimpleJdbcCall)
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                    .withProcedureName("USP_DELETE_PEDIDO");

            Map<String, Object> params = Map.of("CODPEDIDO", codPedido);
            jdbcCall.execute(params);

            // Para obtener filas afectadas, hacemos un update manual
            String sql = "UPDATE PEDIDO SET CODESTADO = 5, ESTPED = 0 WHERE CODPEDIDO = ?";
            int filasAfectadas = jdbcTemplate.update(sql, codPedido);

            if (filasAfectadas == 0) {
                return ""; // igual que en .NET
            }

            return "Se ha eliminado " + filasAfectadas + " pedido" + (filasAfectadas > 1 ? "s" : "");

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar pedido: " + e.getMessage(), e);
        }
    }


}
