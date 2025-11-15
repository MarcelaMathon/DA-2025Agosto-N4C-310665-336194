package obligatorio_da_310665_336194.dominio.bonificacion;

import java.util.Calendar;
import java.util.Date;
import obligatorio_da_310665_336194.dominio.vehiculo.Vehiculo;

public class Trabajador extends TipoBonificacion {

	@Override
	public Double obtenerDescuento(AsignacionDeBonificacion asignacion, Vehiculo vehiculo, Date fechaHoraTransito) {
		if (esDiaDeSemana(fechaHoraTransito)) {
			return 0.8;
		}
		return 0.0;
	}

	private boolean esDiaDeSemana(Date fecha) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);
		int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

		return diaSemana >= Calendar.MONDAY && diaSemana <= Calendar.FRIDAY;
	}

	@Override
	public String getTipoBonificacion() {
		return "Trabajador";
	}

}
