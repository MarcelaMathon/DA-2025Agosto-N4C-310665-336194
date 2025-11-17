package obligatorio_da_310665_336194.dtos;

import java.util.Date;
import obligatorio_da_310665_336194.dominio.notificacion.Notificacion;

public class NotificacionTableroDTO {
    
    private String mensaje;
    private Date fechaHora;
    
    public NotificacionTableroDTO() {}
    
    public NotificacionTableroDTO(String mensaje, Date fechaHora) {
        this.mensaje = mensaje;
        this.fechaHora = fechaHora;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public Date getFechaHora() {
        return fechaHora;
    }
    
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public static NotificacionTableroDTO desde(Notificacion notificacion) {
        return new NotificacionTableroDTO(
                notificacion.getMensaje(),
                notificacion.getFechaHora()
        );
    }
}