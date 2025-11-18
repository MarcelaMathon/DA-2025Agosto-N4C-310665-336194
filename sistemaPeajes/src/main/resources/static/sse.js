/* 
 * Server-Sent Events (SSE) library
 * Se asume que está incluida vistaWeb.js
 * La página que incluya esta lib debe cargar esta variable 
 */

// Para registrar SSE debe ejecutarse siempre antes el inicio de la vista 
var urlRegistroSSE = null;

// Esta función la llama vistaWeb.js al final del submit de inicio de la vista
function primerSubmitFinalizado() {
    registrarSSE();
}

function registrarSSE() {
    // Llamada al endpoint para recibir mensajes desde el servidor
    if (urlRegistroSSE !== null) {
        
        const eventSource = new EventSource(urlRegistroSSE, {withCredentials: true});
               
        // LLEGA UN MENSAJE DESDE EL SERVIDOR!
        eventSource.onmessage = function(event) {
            // Se asume que todos los mensajes llegan en formato JSON
            json = JSON.parse(event.data); // Convertir el JSON a objeto
            procesarMensajeSSE(json); 
        };
        
        // ERROR EN LA CONEXIÓN CON EL SERVIDOR
        eventSource.onerror = function(event) {
            // En todos los casos se cierra el event source
            eventSource.close();
            try {
                conexionSSECerrada(event); // Método que puede estar definido en la página que incluya esta lib 
                                           // para personalizar el manejo del error en la conexión SSE   
            } catch (e) {
                // Por defecto se "borra" la página
                document.body.innerHTML = '';    
            }
        };
    }
}

// Por defecto se asume que los mensajes recibidos via SSE tienen el mismo formato que las respuestas
// del submit. 
function procesarMensajeSSE(mensaje) {
    procesarResultadosSubmit(mensaje);
}
