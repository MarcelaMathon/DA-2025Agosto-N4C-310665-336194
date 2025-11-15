package obligatorio_da_310665_336194.dominio.transito;

import java.util.Date;

import lombok.Getter;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.bonificacion.AsignacionDeBonificacion;
import obligatorio_da_310665_336194.dominio.puesto.Puesto;
import obligatorio_da_310665_336194.dominio.puesto.Tarifa;
import obligatorio_da_310665_336194.dominio.vehiculo.Vehiculo;

public class Transito {
    @Getter
    private Puesto puesto;
    @Getter
    private Vehiculo vehiculo;
    @Getter
    private Date fechaHora;
    @Getter
    private Tarifa tarifaAplicada;
    @Getter
    private AsignacionDeBonificacion bonificacionAplicada;
    @Getter
    private Double costo;
    @Getter
    private Propietario propietario;

    public Transito(Puesto puesto, Vehiculo vehiculo, Date fechaHora) {
        this.puesto = puesto;
        this.vehiculo = vehiculo;
        this.fechaHora = fechaHora;
    }

    public Double calcularCosto(Tarifa tarifa, AsignacionDeBonificacion asignacionBonificacion) {
        this.tarifaAplicada = tarifa;
        this.bonificacionAplicada = asignacionBonificacion;
        Double montoBase = tarifa.getMonto();

        if (asignacionBonificacion != null) {
            Double descuento = asignacionBonificacion.obtenerDescuento(this.vehiculo, this.fechaHora);
            this.costo = montoBase * (1 - descuento);
            asignacionBonificacion.agregarTransito(this);
        } else {
            this.costo = montoBase;
        }

        return this.costo;
    }

}