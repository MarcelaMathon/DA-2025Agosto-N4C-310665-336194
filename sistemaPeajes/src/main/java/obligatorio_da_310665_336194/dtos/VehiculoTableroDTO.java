package obligatorio_da_310665_336194.dtos;

import obligatorio_da_310665_336194.dominio.vehiculo.Vehiculo;

public class VehiculoTableroDTO {
    
    private String matricula;
    private String modelo;
    private String color;

    private int cantidadTransitos;
    private Double montoTotalGastado;
    
    public VehiculoTableroDTO() {}
    
    public VehiculoTableroDTO(String matricula, String modelo, String color, 
                              int cantidadTransitos, Double montoTotalGastado) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.color = color;
        
        this.cantidadTransitos = cantidadTransitos;
        this.montoTotalGastado = montoTotalGastado;
    }
    
    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public String getModelo() {
        return modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public int getCantidadTransitos() {
        return cantidadTransitos;
    }
    
    public void setCantidadTransitos(int cantidadTransitos) {
        this.cantidadTransitos = cantidadTransitos;
    }
    
    public Double getMontoTotalGastado() {
        return montoTotalGastado;
    }
    
    public void setMontoTotalGastado(Double montoTotalGastado) {
        this.montoTotalGastado = montoTotalGastado;
    }
    
    public static VehiculoTableroDTO desde(Vehiculo vehiculo) {
        return new VehiculoTableroDTO(
                vehiculo.getMatricula(),
                vehiculo.getModelo(),
                vehiculo.getColor(),
                vehiculo.getCantidadTransitos(),
                vehiculo.getMontoTotalGastado()
        );
    }
}