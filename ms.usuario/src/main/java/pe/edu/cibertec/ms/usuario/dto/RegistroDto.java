package pe.edu.cibertec.ms.usuario.dto;

import lombok.Data;

@Data
public class RegistroDto {
    private String numeroDocumento;
    private String apellido;
    private String nombre;
    private String fechaNacimiento; // Formato "YYYY-MM-DD"
    private String sexo;
    private String telefono;
    private String correo;
    private String contrasena;
}

