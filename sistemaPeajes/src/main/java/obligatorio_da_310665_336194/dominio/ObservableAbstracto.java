package obligatorio_da_310665_336194.dominio;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ObservableAbstracto implements Observable {
    private final Collection<Observador> observadores = new ArrayList<>();

    public void agregar(Observador o) {
        observadores.add(o);
    }

    public void remover(Observador o) {
        observadores.remove(o);
    }

    protected void avisar(Object evento) {
        for (Observador o : new ArrayList<>(observadores)) {
            o.actualizar(this, evento);
        }
    }
}