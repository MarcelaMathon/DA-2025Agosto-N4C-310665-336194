package obligatorio_da_310665_336194.dominio;

import java.util.Date;

import lombok.Getter;

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

    public Transito(Puesto puesto, Vehiculo vehiculo, Date fechaHora) {
        this.puesto = puesto;
        this.vehiculo = vehiculo;
        this.fechaHora = fechaHora;
    }

    public Double calcularCosto(Tarifa tarifa, AsignacionDeBonificacion bonificacion) {
        this.tarifaAplicada = tarifa;
        this.bonificacionAplicada = bonificacion;
        Double montoBase = tarifa.getMonto();
        if (bonificacion != null) {
            Double descuento = bonificacion.obtenerDescuento(bonificacion);
            this.costo = montoBase - (montoBase * descuento / 100);
        } else {
            this.costo = montoBase;
        }
        return this.costo;
    }

}