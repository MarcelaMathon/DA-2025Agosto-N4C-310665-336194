package obligatorio_da_310665_336194.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;

import obligatorio_da_310665_336194.dominio.usuario.Sesion;
import obligatorio_da_310665_336194.dominio.usuario.Usuario;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;
import obligatorio_da_310665_336194.servicios.fachada.Fachada;
import obligatorio_da_310665_336194.utils.Respuesta;
import jakarta.servlet.http.HttpSession;

public abstract class ControladorLoginAbstracto {

    protected Fachada fachada = Fachada.getInstancia();

    @PostMapping("login")
    public List<Respuesta> login(HttpSession sesion, String cedula, String password) throws PeajesExceptions {
        Usuario usuario = getUsuario(cedula, password);
        if (usuario != null) {
            guardarEstadoUsuarioEnSesion(usuario, sesion);
            // Registrar sesión en la colección
            fachada.agregar(new Sesion(usuario, sesion.getId()));
            return Respuesta.lista(new Respuesta("Login_Exitoso", getDestinoLoginExitoso()));
        } else {
            return Respuesta.lista(new Respuesta("Login_Fallido", getDestinoLogoutExitoso()));
        }
    }

    @PostMapping("logout")
    public List<Respuesta> logout(HttpSession sesion) {
        Usuario usuario = getEstadoUsuario(sesion);
        if (usuario != null) {
            // Remover sesión de la colección antes de invalidar
            fachada.removerPorHttpSessionId(sesion.getId());
            eliminarSesion(sesion);
            return Respuesta.lista(new Respuesta("Logout_exitoso", getDestinoLogoutExitoso()));
        } else {
            return Respuesta.lista(new Respuesta("No_hay_usuario_logueado", getDestinoLogoutExitoso()));
        }
    }

    protected abstract Usuario getUsuario(String cedula, String password) throws PeajesExceptions;

    protected abstract void guardarEstadoUsuarioEnSesion(Usuario usuario, HttpSession sesion);

    protected abstract String getDestinoLoginExitoso();

    protected abstract void eliminarSesion(HttpSession sesion);

    protected abstract Usuario getEstadoUsuario(HttpSession sesion);

    protected abstract String getDestinoLogoutExitoso();
}