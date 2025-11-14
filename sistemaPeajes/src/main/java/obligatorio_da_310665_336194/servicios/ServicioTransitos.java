package obligatorio_da_310665_336194.servicios;

import java.util.ArrayList;
import java.util.Collection;
import obligatorio_da_310665_336194.dominio.AsignacionDeBonificacion;
import obligatorio_da_310665_336194.dominio.Propietario;
import obligatorio_da_310665_336194.dominio.Puesto;
import obligatorio_da_310665_336194.dominio.Tarifa;
import obligatorio_da_310665_336194.dominio.Transito;
import obligatorio_da_310665_336194.dominio.Vehiculo;
import java.util.Date;
import java.util.List;

public class ServicioTransitos {

	private Collection<Transito> transitos = new ArrayList<>();

	public Transito emularTransito(Puesto puesto, String matricula, Date fechaHora) {

		Fachada fachada = Fachada.getInstancia();

		// 1. Obtener vehículo
		Vehiculo vehiculo = fachada.getVehiculo(matricula);
		if (vehiculo == null) {
			return null;
		}

		// 2. Obtener propietario
		Propietario propietario = fachada.getPropietario(matricula);
		if (propietario == null) {
			return null;
		}

		// 3. Validar que puede transitar
		if (!fachada.puedeTransitar(propietario)) {
			return null;
		}

		// 4. Crear el tránsito
		Transito transito = new Transito(puesto, vehiculo, fechaHora);

		// 5. Obtener bonificación si aplica
		AsignacionDeBonificacion bonificacion = fachada.obtenerBonificacion(puesto, propietario);

		// 6. Obtener tarifa del puesto según categoría del vehículo
		List<Tarifa> tarifas = fachada.tarifasDePuesto(puesto);

		Tarifa tarifaAplicable = null;

		for (Tarifa tarifa : tarifas) {
			if (tarifa.getCategoríaVehiculo().getNombre().equals(vehiculo.getCategoria().getNombre())) {
				tarifaAplicable = tarifa;
			}
		}

		// Validar que se encontró la tarifa
		if (tarifaAplicable == null) {
			return null;
		}

		// 7. Calcular costo del tránsito
		transito.calcularCosto(tarifaAplicable, bonificacion);

		// 8. Descontar del saldo del propietario
		propietario.descontarSaldo(transito.getCosto());

		// 9. Registrar notificación del tránsito
		String mensajeTransito = fechaHora + " Pasaste por el puesto " + puesto.getNombre()
				+ " con el vehículo " + matricula;
		fachada.enviarNotificacion(propietario, mensajeTransito);

		// 10. Verificar saldo bajo y notificar si corresponde
		if (propietario.tieneSaldoBajo()) {
			fachada.enviarSaldoBajo(propietario);
		}

		// 11. Guardar el tránsito
		transitos.add(transito);

		return transito;
	}

	public List<Transito> getTransitosPropietario(Propietario propietario) {
		return null;
	}

}
