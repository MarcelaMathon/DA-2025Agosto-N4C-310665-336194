package obligatorio_da_310665_336194.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

	@ExceptionHandler(PeajesExceptions.class)
	public ResponseEntity<String> manejarPeajesException(PeajesExceptions ex) {
		return ResponseEntity.status(299).body(ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> manejarExcepcionGeneral(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Error interno: " + ex.getMessage());
	}
}
