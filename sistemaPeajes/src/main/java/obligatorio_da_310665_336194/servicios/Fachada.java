package obligatorio_da_310665_336194.servicios;

import java.util.List;
import obligatorio_da_310665_336194.dominio.Puesto;
import obligatorio_da_310665_336194.dominio.Tarifa;
import obligatorio_da_310665_336194.dominio.Vehiculo;
import obligatorio_da_310665_336194.dominio.Propietario;
import obligatorio_da_310665_336194.dominio.AsignacionDeBonificacion;
import obligatorio_da_310665_336194.dominio.Bonificacion;
import obligatorio_da_310665_336194.dominio.Transito;
import java.util.Date;
import obligatorio_da_310665_336194.dominio.TipoBonificacion;
import obligatorio_da_310665_336194.dominio.EstadoPropietario;
import obligatorio_da_310665_336194.dominio.Notificacion;

public class Fachada {

	private ServicioNotificaciones servicioNotificaciones;

	private ServicioVehiculos servicioVehiculos;

	private ServicioPropietarios servicioPropietarios;

	private ServicioPuestos servicioPuestos;

	private ServicioBonificaciones servicioBonificaciones;

	private ServicioTransitos servicioTransitos;

	/**
	 *  
	 *  
	 */
	private void Fachada() {

	}

	public static Fachada getInstancia() {
		return null;
	}

	public List<Puesto> listarPuestos() {
		return null;
	}

	public List<Tarifa> tarifasDePuesto(Puesto puesto) {
		return null;
	}

	public Vehiculo getVehiculo(String matricula) {
		return null;
	}

	public Propietario getPropietario(String matricula) {
		return null;
	}

	public Boolean puedeTransitar(Propietario propietario) {
		return null;
	}

	/**
	 *  
	 */
	public void enviarNotificacion(Propietario propietario, String mensaje) {

	}

	/**
	 *  
	 */
	public void enviarSaldoBajo(Propietario propietario) {

	}

	public AsignacionDeBonificacion obtenerBonificacion(Puesto puesto, Propietario propietario) {
		return null;
	}

	public Transito emularTransito(Puesto puesto, String matricula, Date fechaHora) {
		return null;
	}

	public List<AsignacionDeBonificacion> obtenerBonificacionesPropietario(Propietario propietario) {
		return null;
	}

	public List<Transito> getTransitosPropietario(Propietario propietario) {
		return null;
	}

	public List<Vehiculo> getVehiculosPropietario(Propietario propietario) {
		return null;
	}

	public Propietario getPropietario() {
		return null;
	}

	public List<Notificacion> getNotificacionesPropietario(Propietario propietario) {
		return null;
	}

	/**
	 *  
	 */
	public void borrarNotificaciones(Propietario propietario) {

	}

	public List<Bonificacion> getBonificaciones() {
		return null;
	}

	public Propietario buscarPropietarioPorCedula(String cedula) {
		return null;
	}

	public AsignacionDeBonificacion asignarBonificacion(Propietario propietario, Puesto puesto,
			TipoBonificacion tipoBonificacion) {
		return null;
	}

	public List<EstadoPropietario> getEstados() {
		return null;
	}

	public Propietario cambiarEstado(String cedula, EstadoPropietario nuevoEstado) {
		return null;
	}

}
