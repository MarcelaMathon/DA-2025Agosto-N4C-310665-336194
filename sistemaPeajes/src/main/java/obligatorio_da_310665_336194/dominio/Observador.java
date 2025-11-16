package obligatorio_da_310665_336194.dominio;

/**
 * Interfaz Observador genérica del patrón Observer.
 * Define el contrato base para todos los observadores del sistema.
 * Permite escalabilidad para diferentes tipos de observadores.
 * 
 * @param <T> Tipo del objeto observable
 */
public interface Observador<T> {

    void actualizar(T observable, Object evento);

}
