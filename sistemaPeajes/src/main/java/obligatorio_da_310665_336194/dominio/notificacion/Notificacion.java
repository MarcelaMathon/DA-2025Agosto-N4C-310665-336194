package obligatorio_da_310665_336194.dominio.notificacion;

import java.util.Date;

import lombok.Getter;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;

public class Notificacion {

	@Getter
	private String mensaje;

	@Getter
	private Date fechaHora;

	@Getter
	private Propietario propietario;

	public Notificacion(Propietario propietario, String mensaje, Date fechaHora) {
		this.propietario = propietario;
		this.mensaje = mensaje;
		this.fechaHora = fechaHora;
	}

}
