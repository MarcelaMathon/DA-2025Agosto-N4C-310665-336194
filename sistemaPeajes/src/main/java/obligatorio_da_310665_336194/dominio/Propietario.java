package obligatorio_da_310665_336194.dominio;

import java.util.List;

import lombok.Getter;

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

}
