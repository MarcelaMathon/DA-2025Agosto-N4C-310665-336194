package obligatorio_da_310665_336194;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import obligatorio_da_310665_336194.dominio.bonificacion.Bonificacion;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.puesto.Puesto;
import obligatorio_da_310665_336194.dominio.puesto.Tarifa;
import obligatorio_da_310665_336194.dominio.vehiculo.CategoríaVehiculo;
import obligatorio_da_310665_336194.dominio.vehiculo.Vehiculo;
import obligatorio_da_310665_336194.servicios.fachada.Fachada;

@SpringBootApplication
public class SistemaPeajesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaPeajesApplication.class, args);
		cargarDatosDePrueba();
		System.out.println("\n=======================================================");
		System.out.println("   Aplicación iniciada correctamente");
		System.out.println("   Accede a: http://localhost:8080/asignarBonificacion.html");
		System.out.println("   Accede a: http://localhost:8080/emularTransito.html");
		System.out.println("   Accede a: http://localhost:8080/cambiarEstado.html");
		System.out.println("=======================================================\n");
	}

	private static void cargarDatosDePrueba() {
		// Crear bonificaciones
		Bonificacion bonifExonerado = new Bonificacion("Exonerado");
		Bonificacion bonifFrecuente = new Bonificacion("Frecuente");
		Bonificacion bonifTrabajador = new Bonificacion("Trabajador");

		Fachada.getInstancia().agregar(bonifExonerado);
		Fachada.getInstancia().agregar(bonifFrecuente);
		Fachada.getInstancia().agregar(bonifTrabajador);

		// Crear puestos
		Puesto puesto1 = new Puesto("Puesto Montevideo Norte", "Ruta 5 km 25", 150.0);
		Puesto puesto2 = new Puesto("Puesto Canelones Este", "Ruta 8 km 30", 120.0);
		Puesto puesto3 = new Puesto("Puesto Maldonado Sur", "Ruta 9 km 15", 180.0);

		Fachada.getInstancia().agregar(puesto1);
		Fachada.getInstancia().agregar(puesto2);
		Fachada.getInstancia().agregar(puesto3);

		Tarifa tarifa1 = new Tarifa(puesto1, new CategoríaVehiculo("Auto"), 50.0);
		Tarifa tarifa2 = new Tarifa(puesto1, new CategoríaVehiculo("Camioneta"), 80.0);
		Tarifa tarifa3 = new Tarifa(puesto1, new CategoríaVehiculo("Moto"), 30.0);
		Tarifa tarifa4 = new Tarifa(puesto2, new CategoríaVehiculo("Auto"), 40.0);
		Tarifa tarifa5 = new Tarifa(puesto2, new CategoríaVehiculo("Camioneta"), 20.0);
		Tarifa tarifa6 = new Tarifa(puesto2, new CategoríaVehiculo("Moto"), 20.0);

		puesto1.agregarTarifa(tarifa1);
		puesto1.agregarTarifa(tarifa2);
		puesto1.agregarTarifa(tarifa3);

		puesto2.agregarTarifa(tarifa4);
		puesto2.agregarTarifa(tarifa5);
		puesto2.agregarTarifa(tarifa6);

		// Crear propietarios con diferentes estados
		Propietario prop1 = new Propietario("12345678", "Juan Pérez");
		Propietario prop2 = new Propietario("23456789", "María González");
		Propietario prop3 = new Propietario("34567890", "Carlos Rodríguez");
		Propietario prop4 = new Propietario("45678901", "Ana Martínez");

		// prop1 y prop2 quedan habilitados por defecto
		Fachada.getInstancia().agregar(prop1);
		Fachada.getInstancia().agregar(prop2);
		Fachada.getInstancia().agregar(prop3);
		Fachada.getInstancia().agregar(prop4);

		CategoríaVehiculo categoría1 = new CategoríaVehiculo("Camioneta");
		CategoríaVehiculo categoría2 = new CategoríaVehiculo("Auto");
		CategoríaVehiculo categoría3 = new CategoríaVehiculo("Moto");

		// Crear vehículos
		Vehiculo vehiculo1 = new Vehiculo("ABC1234", "Toyota", prop1, "Rojo", categoría1);
		Vehiculo vehiculo2 = new Vehiculo("DEF5678", "Honda", prop2, "Azul", categoría2);
		Vehiculo vehiculo3 = new Vehiculo("GHI9012", "Yamaha", prop3, "Negro", categoría3);
		Vehiculo vehiculo4 = new Vehiculo("JKL3456", "Ford", prop4, "Blanco", categoría1);

		Fachada.getInstancia().agregar(vehiculo1);
		Fachada.getInstancia().agregar(vehiculo2);
		Fachada.getInstancia().agregar(vehiculo3);
		Fachada.getInstancia().agregar(vehiculo4);

		System.out.println("\n✓ Datos de prueba cargados:");
		System.out.println("  - 3 bonificaciones (Exonerado, Frecuente, Trabajador)");
		System.out.println("  - 3 puestos de peaje");
		System.out.println("  - 4 propietarios habilitados (cédulas: 12345678, 23456789, 34567890, 45678901)");
		System.out.println("  - 4 vehículos\n");
	}
}
