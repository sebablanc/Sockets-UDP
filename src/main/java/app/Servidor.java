package app;

import comunicacion.SysCom;
import protocols.MSSP;

public class Servidor extends Thread {
	private SysCom sc;
	private byte[] datosrecibidos;
	private boolean salir = false;

	public Servidor() {
		this.sc = new SysCom();
	}

	public void fin() {
		this.interrupt();
		this.sc.fin();
	}

	public void run() {
		salir = false;
		byte[] datos = this.sc.recibir();
		MSSP strsalir = new MSSP(datos);
		if (strsalir.getMensaje().trim() == "SALIR") {
			salir = true;
			return;
		} 
		datosrecibidos = datos;
	}

	public void init(int portserver) {
		this.sc.Init(portserver);

		System.out.println("servidor en escucha en puerto:" + sc.getServerPort());
		//this.run();
	}

	public byte[] GetData() {
		return this.datosrecibidos;
	}

	public boolean getSalir() {
		return this.salir;
	}
}
