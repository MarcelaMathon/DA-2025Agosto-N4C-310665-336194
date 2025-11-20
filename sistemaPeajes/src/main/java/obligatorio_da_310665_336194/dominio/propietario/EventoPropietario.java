package obligatorio_da_310665_336194.dominio.propietario;

import obligatorio_da_310665_336194.dominio.transito.Transito;

public class EventoPropietario {
    private Propietario propietario;
    private Propietario.EventosPropietario tipo;
    private Transito transito;

    public EventoPropietario(Propietario propietario, Propietario.EventosPropietario tipo) {
        this.propietario = propietario;
        this.tipo = tipo;
        this.transito = null;
    }

    public EventoPropietario(Propietario propietario, Propietario.EventosPropietario tipo, Transito transito) {
        this.propietario = propietario;
        this.tipo = tipo;
        this.transito = transito;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public Propietario.EventosPropietario getTipo() {
        return tipo;
    }

    public Transito getTransito() {
        return transito;
    }
}
