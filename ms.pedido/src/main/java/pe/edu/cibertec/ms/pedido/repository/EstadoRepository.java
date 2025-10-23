package pe.edu.cibertec.ms.pedido.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.ms.pedido.Dto.EstadosDto;
import pe.edu.cibertec.ms.pedido.Interfaces.IEstadoRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class EstadoRepository implements IEstadoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final DataSource dataSource;

    @Autowired
    public EstadoRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    @Override
    public List<EstadosDto> findAll() {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                    .withProcedureName("USP_SELECT_ESTADOS")
                    .returningResultSet("estados",
                            (rs, rowNum) -> new EstadosDto(
                                    rs.getInt("Value"),
                                    rs.getString("Name")
                            )
                    );

            Map<String, Object> result = jdbcCall.execute();
            return (List<EstadosDto>) result.get("estados");

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los estados: " + e.getMessage(), e);
        }
    }
}
