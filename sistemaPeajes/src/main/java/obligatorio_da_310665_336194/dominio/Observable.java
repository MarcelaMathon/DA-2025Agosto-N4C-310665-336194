package obligatorio_da_310665_336194.dominio;

/**
 * Interfaz Observable del patrón Observer.
 * Define el contrato para objetos que pueden ser observados.
 * Permite escalabilidad para agregar más observables en el futuro.
 * 
 * @param <T> Tipo del observador que puede suscribirse
 */
public interface Observable<T extends Observador<?>> {

    /**
     * Agrega un observador a la lista de observadores.
     * 
     * @param observador El observador a agregar
     */
    void agregarObservador(T observador);

    /**
     * Remueve un observador de la lista de observadores.
     * 
     * @param observador El observador a remover
     */
    void removerObservador(T observador);

}
