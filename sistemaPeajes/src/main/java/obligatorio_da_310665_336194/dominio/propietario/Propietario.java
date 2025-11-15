package obligatorio_da_310665_336194.dominio.propietario;

import java.util.List;

import lombok.Getter;
import obligatorio_da_310665_336194.dominio.Usuario;
import obligatorio_da_310665_336194.dominio.bonificacion.AsignacionDeBonificacion;
import obligatorio_da_310665_336194.dominio.notificacion.Notificacion;
import obligatorio_da_310665_336194.dominio.transito.Transito;
import obligatorio_da_310665_336194.dominio.vehiculo.Vehiculo;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;

public class Propietario extends Usuario {

	@Getter
	private Double saldoActual;
	@Getter
	private Double saldoMinimoAlerta;
	@Getter
	private String cedula;
	@Getter
	private EstadoPropietario estadoActual;
	@Getter
	private List<AsignacionDeBonificacion> asignacionesDeBonificacion;
	@Getter
	private List<Notificacion> notificaciones;
	@Getter
	private List<Transito> transitos;
	@Getter
	private List<Vehiculo> vehículos;

	public Boolean puedeIngresar() {
		return null;
	}

	public boolean puedeTransitar() {
		return false;
	}

	/**
	 *  
	 */
	public String getNombreEstado() {
		return null;
	}

	public Boolean recibeNotificaciones() {
		return null;
	}

	public Boolean aplicaBonificaciones() {
		return null;
	}

	public EstadoPropietario cambiarEstado(EstadoPropietario nuevoEstado) {
		return null;
	}

	public void descontarSaldo(Double monto) {
		this.saldoActual -= monto;
	}

	public boolean tieneSaldoBajo() {
		return this.saldoActual < this.saldoMinimoAlerta;
	}

	// Validaciones aplicando Principio de Experto
	public void validarPuedeTransitar() throws PeajesExceptions {
		String estado = this.getNombreEstado();

		if ("Deshabilitado".equals(estado)) {
			throw new PeajesExceptions("El propietario del vehículo está deshabilitado, no puede realizar tránsitos");
		}

		if ("Suspendido".equals(estado)) {
			throw new PeajesExceptions("El propietario del vehículo está suspendido, no puede realizar tránsitos");
		}
	}

	public void validarSaldoSuficiente(Double monto) throws PeajesExceptions {
		if (this.saldoActual < monto) {
			throw new PeajesExceptions("Saldo insuficiente: " + this.saldoActual);
		}
	}

	public boolean esPenalizado() {
		return "Penalizado".equals(this.getNombreEstado());
	}

	public void validarPuedeRecibirBonificacion() throws PeajesExceptions {
		if ("Deshabilitado".equals(this.getNombreEstado())) {
			throw new PeajesExceptions("El propietario esta deshabilitado. No se pueden asignar bonificaciones");
		}
	}

	public boolean estaDeshabilitado() {
		return "Deshabilitado".equals(this.getNombreEstado());
	}

}
