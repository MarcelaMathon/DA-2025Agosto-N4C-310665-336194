package obligatorio_da_310665_336194.dominio.propietario;

public class Habilitado extends EstadoPropietario {

	public Habilitado() {
		this.nombre = "Habilitado";
	}

	public boolean puedeIngresar() {
		return true;
	}

	public boolean puedeTransitar() {
		return true;
	}

	public Boolean recibeNotificaciones() {
		return true;
	}

	public Boolean aplicaBonificaciones() {
		return true;
	}

}
