package obligatorio_da_310665_336194.dominio.propietario;

public class Habilitado extends EstadoPropietario {

	public Habilitado() {
		this.nombre = "Habilitado";
	}

	public boolean puedeIngresar() {
		return false;
	}

	public boolean puedeTransitar() {
		return false;
	}

	public Boolean recibeNotificaciones() {
		return null;
	}

	public Boolean aplicaBonificaciones() {
		return null;
	}

}
