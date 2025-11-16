package obligatorio_da_310665_336194.dominio;

public interface Observable {
 
    void agregar(Observador observador);
    
    void remover(Observador observador);
}