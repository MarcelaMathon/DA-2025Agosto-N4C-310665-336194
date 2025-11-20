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
		Vehiculo vehiculo = validarYObtenerVehiculo(matricula);
		Propietario propietario = validarYObtenerPropietario(matricula);

		Transito transito = crearTransito(puesto, vehiculo, propietario, fechaHora);

		AsignacionDeBonificacion bonificacion = obtenerBonificacionSiAplica(puesto, propietario);
		Tarifa tarifaAplicable = buscarTarifaParaVehiculo(puesto, vehiculo);

		procesarCostoYPago(transito, tarifaAplicable, bonificacion, propietario);
		registrarTransito(transito, vehiculo, propietario);
		notificarSiCorresponde(propietario, transito);

		return transito;
	}

	private Vehiculo validarYObtenerVehiculo(String matricula) throws PeajesExceptions {
		Fachada fachada = Fachada.getInstancia();
		Vehiculo vehiculo = fachada.getVehiculo(matricula);
		Vehiculo.validarExistencia(vehiculo);
		return vehiculo;
	}

	private Propietario validarYObtenerPropietario(String matricula) throws PeajesExceptions {
		Fachada fachada = Fachada.getInstancia();
		Propietario propietario = fachada.getPropietario(matricula);
		propietario.validarPuedeTransitar();
		return propietario;
	}

	private Transito crearTransito(Puesto puesto, Vehiculo vehiculo, Propietario propietario, Date fechaHora) {
		Transito transito = new Transito(puesto, vehiculo, fechaHora);
		transito.setPropietario(propietario);
		return transito;
	}

	private AsignacionDeBonificacion obtenerBonificacionSiAplica(Puesto puesto, Propietario propietario) {
		if (propietario.esPenalizado()) {
			return null;
		}
		Fachada fachada = Fachada.getInstancia();
		return fachada.obtenerBonificacion(puesto, propietario);
	}

	private Tarifa buscarTarifaParaVehiculo(Puesto puesto, Vehiculo vehiculo) {
		Fachada fachada = Fachada.getInstancia();
		List<Tarifa> tarifas = fachada.tarifasDePuesto(puesto);
		for (Tarifa tarifa : tarifas) {
			if (tarifa.getCategoríaVehiculo().getNombre().equals(vehiculo.getCategoria().getNombre())) {
				return tarifa;
			}
		}
		return null;
	}

	private void procesarCostoYPago(Transito transito, Tarifa tarifaAplicable, AsignacionDeBonificacion bonificacion,
			Propietario propietario) throws PeajesExceptions {
		transito.calcularCosto(tarifaAplicable, bonificacion);
		propietario.validarSaldoSuficiente(transito.getCosto());
		propietario.descontarSaldo(transito.getCosto());
	}

	private void registrarTransito(Transito transito, Vehiculo vehiculo, Propietario propietario) {
		transitos.add(transito);
		vehiculo.getTransitos().add(transito);
		propietario.getTransitos().add(transito);
	}

	private void notificarSiCorresponde(Propietario propietario, Transito transito) {
		if (!propietario.esPenalizado()) {
			propietario.notificarTransito(transito);
			propietario.tieneSaldoBajo();
		}
	}

	public List<Transito> getTransitosPropietario(Propietario propietario) {
		List<Transito> transitosPropietario = propietario.getTransitos();
		transitosPropietario.sort(Comparator.comparing(Transito::getFechaHora).reversed());
		return transitosPropietario;
	}

}
