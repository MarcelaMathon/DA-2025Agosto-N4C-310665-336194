package obligatorio_da_310665_336194.dtos;

import lombok.Getter;
import obligatorio_da_310665_336194.dominio.puesto.Tarifa;

public class TarifaDTO {
    @Getter
    private Double monto;

    @Getter
    private String categoriaVehiculo;

    @Getter
    private String nombrePuesto;

    public TarifaDTO(Tarifa tarifa) {
        this.monto = tarifa.getMonto();
        this.categoriaVehiculo = tarifa.getCategoríaVehiculo().getNombre();
        this.nombrePuesto = tarifa.getPuesto().getNombre();
    }

}
