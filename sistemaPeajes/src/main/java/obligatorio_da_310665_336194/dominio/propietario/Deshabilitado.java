package obligatorio_da_310665_336194.dominio.propietario;

public class Deshabilitado extends EstadoPropietario {

	public Deshabilitado() {
		this.nombre = "Deshabilitado";
	}

	public boolean puedeIngresar() {
		return false;
	}

	public boolean puedeTransitar() {
		return false;
	}

	public Boolean recibeNotificaciones() {
		return true;
	}

	public Boolean aplicaBonificaciones() {
		return false;
	}

}
