package obligatorio_da_310665_336194.dtos;

import lombok.Getter;
import obligatorio_da_310665_336194.dominio.puesto.Puesto;

public class PuestoDTO {
    @Getter
    private String nombre;

    @Getter
    private String direccion;

    public PuestoDTO(Puesto puesto) {
        this.nombre = puesto.getNombre();
        this.direccion = puesto.getDireccion();
    }

}
