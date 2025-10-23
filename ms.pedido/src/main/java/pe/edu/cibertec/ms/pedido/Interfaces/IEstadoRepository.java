package pe.edu.cibertec.ms.pedido.Interfaces;

import pe.edu.cibertec.ms.pedido.Dto.EstadosDto;

import java.util.List;

public interface IEstadoRepository {
    List<EstadosDto>  findAll();
}
