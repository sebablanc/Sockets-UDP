package app;

import java.net.InetAddress;

import comunicacion.SysCom;

public class Cliente {
	private SysCom sc;
	private boolean alive = false;

	public Cliente() {
		this.sc = new SysCom();
	}

	public void init(int portserver) {
		this.alive = true;
		this.sc.Init(portserver);
	}

	public void fin() {
		this.alive = false;
		this.sc.fin();
	}

	public void enviarMensaje(byte[] mensaje, String direccionIP, int puerto) {
		InetAddress IP = null;
		try {
			IP = InetAddress.getByName(direccionIP);
		} catch (Exception e) {
			System.out.println(e);
		}
		int port = puerto;
		sc.enviar(mensaje, IP, port);
		// System.out.println("Datos enviados");
	}
	
	public boolean isAlive() {
		return this.alive;
	}
}
