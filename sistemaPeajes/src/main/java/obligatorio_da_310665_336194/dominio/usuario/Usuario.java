package obligatorio_da_310665_336194.dominio.usuario;

import lombok.Getter;

public abstract class Usuario {

	@Getter
	private String nombre;
	@Getter
	private String cedula;
	@Getter
	private String password;

	public boolean validarAcceso(String password) {
		return false;
	}

}
