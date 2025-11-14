package obligatorio_da_310665_336194.dominio;

import java.util.List;

import lombok.Getter;

public class Bonificacion {
	@Getter
	private String nombre;

	@Getter
	private List<AsignacionDeBonificacion> asignaciones;

}
