package obligatorio_da_310665_336194.servicios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.usuario.Administrador;
import obligatorio_da_310665_336194.dominio.usuario.Sesion;
import obligatorio_da_310665_336194.dominio.usuario.Usuario;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;

public class ServicioUsuarios {
    private Map<String, Usuario> usuarios = new HashMap<String, Usuario>();
    private Collection<Sesion> sesiones = new java.util.ArrayList<>();

    public Propietario loginPropietario(String cedula, String password) throws PeajesExceptions {
        Usuario usuario = login(cedula, password);

        if (!(usuario instanceof Propietario)) {
            throw new PeajesExceptions("El usuario no es un propietario.");
        }

        Propietario propietario = (Propietario) usuario;
        
        if (!propietario.puedeIngresar()) {
            throw new PeajesExceptions("Usuario deshabilitado, no puede ingresar al sistema.");
        }
    
        return propietario;
    }

    public Administrador loginAdministrador(String cedula, String password) throws PeajesExceptions {
        Usuario usuario = login(cedula, password);

        if (!(usuario instanceof Administrador)) {
            throw new PeajesExceptions("El usuario no es un administrador.");
        }

        Administrador administrador = (Administrador) usuario;

        // Verificar si ESTE administrador específico ya está logueado
        if (existeAdministradorLogueado(administrador)) {
            throw new PeajesExceptions("Ud. Ya está logueado");
        }

        return administrador;
    }

    private boolean existeAdministradorLogueado(Administrador admin) {
        for (Sesion sesion : sesiones) {
            if (sesion.esAdministrador() && sesion.getUsuario().getCedula().equals(admin.getCedula())) {
                return true;
            }
        }
        return false;
    }

    private Usuario login(String cedula, String password) throws PeajesExceptions {
        Usuario usuario = usuarios.get(cedula);
        if (usuario != null && usuario.validarAcceso(password)) {
            return usuario;
        }
        throw new PeajesExceptions("Acceso denegado.");
    }

    public void agregar(Usuario usuario) {
        usuarios.put(usuario.getCedula(), usuario);
    }

    public Collection<Sesion> getSesiones() {
        return sesiones;
    }

    public void agregar(Sesion sesion) {
        sesiones.add(sesion);
    }

    public void remover(Sesion sesion) {
        sesiones.remove(sesion);
    }

    public void removerPorHttpSessionId(String httpSessionId) {
        sesiones.removeIf(sesion -> sesion.getHttpSessionId().equals(httpSessionId));
    }

}
