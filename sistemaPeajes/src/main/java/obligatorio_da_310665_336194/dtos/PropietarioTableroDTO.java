package obligatorio_da_310665_336194.dtos;

public class PropietarioTableroDTO {
    
    private String nombre;
    private String estadoActual;
    private Double saldoActual;
    
    public PropietarioTableroDTO() {}
    
    public PropietarioTableroDTO(String nombre, String estadoActual, 
                                Double saldoActual) {
        this.nombre = nombre;
        this.estadoActual = estadoActual;
        this.saldoActual = saldoActual;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    public String getEstadoActual() {
        return estadoActual;
    }
    
    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }
    
    public Double getSaldoActual() {
        return saldoActual;
    }
    
    public void setSaldoActual(Double saldoActual) {
        this.saldoActual = saldoActual;
    }
 
}