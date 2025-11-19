package obligatorio_da_310665_336194.dominio.usuario;

import lombok.Getter;

@Getter
public class Sesion {

    private final Usuario usuario;
    private final String httpSessionId;

    public Sesion(Usuario usuario, String httpSessionId) {
        this.usuario = usuario;
        this.httpSessionId = httpSessionId;
    }

    public boolean esAdministrador() {
        return usuario instanceof Administrador;
    }

}
