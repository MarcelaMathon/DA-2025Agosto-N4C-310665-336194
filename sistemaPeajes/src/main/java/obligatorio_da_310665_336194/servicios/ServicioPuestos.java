package obligatorio_da_310665_336194.servicios;

import java.util.ArrayList;
import java.util.List;

import obligatorio_da_310665_336194.dominio.puesto.Puesto;
import obligatorio_da_310665_336194.dominio.puesto.Tarifa;

public class ServicioPuestos {

	private ArrayList<Puesto> puestos;

	public ServicioPuestos() {
		this.puestos = new ArrayList<>();
	}

	public void agregar(Puesto puesto) {
		if (puesto != null) {
			this.puestos.add(puesto);
		}
	}

	public List<Puesto> listarPuestos() {
		return puestos;
	}

	public List<String> getNombresPuestos() {
		List<String> nombresPuestos = new ArrayList<>();
		for (Puesto puesto : puestos) {
			nombresPuestos.add(puesto.getNombre());
		}
		return nombresPuestos;
	}

	public List<Tarifa> tarifasDePuesto(Puesto puesto) {
		for (Puesto p : puestos) {
			if (p.equals(puesto)) {
				return p.getTarifas();
			}
		}
		return null;
	}

}
