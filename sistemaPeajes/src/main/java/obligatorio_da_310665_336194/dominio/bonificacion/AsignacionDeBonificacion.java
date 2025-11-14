package obligatorio_da_310665_336194.dominio.bonificacion;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.puesto.Puesto;
import obligatorio_da_310665_336194.dominio.transito.Transito;

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

	public String getNombreBonificacion() {
		return bonificacion.getNombre();
	}

	public String getTipoBonificacion() {
		return tipoBonificacion.getTipoBonificacion();
	}

	public Double obtenerDescuento(AsignacionDeBonificacion ab) {
		return tipoBonificacion.obtenerDescuento(ab);
	}

	public String getNombrePuesto() {
		return puesto.getNombre();
	}

}
