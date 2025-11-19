package obligatorio_da_310665_336194.controladores;

import jakarta.servlet.http.HttpSession;
import obligatorio_da_310665_336194.dominio.usuario.Usuario;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;
import obligatorio_da_310665_336194.servicios.fachada.Fachada;

public class ControladorLoginPropietario extends ControladorLoginAbstracto {

    private static final String PROPIETARIO_STATE_KEY = "PROPIETARIO_STATE_KEY";

    @Override
    protected Usuario getUsuario(String cedula, String password) throws PeajesExceptions {
        return Fachada.getInstancia().loginPropietario(cedula, password);
    }

    @Override
    protected void guardarEstadoUsuarioEnSesion(Usuario usuario, HttpSession sesion) {
        sesion.setAttribute(PROPIETARIO_STATE_KEY, usuario);
    }

    @Override
    protected String getDestinoLoginExitoso() {
        return "tableroPropietario.html";
    }

    @Override
    protected void eliminarSesion(HttpSession sesion) {
        sesion.removeAttribute(PROPIETARIO_STATE_KEY);
        sesion.invalidate();
    }

    @Override
    protected Usuario getEstadoUsuario(HttpSession sesion) {
        return (Usuario) sesion.getAttribute(PROPIETARIO_STATE_KEY);
    }

    @Override
    protected String getDestinoLogoutExitoso() {
        return "login.html";
    }

}
