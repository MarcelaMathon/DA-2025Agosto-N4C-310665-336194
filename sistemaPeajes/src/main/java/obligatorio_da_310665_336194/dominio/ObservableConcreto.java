package obligatorio_da_310665_336194.dominio;

public class ObservableConcreto extends ObservableAbstracto {

    public void notificarObservadores(Object evento) {
        avisar(evento);
    }
}