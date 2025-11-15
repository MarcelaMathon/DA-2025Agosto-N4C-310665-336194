package obligatorio_da_310665_336194.dtos;

import java.text.SimpleDateFormat;

import lombok.Getter;
import obligatorio_da_310665_336194.dominio.bonificacion.AsignacionDeBonificacion;

public class BonificacionAsignadaDTO {
    @Getter
    private String nombreBonificacion;
    @Getter
    private String nombrePuesto;
    @Getter
    private String fechaAsignacion;

    public BonificacionAsignadaDTO(AsignacionDeBonificacion asignacion) {
        this.nombreBonificacion = asignacion.getTipoBonificacion();
        this.nombrePuesto = asignacion.getNombrePuesto();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        this.fechaAsignacion = formatter.format(asignacion.getFechaHora());
    }

}
