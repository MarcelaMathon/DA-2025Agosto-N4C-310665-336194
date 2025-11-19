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
        return usuario instanceof Propietario ? (Propietario) usuario : null;
    }

    public Administrador loginAdministrador(String cedula, String password) throws PeajesExceptions {
        // Verificar si ya hay un administrador logueado
        for (Sesion sesion : sesiones) {
            if (sesion.esAdministrador()) {
                throw new PeajesExceptions("Ud. Ya está logueado");
            }
        }
        
        Usuario usuario = login(cedula, password);
        return usuario instanceof Administrador ? (Administrador) usuario : null;
    }

    private Usuario login(String cedula, String password) throws PeajesExceptions {
        Usuario usuario = usuarios.get(cedula);
        if (usuario != null && usuario.validarAcceso(password)) {
            return usuario;
        }
        throw new PeajesExceptions("Usuario o password incorrecto");
    }

    public void agregar(Usuario usuario) {
        usuarios.put(usuario.getNombre(), usuario);
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
