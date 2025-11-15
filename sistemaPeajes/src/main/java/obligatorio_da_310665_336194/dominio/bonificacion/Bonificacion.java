package obligatorio_da_310665_336194.dominio.bonificacion;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class Bonificacion {
	@Getter
	private String nombre;

	@Getter
	private List<AsignacionDeBonificacion> asignaciones;

	public Bonificacion(String nombre) {
		this.nombre = nombre;
		this.asignaciones = new ArrayList<>();
	}

}
