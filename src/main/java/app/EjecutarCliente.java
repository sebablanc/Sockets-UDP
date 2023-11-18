package app;

import protocols.MSSP;

public class EjecutarCliente {
	private static int LOCAL_PORT = 81;
	private static int REMOTE_PORT = 80;
	private static int MAX_LONG_MSG_MSSP = 50;
	private static String REMOTE_SERVER = "127.0.0.1";
	private static String KILL_COMM_WORD = "SALIR";

	public static void main(String[] args) {
		String msg = "Hola, ¿Cómo estás? quería consultarte sobre esto que te quiero preguntar porque no me quedo claro, entonces preguntando queria aclarar...";
		// Se obtienen la cantidad de secuencias necesarias para enviar con MSSP
		Integer secuencias_totales = msg.length() / MAX_LONG_MSG_MSSP;
		Integer resto_secuencias = msg.length()%MAX_LONG_MSG_MSSP;
		
		// agrego una secuencia más al total para enviar todos los bytes
		if(resto_secuencias > 0) secuencias_totales++;
		
		System.out.println("Secuencias totales a enviar: " + secuencias_totales + "\n");

		// agregando Objetos MSSP a la lista de mensajes
		String id = "10";

		Cliente c = new Cliente();
		c.init(LOCAL_PORT);
		for (Integer i = 0; i < secuencias_totales; i++) {
			int substringStart = i * MAX_LONG_MSG_MSSP;
			int substringEnd = (substringStart + MAX_LONG_MSG_MSSP) > msg.length() ? msg.length() : substringStart + MAX_LONG_MSG_MSSP;
			String mensaje = msg.substring(substringStart, substringEnd);
			MSSP msspToSend = new MSSP(id.getBytes()[0], secuencias_totales.byteValue(), i.byteValue(), mensaje);
			System.out.println(msspToSend.toString());
			System.out.println("\n");
			c.enviarMensaje(msspToSend.getArrayBytes(), REMOTE_SERVER, REMOTE_PORT);
		}

		MSSP msspToSend = new MSSP(id.getBytes()[0], secuencias_totales.byteValue(), secuencias_totales.byteValue(), KILL_COMM_WORD);
		c.enviarMensaje(msspToSend.getArrayBytes(), REMOTE_SERVER, REMOTE_PORT);
		System.out.println("Se enviaron todos los mensajes y se cerró la sesión");
		c.fin();

	}

}
