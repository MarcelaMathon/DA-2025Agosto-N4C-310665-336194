package obligatorio_da_310665_336194.dominio.propietario;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import obligatorio_da_310665_336194.dominio.bonificacion.AsignacionDeBonificacion;
import obligatorio_da_310665_336194.dominio.notificacion.Notificacion;
import obligatorio_da_310665_336194.dominio.observador.Observable;
import obligatorio_da_310665_336194.dominio.observador.ObservableConcreto;
import obligatorio_da_310665_336194.dominio.observador.Observador;
import obligatorio_da_310665_336194.dominio.transito.Transito;
import obligatorio_da_310665_336194.dominio.usuario.Usuario;
import obligatorio_da_310665_336194.dominio.vehiculo.Vehiculo;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;

public class Propietario extends Usuario implements Observable {

	@Getter
	private Double saldoActual;
	@Getter
	private Double saldoMinimoAlerta;
	@Getter
	private String cedula;
	@Getter
	private String nombre;
	@Getter
	private EstadoPropietario estadoActual;
	@Getter
	private List<AsignacionDeBonificacion> asignacionesDeBonificacion;
	@Getter
	private List<Notificacion> notificaciones;
	@Getter
	private List<Transito> transitos;
	@Getter
	private List<Vehiculo> vehículos;

	private ObservableConcreto observableConcreto;

	public Propietario(String cedula, String nombre) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.saldoActual = 1000.0;
		this.saldoMinimoAlerta = 100.0;
		this.estadoActual = EstadoPropietario.habilitado();
		this.asignacionesDeBonificacion = new ArrayList<>();
		this.notificaciones = new ArrayList<>();
		this.transitos = new ArrayList<>();
		this.vehículos = new ArrayList<>();
		this.observableConcreto = new ObservableConcreto();
	}

	public enum EventosPropietario {
		TRANSITO_REALIZADO,
		SALDO_BAJO,
		ESTADO_CAMBIADO,
		BONIFICACION_ASIGNADA
	}

	public Boolean puedeIngresar() {
		return this.estadoActual.puedeIngresar();
	}

	public boolean puedeTransitar() {
		return this.estadoActual.puedeTransitar();
	}

	public String getNombreEstado() {
		return this.estadoActual.getNombreEstado();
	}

	public Boolean recibeNotificaciones() {
		return this.estadoActual.recibeNotificaciones();
	}

	public Boolean aplicaBonificaciones() {
		return this.estadoActual.aplicaBonificaciones();
	}

	public EstadoPropietario cambiarEstado(EstadoPropietario nuevoEstado) throws PeajesExceptions {
		if (nuevoEstado.equals(this.estadoActual)) {
			throw new PeajesExceptions("El nuevo estado debe ser diferente al actual");
		}
		this.estadoActual = nuevoEstado;

		// Notificar a los observadores sobre el cambio de estado
		observableConcreto.notificarObservadores(new EventoPropietario(this, EventosPropietario.ESTADO_CAMBIADO));

		return this.estadoActual;
	}

	public void descontarSaldo(Double monto) {
		this.saldoActual -= monto;
	}

	public boolean tieneSaldoBajo() {
		boolean saldoBajo = this.saldoActual < this.saldoMinimoAlerta;
		if (saldoBajo) {
			observableConcreto.notificarObservadores(new EventoPropietario(this, EventosPropietario.SALDO_BAJO));
		}
		return saldoBajo;
	}

	public void validarPuedeTransitar() throws PeajesExceptions {
		String estado = this.getNombreEstado();

		if ("Deshabilitado".equals(estado)) {
			throw new PeajesExceptions("El propietario del vehículo está deshabilitado, no puede realizar tránsitos");
		}

		if ("Suspendido".equals(estado)) {
			throw new PeajesExceptions("El propietario del vehículo está suspendido, no puede realizar tránsitos");
		}
	}

	public void validarSaldoSuficiente(Double monto) throws PeajesExceptions {
		if (this.saldoActual < monto) {
			throw new PeajesExceptions("Saldo insuficiente: " + this.saldoActual);
		}
	}

	public boolean esPenalizado() {
		return "Penalizado".equals(this.getNombreEstado());
	}

	public void validarPuedeRecibirBonificacion() throws PeajesExceptions {
		if ("Deshabilitado".equals(this.getNombreEstado())) {
			throw new PeajesExceptions("El propietario esta deshabilitado. No se pueden asignar bonificaciones");
		}
	}

	public boolean estaDeshabilitado() {
		return "Deshabilitado".equals(this.getNombreEstado());
	}

	@Override
	public void agregar(Observador observador) {
		observableConcreto.agregar(observador);
	}

	@Override
	public void remover(Observador observador) {
		observableConcreto.remover(observador);
	}

	public void notificarTransito(String mensaje) {
		observableConcreto.notificarObservadores(new EventoPropietario(this, EventosPropietario.TRANSITO_REALIZADO));
	}

	public void notificarBonificacion() {
		observableConcreto.notificarObservadores(new EventoPropietario(this, EventosPropietario.BONIFICACION_ASIGNADA));
	}

}
