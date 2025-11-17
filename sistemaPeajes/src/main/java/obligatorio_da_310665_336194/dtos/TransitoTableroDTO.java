package obligatorio_da_310665_336194.dtos;

import java.util.Date;
import obligatorio_da_310665_336194.dominio.transito.Transito;

public class TransitoTableroDTO {
    
    private String puestoNombre;
    private String vehiculoMatricula;
    private String vehiculoCategoria;
    private Double montoTarifa;
    private String bonificacionNombre;
    private Double montoBonificacion;
    private Double montoPagado;
    private Date fechaHora;
    
    public TransitoTableroDTO() {}
    
    public TransitoTableroDTO(String puestoNombre, String vehiculoMatricula, String vehiculoCategoria,
                             Double montoTarifa, String bonificacionNombre, Double montoBonificacion,
                             Double montoPagado, Date fechaHora) {
        this.puestoNombre = puestoNombre;
        this.vehiculoMatricula = vehiculoMatricula;
        this.vehiculoCategoria = vehiculoCategoria;
        this.montoTarifa = montoTarifa;
        this.bonificacionNombre = bonificacionNombre;
        this.montoBonificacion = montoBonificacion;
        this.montoPagado = montoPagado;
        this.fechaHora = fechaHora;
    }
    
    public String getPuestoNombre() {
        return puestoNombre;
    }
    
    public void setPuestoNombre(String puestoNombre) {
        this.puestoNombre = puestoNombre;
    }
    
    public String getVehiculoMatricula() {
        return vehiculoMatricula;
    }
    
    public void setVehiculoMatricula(String vehiculoMatricula) {
        this.vehiculoMatricula = vehiculoMatricula;
    }
    
    public String getVehiculoCategoria() {
        return vehiculoCategoria;
    }
    
    public void setVehiculoCategoria(String vehiculoCategoria) {
        this.vehiculoCategoria = vehiculoCategoria;
    }
    
    public Double getMontoTarifa() {
        return montoTarifa;
    }
    
    public void setMontoTarifa(Double montoTarifa) {
        this.montoTarifa = montoTarifa;
    }
    
    public String getBonificacionNombre() {
        return bonificacionNombre;
    }
    
    public void setBonificacionNombre(String bonificacionNombre) {
        this.bonificacionNombre = bonificacionNombre;
    }
    
    public Double getMontoBonificacion() {
        return montoBonificacion;
    }
    
    public void setMontoBonificacion(Double montoBonificacion) {
        this.montoBonificacion = montoBonificacion;
    }
    
    public Double getMontoPagado() {
        return montoPagado;
    }
    
    public void setMontoPagado(Double montoPagado) {
        this.montoPagado = montoPagado;
    }
    
    public Date getFechaHora() {
        return fechaHora;
    }
    
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public static TransitoTableroDTO desde(Transito transito) {
        Double montoTarifa = transito.getTarifaAplicada() != null ? transito.getTarifaAplicada().getMonto() : 0.0;
        String bonificacionNombre = transito.getBonificacionAplicada() != null ? 
                transito.getBonificacionAplicada().getNombreBonificacion() : "Sin bonificación";
        Double montoBonificacion = montoTarifa - (transito.getCosto() != null ? transito.getCosto() : 0.0);
        String vehiculoCategoria = transito.getVehiculo() != null && transito.getVehiculo().getCategoria() != null ? 
                transito.getVehiculo().getCategoria().getNombre() : "-";
        
        return new TransitoTableroDTO(
                transito.getPuesto() != null ? transito.getPuesto().getNombre() : "-",
                transito.getVehiculo() != null ? transito.getVehiculo().getMatricula() : "-",
                vehiculoCategoria,
                montoTarifa,
                bonificacionNombre,
                montoBonificacion,
                transito.getCosto() != null ? transito.getCosto() : 0.0,
                transito.getFechaHora()
        );
    }
}