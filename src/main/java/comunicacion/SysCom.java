package comunicacion;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SysCom {
	private DatagramSocket Server;
	private int puertoescucha;
	private DatagramSocket Cliente;

	public void Init(int portServer) {
		this.puertoescucha = portServer;
		try {
			this.Server = new DatagramSocket(this.puertoescucha);
			this.Cliente = new DatagramSocket();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public int getServerPort() {
		return this.puertoescucha;
	}

	public void enviar(byte[] datos, InetAddress IP, int port) {
		DatagramPacket dp = new DatagramPacket(datos, datos.length, IP, port);
		try {
			this.Cliente.send(dp);
		} catch (Exception e) {
			System.out.println("ERROR" + e);
		}
	}

	public byte[] recibir() {
		byte[] datos = new byte[1024];
		DatagramPacket dp = new DatagramPacket(datos, datos.length);
		try {
			this.Server.receive(dp);
			datos = dp.getData();
		} catch (Exception e) {
			System.out.println("ERROR" + e);
		}
		return datos;
	}

	public void fin() {
		this.Cliente.close();
		this.Server.close();
	}
}
