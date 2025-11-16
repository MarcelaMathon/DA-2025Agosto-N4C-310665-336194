package obligatorio_da_310665_336194.dominio.propietario;

public class Penalizado extends EstadoPropietario {

	public Penalizado() {
		this.nombre = "Penalizado";
	}

	public boolean puedeIngresar() {
		return true;
	}

	public boolean puedeTransitar() {
		return true;
	}

	public Boolean recibeNotificaciones() {
		return false;
	}

	public Boolean aplicaBonificaciones() {
		return false;
	}

}
