package obligatorio_da_310665_336194.dominio.propietario;

public class Penalizado extends EstadoPropietario {

	public Penalizado() {
		this.nombre = "Penalizado";
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
