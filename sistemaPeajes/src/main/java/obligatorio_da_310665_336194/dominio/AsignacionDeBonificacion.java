package obligatorio_da_310665_336194.dominio;

import java.util.Date;
import java.util.List;

import lombok.Getter;

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
