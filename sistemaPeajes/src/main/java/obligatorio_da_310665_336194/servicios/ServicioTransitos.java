package obligatorio_da_310665_336194.servicios;

import java.util.ArrayList;
import java.util.Collection;

import obligatorio_da_310665_336194.dominio.bonificacion.AsignacionDeBonificacion;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.puesto.Puesto;
import obligatorio_da_310665_336194.dominio.puesto.Tarifa;
import obligatorio_da_310665_336194.dominio.transito.Transito;
import obligatorio_da_310665_336194.dominio.vehiculo.Vehiculo;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;
import obligatorio_da_310665_336194.servicios.fachada.Fachada;

import java.util.Date;
import java.util.List;
import java.util.Comparator;

public class ServicioTransitos {

	private Collection<Transito> transitos = new ArrayList<>();

	public Transito emularTransito(Puesto puesto, String matricula, Date fechaHora) throws PeajesExceptions {

		Fachada fachada = Fachada.getInstancia();

		Vehiculo vehiculo = fachada.getVehiculo(matricula);
		Vehiculo.validarExistencia(vehiculo);

		Propietario propietario = fachada.getPropietario(matricula);
		propietario.validarPuedeTransitar();

		Transito transito = new Transito(puesto, vehiculo, fechaHora);
		transito.setPropietario(propietario);

		AsignacionDeBonificacion bonificacion = null;
		if (!propietario.esPenalizado()) {
			bonificacion = fachada.obtenerBonificacion(puesto, propietario);
		}

		List<Tarifa> tarifas = fachada.tarifasDePuesto(puesto);

		Tarifa tarifaAplicable = null;
		for (Tarifa tarifa : tarifas) {
			if (tarifa.getCategoríaVehiculo().getNombre().equals(vehiculo.getCategoria().getNombre())) {
				tarifaAplicable = tarifa;
				break;
			}
		}

		transito.calcularCosto(tarifaAplicable, bonificacion);

		propietario.validarSaldoSuficiente(transito.getCosto());
		propietario.descontarSaldo(transito.getCosto());

		transitos.add(transito);
		vehiculo.getTransitos().add(transito);

		if (!propietario.esPenalizado()) {
			propietario.notificarTransito(transito);
			propietario.tieneSaldoBajo();
		}

		return transito;
	}

	public List<Transito> getTransitosPropietario(Propietario propietario) {
		List<Transito> transitosPropietario = new ArrayList<>();
		for (Transito transito : transitos) {
			if (transito.getPropietario() != null && transito.getPropietario().equals(propietario)) {
				transitosPropietario.add(transito);
			}
		}
		transitosPropietario.sort(Comparator.comparing(Transito::getFechaHora).reversed());
		return transitosPropietario;
	}

}
