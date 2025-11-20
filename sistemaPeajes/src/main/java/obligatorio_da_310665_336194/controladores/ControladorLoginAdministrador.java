package obligatorio_da_310665_336194.controladores;

import obligatorio_da_310665_336194.dominio.usuario.Usuario;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;
import obligatorio_da_310665_336194.servicios.fachada.Fachada;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/acceso/admin")
public class ControladorLoginAdministrador extends ControladorLoginAbstracto {

	private static final String ADMINISTRADOR_STATE_KEY = "ADMINISTRADOR_STATE_KEY";

	@Override
	protected Usuario getUsuario(String cedula, String password) throws PeajesExceptions {
		return Fachada.getInstancia().loginAdministrador(cedula, password);
	}

	@Override
	protected void guardarEstadoUsuarioEnSesion(Usuario usuario, HttpSession sesion) {
		sesion.setAttribute(ADMINISTRADOR_STATE_KEY, usuario);
	}

	@Override
	protected String getDestinoLoginExitoso() {
		return "menuAdministrador.html";
	}

	@Override
	protected void eliminarSesion(HttpSession sesion) {
		sesion.removeAttribute(ADMINISTRADOR_STATE_KEY);
		sesion.invalidate();
	}

	@Override
	protected Usuario getEstadoUsuario(HttpSession sesion) {
		return (Usuario) sesion.getAttribute(ADMINISTRADOR_STATE_KEY);
	}

	@Override
	protected String getDestinoLogoutExitoso() {
		return "index.html";
	}

}
