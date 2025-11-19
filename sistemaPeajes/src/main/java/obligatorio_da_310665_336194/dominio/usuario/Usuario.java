package obligatorio_da_310665_336194.dominio.usuario;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;

@Getter
@Setter
public abstract class Usuario {

	private String nombre;

	private String cedula;

	@Getter(AccessLevel.NONE)
	private String password;

	public boolean validarAcceso(String password) {
		return this.password.equals(password);
	}

}
