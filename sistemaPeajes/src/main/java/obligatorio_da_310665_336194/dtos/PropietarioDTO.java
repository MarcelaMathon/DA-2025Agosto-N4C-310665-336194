package obligatorio_da_310665_336194.dtos;

import lombok.Getter;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;

public class PropietarioDTO {
    @Getter
    private String cedula;
    @Getter
    private String nombreCompleto;
    @Getter
    private String estado;
    @Getter
    private Double saldoActual;

    public PropietarioDTO(Propietario propietario) {
        this.cedula = propietario.getCedula();
        this.nombreCompleto = propietario.getNombre();
        this.estado = propietario.getNombreEstado();
        this.saldoActual = propietario.getSaldoActual();
    }

}
