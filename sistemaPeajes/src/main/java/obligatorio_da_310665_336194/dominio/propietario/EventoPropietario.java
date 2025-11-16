package obligatorio_da_310665_336194.dominio.propietario;

public class EventoPropietario {
    private Propietario propietario;
    private Propietario.EventosPropietario tipo;

    public EventoPropietario(Propietario propietario, Propietario.EventosPropietario tipo) {
        this.propietario = propietario;
        this.tipo = tipo;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public Propietario.EventosPropietario getTipo() {
        return tipo;
    }
}
