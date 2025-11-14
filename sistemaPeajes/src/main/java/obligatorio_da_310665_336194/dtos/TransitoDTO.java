package obligatorio_da_310665_336194.dtos;

import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.transito.Transito;

public class TransitoDTO {
    private String nombrePropietario;
    private String estadoPropietario;
    private String categoriaVehiculo;
    private String nombreBonificacion;
    private Double costoTransito;
    private Double saldoFinal;

    public TransitoDTO(Transito transito, Propietario propietario) {
        this.nombrePropietario = propietario.getNombre();
        this.estadoPropietario = propietario.getNombreEstado();
        this.categoriaVehiculo = transito.getVehiculo().getCategoria().getNombre();
        this.nombreBonificacion = transito.getBonificacionAplicada() != null
                ? transito.getBonificacionAplicada().getNombreBonificacion()
                : "Sin bonificación";
        this.costoTransito = transito.getCosto();
        this.saldoFinal = propietario.getSaldoActual();
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public String getEstadoPropietario() {
        return estadoPropietario;
    }

    public String getCategoriaVehiculo() {
        return categoriaVehiculo;
    }

    public String getNombreBonificacion() {
        return nombreBonificacion;
    }

    public Double getCostoTransito() {
        return costoTransito;
    }

    public Double getSaldoFinal() {
        return saldoFinal;
    }
}
