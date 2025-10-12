package pe.edu.cibertec.ms.pedido.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.ms.pedido.Dto.request.PedidoDetalleRequest;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoResponse;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

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
        return "";
    }

    @Override
    public String actualizarPedido(int codPedido, int codEstado) {
        return "";
    }

    @Override
    public String eliminarPedido(int codPedido) {
        return "";
    }
}
