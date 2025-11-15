package obligatorio_da_310665_336194.dominio.bonificacion;

import java.util.Date;
import obligatorio_da_310665_336194.dominio.vehiculo.Vehiculo;

public class Exonerado extends TipoBonificacion {

	@Override
	public Double obtenerDescuento(AsignacionDeBonificacion asignacion, Vehiculo vehiculo, Date fechaHoraTransito) {
		return 1.0;
	}

	@Override
	public String getTipoBonificacion() {
		return "Exonerado";
	}

}
