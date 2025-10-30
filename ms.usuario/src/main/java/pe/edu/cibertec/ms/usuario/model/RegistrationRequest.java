package pe.edu.cibertec.ms.usuario.model;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String numeroDocumento;
    private String apellido;
    private String nombre;
    private String fechaNacimiento;
    private String sexo;
    private String telefono;
    private String correo;
    private String contrasenia;
}
