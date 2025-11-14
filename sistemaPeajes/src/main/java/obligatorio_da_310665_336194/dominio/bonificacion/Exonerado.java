package obligatorio_da_310665_336194.dominio.bonificacion;

public class Exonerado extends TipoBonificacion {

	@Override
	public Double obtenerDescuento(AsignacionDeBonificacion ab) {
		return null;
	}

	@Override
	public String getTipoBonificacion() {
		return "Exonerado";
	}

}
