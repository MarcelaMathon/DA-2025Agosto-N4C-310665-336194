package obligatorio_da_310665_336194.dominio.propietario;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;

public abstract class EstadoPropietario {

	@Getter
	protected String nombre;
	@Getter
	private boolean puedeIngresar;

	private static final EstadoPropietario HABILITADO = new Habilitado();
	private static final EstadoPropietario DESHABILITADO = new Deshabilitado();
	private static final EstadoPropietario SUSPENDIDO = new Suspendido();
	private static final EstadoPropietario PENALIZADO = new Penalizado();

	private static final List<EstadoPropietario> ESTADOS_DISPONIBLES = Arrays.asList(
			HABILITADO,
			DESHABILITADO,
			SUSPENDIDO,
			PENALIZADO);

	public abstract boolean puedeIngresar();

	public abstract boolean puedeTransitar();

	public String getNombreEstado() {
		return this.nombre;
	}

	public abstract Boolean recibeNotificaciones();

	public abstract Boolean aplicaBonificaciones();

	public static List<EstadoPropietario> getEstadosDisponibles() {
		return ESTADOS_DISPONIBLES;
	}

	public static EstadoPropietario obtenerPorNombre(String nombreEstado) throws PeajesExceptions {
		for (EstadoPropietario estado : ESTADOS_DISPONIBLES) {
			if (estado.getNombreEstado().equalsIgnoreCase(nombreEstado)) {
				return estado;
			}
		}
		throw new PeajesExceptions("Estado no encontrado: " + nombreEstado);
	}

	public static EstadoPropietario habilitado() {
		return HABILITADO;
	}

	public static EstadoPropietario deshabilitado() {
		return DESHABILITADO;
	}

	public static EstadoPropietario suspendido() {
		return SUSPENDIDO;
	}

	public static EstadoPropietario penalizado() {
		return PENALIZADO;
	}

}
