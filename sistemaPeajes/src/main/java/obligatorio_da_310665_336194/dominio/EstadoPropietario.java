package obligatorio_da_310665_336194.dominio;

public abstract class EstadoPropietario {

	private String nombre;

	private boolean puedeIngresar;

	public abstract boolean puedeIngresar();

	public abstract boolean puedeTransitar();

	public String getNombreEstado() {
		return null;
	}

	public abstract Boolean recibeNotificaciones();

	public abstract Boolean aplicaBonificaciones();

}
