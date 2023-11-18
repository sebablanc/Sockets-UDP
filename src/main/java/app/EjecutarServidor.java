package app;

import protocols.MSSP;

public class EjecutarServidor {
	private static int LOCAL_PORT = 80;
	public static void main(String[] args) {

		try{
		Servidor s = new Servidor();
		s.init(LOCAL_PORT);
		s.run();

		byte[] datos = s.GetData();
		MSSP lala = new MSSP(datos);
		String mensajeAMostrar = "";
		for(int i=0;i<lala.getSecuencia();i++) {
			datos = s.GetData();
			lala = new MSSP(datos);
			s.run();

			mensajeAMostrar += lala.getMensaje().trim();
		}

		System.out.println(mensajeAMostrar);
		if (s.getSalir()) {
			System.out.println("entra al if");
			s.fin();
		}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
