package obligatorio_da_310665_336194.dominio.propietario;

import lombok.Getter;

public abstract class EstadoPropietario {

	@Getter
	private String nombre;
	@Getter
	private boolean puedeIngresar;

	public abstract boolean puedeIngresar();

	public abstract boolean puedeTransitar();

	public String getNombreEstado() {
		return null;
	}

	public abstract Boolean recibeNotificaciones();

	public abstract Boolean aplicaBonificaciones();

}
