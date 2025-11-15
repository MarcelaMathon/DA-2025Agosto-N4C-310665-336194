package obligatorio_da_310665_336194.dominio.bonificacion;

import java.util.Date;
import obligatorio_da_310665_336194.dominio.vehiculo.Vehiculo;

public abstract class TipoBonificacion {

	public abstract String getTipoBonificacion();

	public abstract Double obtenerDescuento(AsignacionDeBonificacion asignacion, Vehiculo vehiculo,
			Date fechaHoraTransito);

}
