package obligatorio_da_310665_336194;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import obligatorio_da_310665_336194.dominio.bonificacion.Bonificacion;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.puesto.Puesto;
import obligatorio_da_310665_336194.dominio.puesto.Tarifa;
import obligatorio_da_310665_336194.dominio.usuario.Administrador;
import obligatorio_da_310665_336194.dominio.vehiculo.CategoríaVehiculo;
import obligatorio_da_310665_336194.dominio.vehiculo.Vehiculo;
import obligatorio_da_310665_336194.servicios.fachada.Fachada;
import obligatorio_da_310665_336194.dominio.usuario.Usuario;

@SpringBootApplication
public class SistemaPeajesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaPeajesApplication.class, args);
		cargarDatosDePrueba();
		System.out.println("\n=======================================================");
		System.out.println("   Aplicación iniciada correctamente");
		System.out.println("   Accede a: http://localhost:8080/index.html");
		System.out.println("=======================================================\n");
	}

	private static void cargarDatosDePrueba() {
		System.out.println("Cargando datos de prueba...");

		// ========== ADMINISTRADORES ==========
		// Administrador obligatorio según letra
		Administrador admin1 = new Administrador("12345678", "Usuario Administrador");
		admin1.setPassword("admin.123");

		// Segundo administrador
		Administrador admin2 = new Administrador("21234567", "Laura Fernández");
		admin2.setPassword("admin.456");

		Administrador admin3 = new Administrador("123", "Laura Fernández");
		admin3.setPassword("123");

		Fachada.getInstancia().agregar((Usuario) admin1);
		Fachada.getInstancia().agregar((Usuario) admin2);
		Fachada.getInstancia().agregar((Usuario) admin3);
		System.out.println("✓ Administradores cargados: 3");

		// ========== BONIFICACIONES ==========
		// 3 bonificaciones definidas en la letra
		Bonificacion bonifExonerado = new Bonificacion("Exonerado");
		Bonificacion bonifFrecuente = new Bonificacion("Frecuente");
		Bonificacion bonifTrabajador = new Bonificacion("Trabajador");

		Fachada.getInstancia().agregar(bonifExonerado);
		Fachada.getInstancia().agregar(bonifFrecuente);
		Fachada.getInstancia().agregar(bonifTrabajador);
		System.out.println("✓ Bonificaciones cargadas: 3 (Exonerado, Frecuente, Trabajador)");

		// ========== CATEGORÍAS DE VEHÍCULOS ==========
		CategoríaVehiculo catAutomovil = new CategoríaVehiculo("Automóvil");
		CategoríaVehiculo catCamioneta = new CategoríaVehiculo("Camioneta");
		CategoríaVehiculo catMoto = new CategoríaVehiculo("Moto");
		CategoríaVehiculo catCamion = new CategoríaVehiculo("Camión");
		System.out.println("✓ Categorías de vehículos: 4 (Automóvil, Camioneta, Moto, Camión)");

		// ========== PUESTOS Y TARIFAS ==========
		Puesto puesto1 = new Puesto("Puesto Montevideo Norte", "Ruta 5 km 25", 0.0);
		Puesto puesto2 = new Puesto("Puesto Canelones Este", "Ruta 8 km 30", 0.0);
		Puesto puesto3 = new Puesto("Puesto Maldonado Sur", "Ruta 9 km 112", 0.0);

		// Tarifas para Puesto 1
		Tarifa tarifa1_1 = new Tarifa(puesto1, catAutomovil, 100.0);
		Tarifa tarifa1_2 = new Tarifa(puesto1, catCamioneta, 150.0);
		Tarifa tarifa1_3 = new Tarifa(puesto1, catMoto, 50.0);
		Tarifa tarifa1_4 = new Tarifa(puesto1, catCamion, 300.0);

		puesto1.agregarTarifa(tarifa1_1);
		puesto1.agregarTarifa(tarifa1_2);
		puesto1.agregarTarifa(tarifa1_3);
		puesto1.agregarTarifa(tarifa1_4);

		// Tarifas para Puesto 2
		Tarifa tarifa2_1 = new Tarifa(puesto2, catAutomovil, 80.0);
		Tarifa tarifa2_2 = new Tarifa(puesto2, catCamioneta, 120.0);
		Tarifa tarifa2_3 = new Tarifa(puesto2, catMoto, 40.0);
		Tarifa tarifa2_4 = new Tarifa(puesto2, catCamion, 250.0);

		puesto2.agregarTarifa(tarifa2_1);
		puesto2.agregarTarifa(tarifa2_2);
		puesto2.agregarTarifa(tarifa2_3);
		puesto2.agregarTarifa(tarifa2_4);

		// Tarifas para Puesto 3
		Tarifa tarifa3_1 = new Tarifa(puesto3, catAutomovil, 120.0);
		Tarifa tarifa3_2 = new Tarifa(puesto3, catCamioneta, 180.0);
		Tarifa tarifa3_3 = new Tarifa(puesto3, catMoto, 60.0);
		Tarifa tarifa3_4 = new Tarifa(puesto3, catCamion, 350.0);

		puesto3.agregarTarifa(tarifa3_1);
		puesto3.agregarTarifa(tarifa3_2);
		puesto3.agregarTarifa(tarifa3_3);
		puesto3.agregarTarifa(tarifa3_4);

		Fachada.getInstancia().agregar(puesto1);
		Fachada.getInstancia().agregar(puesto2);
		Fachada.getInstancia().agregar(puesto3);
		System.out.println("✓ Puestos cargados: 3 (cada uno con 4 tarifas)");

		// ========== ESTADOS DE PROPIETARIOS ==========
		// Los estados se crean automáticamente en el sistema
		// Habilitado, Deshabilitado, Suspendido, Penalizado
		System.out.println("✓ Estados de propietarios: 4 (Habilitado, Deshabilitado, Suspendido, Penalizado)");

		// ========== PROPIETARIOS ==========
		// Propietario obligatorio según letra
		Propietario prop1 = new Propietario("23456789", "Usuario Propietario");
		prop1.setPassword("prop.123");
		prop1.setSaldoActual(2000.0);
		prop1.setSaldoMinimoAlerta(500.0);
		// Queda Habilitado por defecto

		// Segundo propietario
		Propietario prop2 = new Propietario("34567890", "María González");
		prop2.setPassword("prop.456");
		prop2.setSaldoActual(1500.0);
		prop2.setSaldoMinimoAlerta(300.0);
		// Queda Habilitado por defecto

		Propietario prop3 = new Propietario("321", "María González");
		prop3.setPassword("321");
		prop3.setSaldoActual(1500.0);
		prop3.setSaldoMinimoAlerta(300.0);
		// Queda Habilitado por defecto

		// Propietarios adicionales para probar diferentes estados
		Propietario prop4 = new Propietario("45678901", "Carlos Rodríguez");
		prop4.setPassword("prop.789");
		prop4.setSaldoActual(3000.0);
		prop4.setSaldoMinimoAlerta(800.0);

		Propietario prop5 = new Propietario("56789012", "Ana Martínez");
		prop5.setPassword("prop.012");
		prop5.setSaldoActual(500.0);
		prop5.setSaldoMinimoAlerta(200.0);

		Fachada.getInstancia().agregar(prop1);
		Fachada.getInstancia().agregar(prop2);
		Fachada.getInstancia().agregar(prop3);
		Fachada.getInstancia().agregar(prop4);
		Fachada.getInstancia().agregar(prop5);

		// Registrar propietarios como usuarios para login
		Fachada.getInstancia().agregar((Usuario) prop1);
		Fachada.getInstancia().agregar((Usuario) prop2);
		Fachada.getInstancia().agregar((Usuario) prop3);
		Fachada.getInstancia().agregar((Usuario) prop4);
		Fachada.getInstancia().agregar((Usuario) prop5);
		System.out.println("✓ Propietarios cargados: 5 (todos con saldo y saldo mínimo para alerta)");

		// ========== VEHÍCULOS ==========
		// Vehículos asociados a los propietarios
		Vehiculo vehiculo1 = new Vehiculo("ABC1234", "Toyota Hilux", prop1, "Blanco", catCamioneta);
		Vehiculo vehiculo2 = new Vehiculo("DEF5678", "Honda Civic", prop2, "Azul", catAutomovil);
		Vehiculo vehiculo3 = new Vehiculo("GHI9012", "Yamaha FZ", prop3, "Negro", catMoto);
		Vehiculo vehiculo4 = new Vehiculo("JKL3456", "Ford Ranger", prop4, "Rojo", catCamioneta);
		Vehiculo vehiculo5 = new Vehiculo("MNO7890", "Chevrolet Onix", prop1, "Gris", catAutomovil);
		Vehiculo vehiculo6 = new Vehiculo("PQR1234", "Mercedes-Benz Actros", prop3, "Blanco", catCamion);

		Fachada.getInstancia().agregar(vehiculo1);
		Fachada.getInstancia().agregar(vehiculo2);
		Fachada.getInstancia().agregar(vehiculo3);
		Fachada.getInstancia().agregar(vehiculo4);
		Fachada.getInstancia().agregar(vehiculo5);
		Fachada.getInstancia().agregar(vehiculo6);
		System.out.println("✓ Vehículos cargados: 6 (con matrícula, modelo, color y categoría)");

		System.out.println("\n=== Datos de prueba cargados correctamente ===");
		System.out.println("Usuario Administrador: 12345678 / admin.123");
		System.out.println("Usuario Propietario: 23456789 / prop.123 (Saldo: $2000)");
	}
}
