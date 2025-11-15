package obligatorio_da_310665_336194.dominio.bonificacion;

import java.util.Date;
import obligatorio_da_310665_336194.dominio.vehiculo.Vehiculo;

public class Frecuente extends TipoBonificacion {

	@Override
	public Double obtenerDescuento(AsignacionDeBonificacion asignacion, Vehiculo vehiculo, Date fechaHoraTransito) {
		int transitosDelDia = asignacion.contarTransitosDelDia(vehiculo, fechaHoraTransito);

		if (transitosDelDia >= 1) {
			return 0.5;
		}

		return 0.0;
	}

	@Override
	public String getTipoBonificacion() {
		return "Frecuente";
	}

}
