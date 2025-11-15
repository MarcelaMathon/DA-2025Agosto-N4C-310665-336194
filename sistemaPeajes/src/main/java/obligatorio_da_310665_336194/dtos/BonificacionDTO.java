package obligatorio_da_310665_336194.dtos;

import lombok.Getter;
import obligatorio_da_310665_336194.dominio.bonificacion.TipoBonificacion;

public class BonificacionDTO {
    @Getter
    private String nombre;

    public BonificacionDTO(TipoBonificacion tipoBonificacion) {
        this.nombre = tipoBonificacion.getTipoBonificacion();
    }

}
