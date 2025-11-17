package obligatorio_da_310665_336194.dtos;

import java.util.Date;
import obligatorio_da_310665_336194.dominio.bonificacion.AsignacionDeBonificacion;

public class BonificacionTableroDTO {
    
    private String bonificacionNombre;
    private String puestoNombre;
    private Date fechaAsignacion;
    
    public BonificacionTableroDTO() {}
    
    public BonificacionTableroDTO(String bonificacionNombre, String puestoNombre, Date fechaAsignacion) {
        this.bonificacionNombre = bonificacionNombre;
        this.puestoNombre = puestoNombre;
        this.fechaAsignacion = fechaAsignacion;
    }
    
    public String getBonificacionNombre() {
        return bonificacionNombre;
    }
    
    public void setBonificacionNombre(String bonificacionNombre) {
        this.bonificacionNombre = bonificacionNombre;
    }
    
    public String getPuestoNombre() {
        return puestoNombre;
    }
    
    public void setPuestoNombre(String puestoNombre) {
        this.puestoNombre = puestoNombre;
    }
    
    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }
    
    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }
    
    public static BonificacionTableroDTO desde(AsignacionDeBonificacion bonificacion) {
        return new BonificacionTableroDTO(
                bonificacion.getNombreBonificacion(),
                bonificacion.getNombrePuesto(),
                bonificacion.getFechaHora()
        );
    }
}