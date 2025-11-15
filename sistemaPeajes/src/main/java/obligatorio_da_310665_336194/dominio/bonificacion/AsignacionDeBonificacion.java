package obligatorio_da_310665_336194.dominio.bonificacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.puesto.Puesto;
import obligatorio_da_310665_336194.dominio.transito.Transito;
import obligatorio_da_310665_336194.dominio.vehiculo.Vehiculo;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;

public class AsignacionDeBonificacion {
	@Getter
	private Bonificacion bonificacion;
	@Getter
	private Date fechaHora;
	@Getter
	private Puesto puesto;
	@Getter
	private List<Transito> transitos;
	@Getter
	private Propietario propietario;
	private TipoBonificacion tipoBonificacion;

	public AsignacionDeBonificacion(Propietario propietario, Puesto puesto, TipoBonificacion tipoBonificacion)
			throws PeajesExceptions {
		if (tipoBonificacion == null) {
			throw new PeajesExceptions("Debe especificar una bonificación");
		}

		if (puesto == null) {
			throw new PeajesExceptions("Debe especificar un puesto");
		}

		propietario.validarPuedeRecibirBonificacion();

		this.propietario = propietario;
		this.puesto = puesto;
		this.tipoBonificacion = tipoBonificacion;
		this.fechaHora = new Date();
		this.transitos = new ArrayList<>();
	}

	public String getNombreBonificacion() {
		return bonificacion.getNombre();
	}

	public String getTipoBonificacion() {
		return tipoBonificacion.getTipoBonificacion();
	}

	public Double obtenerDescuento(Vehiculo vehiculo, Date fechaHoraTransito) {
		return tipoBonificacion.obtenerDescuento(this, vehiculo, fechaHoraTransito);
	}

	public int contarTransitosDelDia(Vehiculo vehiculo, Date fecha) {
		int contador = 0;
		for (Transito transito : transitos) {
			if (transito.getVehiculo().equals(vehiculo) && esMismoDia(transito.getFechaHora(), fecha)) {
				contador++;
			}
		}
		return contador;
	}

	private boolean esMismoDia(Date fecha1, Date fecha2) {
		if (fecha1 == null || fecha2 == null) {
			return false;
		}
		if (fecha1.equals(fecha2)) {
			return true;
		}
		return false;
	}

	public void agregarTransito(Transito transito) {
		this.transitos.add(transito);
	}

	public String getNombrePuesto() {
		return puesto.getNombre();
	}

}
