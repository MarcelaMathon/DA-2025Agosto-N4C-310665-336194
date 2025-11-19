package obligatorio_da_310665_336194.dominio.usuario;

import lombok.Getter;

public class Administrador extends Usuario {
    @Getter
    private String cedula;
    @Getter
    private String nombre;

    public Administrador(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }
}
