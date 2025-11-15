package obligatorio_da_310665_336194.servicios.fachada;

import java.util.List;

import obligatorio_da_310665_336194.dominio.bonificacion.AsignacionDeBonificacion;
import obligatorio_da_310665_336194.dominio.bonificacion.Bonificacion;
import obligatorio_da_310665_336194.dominio.bonificacion.TipoBonificacion;
import obligatorio_da_310665_336194.dominio.notificacion.Notificacion;
import obligatorio_da_310665_336194.dominio.propietario.EstadoPropietario;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.puesto.Puesto;
import obligatorio_da_310665_336194.dominio.puesto.Tarifa;
import obligatorio_da_310665_336194.dominio.transito.Transito;
import obligatorio_da_310665_336194.dominio.vehiculo.Vehiculo;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;

import java.util.Date;

import obligatorio_da_310665_336194.servicios.ServicioBonificaciones;
import obligatorio_da_310665_336194.servicios.ServicioNotificaciones;
import obligatorio_da_310665_336194.servicios.ServicioPropietarios;
import obligatorio_da_310665_336194.servicios.ServicioPuestos;
import obligatorio_da_310665_336194.servicios.ServicioTransitos;
import obligatorio_da_310665_336194.servicios.ServicioVehiculos;

public class Fachada {

	private static Fachada instancia;

	private ServicioNotificaciones servicioNotificaciones;

	private ServicioVehiculos servicioVehiculos;

	private ServicioPropietarios servicioPropietarios;

	private ServicioPuestos servicioPuestos;

	private ServicioBonificaciones servicioBonificaciones;

	private ServicioTransitos servicioTransitos;

	private Fachada() {
		this.servicioNotificaciones = new ServicioNotificaciones();
		this.servicioVehiculos = new ServicioVehiculos();
		this.servicioPropietarios = new ServicioPropietarios();
		this.servicioPuestos = new ServicioPuestos();
		this.servicioBonificaciones = new ServicioBonificaciones();
		this.servicioTransitos = new ServicioTransitos();
	}

	public static Fachada getInstancia() {
		if (instancia == null) {
			instancia = new Fachada();
		}
		return instancia;
	}

	public List<Puesto> listarPuestos() {
		return servicioPuestos.listarPuestos();
	}

	public List<Tarifa> tarifasDePuesto(Puesto puesto) {
		return servicioPuestos.tarifasDePuesto(puesto);
	}

	public Vehiculo getVehiculo(String matricula) {
		return servicioVehiculos.getVehiculo(matricula);
	}

	public Propietario getPropietario(String matricula) {
		return servicioVehiculos.getPropietario(matricula);
	}

	public Boolean puedeTransitar(Propietario propietario) {
		return servicioPropietarios.puedeTransitar(propietario);
	}

	public void enviarNotificacion(Propietario propietario, String mensaje) {
		servicioNotificaciones.enviarNotificacion(propietario, mensaje);
	}

	public void enviarSaldoBajo(Propietario propietario) {
		servicioNotificaciones.enviarSaldoBajo(propietario);
	}

	public AsignacionDeBonificacion obtenerBonificacion(Puesto puesto, Propietario propietario) {
		return servicioBonificaciones.obtenerBonificacion(puesto, propietario);
	}

	public Transito emularTransito(Puesto puesto, String matricula, Date fechaHora) throws PeajesExceptions {
		return servicioTransitos.emularTransito(puesto, matricula, fechaHora);
	}

	public List<AsignacionDeBonificacion> obtenerBonificacionesPropietario(Propietario propietario) {
		return servicioBonificaciones.obtenerBonificacionesPropietario(propietario);
	}

	public List<Transito> getTransitosPropietario(Propietario propietario) {
		return servicioTransitos.getTransitosPropietario(propietario);
	}

	public List<Vehiculo> getVehiculosPropietario(Propietario propietario) {
		return servicioVehiculos.getVehiculosPropietario(propietario);
	}

	public List<Notificacion> getNotificacionesPropietario(Propietario propietario) {
		return servicioNotificaciones.getNotificacionesPropietario(propietario);
	}

	public void borrarNotificaciones(Propietario propietario) {
		servicioNotificaciones.borrarNotificaciones(propietario);
	}

	public List<Bonificacion> getBonificaciones() {
		return servicioBonificaciones.getBonificaciones();
	}

	public Propietario buscarPropietarioPorCedula(String cedula) {
		return servicioPropietarios.buscarPropietarioPorCedula(cedula);
	}

	public Propietario buscarPropietarioPorCedulaConValidacion(String cedula) throws PeajesExceptions {
		return servicioPropietarios.buscarPropietarioPorCedulaConValidacion(cedula);
	}

	public AsignacionDeBonificacion asignarBonificacion(Propietario propietario, Puesto puesto,
			TipoBonificacion tipoBonificacion) throws PeajesExceptions {
		return servicioBonificaciones.asignarBonificacion(propietario, puesto, tipoBonificacion);
	}

	public List<EstadoPropietario> getEstados() {
		return servicioPropietarios.getEstados();
	}

	public Propietario cambiarEstado(String cedula, EstadoPropietario nuevoEstado) {
		return servicioPropietarios.cambiarEstado(cedula, nuevoEstado);
	}

	public void agregar(Propietario propietario) {
		servicioPropietarios.agregar(propietario);
	}

	public void agregar(Puesto puesto) {
		servicioPuestos.agregar(puesto);
	}

	public void agregar(Vehiculo vehiculo) {
		servicioVehiculos.agregar(vehiculo);
	}

	public void agregar(Bonificacion bonificacion) {
		servicioBonificaciones.agregar(bonificacion);
	}

}
