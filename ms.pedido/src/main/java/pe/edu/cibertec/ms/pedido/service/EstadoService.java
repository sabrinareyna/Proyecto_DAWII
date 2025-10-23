package pe.edu.cibertec.ms.pedido.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.ms.pedido.Dto.EstadosDto;
import pe.edu.cibertec.ms.pedido.Interfaces.IEstadoService;
import pe.edu.cibertec.ms.pedido.repository.EstadoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoService implements IEstadoService {

    private final EstadoRepository estadoRepository;

    @Override
    public List<EstadosDto> findAll() {
        return estadoRepository.findAll();
    }
}
