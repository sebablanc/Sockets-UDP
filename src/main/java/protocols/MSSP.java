package protocols;

public class MSSP {
	private byte id_msn;
	private byte sec_msn;
	private byte total_sec_msn;
	private String msn;

	public MSSP(byte id_mensaje, byte total_secuencias, byte secuencia, String mensaje) {
		id_msn = id_mensaje;
		total_sec_msn = total_secuencias;
		sec_msn = secuencia;
		msn = mensaje;
	}

	public MSSP(byte[] datos_UDP) {
		id_msn = datos_UDP[0];
		sec_msn = datos_UDP[1];
		total_sec_msn = datos_UDP[2];

		byte[] msg_aux = new byte[datos_UDP.length-3];

		for (int i = 0; i < datos_UDP.length-3; i++) {
			msg_aux[i] = datos_UDP[i+3];
		}
		msn=new String(msg_aux);
	}

	public byte getId() {
		return id_msn;
	}

	public void setId(byte id) {
		this.id_msn = id;
	}

	public byte getSecuencia() {
		return sec_msn;
	}

	public void setSecuencia(byte secuencia) {
		this.sec_msn = secuencia;
	}

	public String getMensaje() {
		return msn;
	}

	public void setMensaje(String mensaje) {
		this.msn = mensaje;
	}

	public byte getTotal_sec_msn() {
		return total_sec_msn;
	}

	public void setTotal_sec_msn(byte total_sec_msn) {
		this.total_sec_msn = total_sec_msn;
	}

	public byte[] getArrayBytes() {
		// array de bytes para todo el protocolo
		byte proto[] = new byte[57];

		// transforma el mensaje a bytes
		byte msn_by[] = msn.getBytes();

		// asigna el primer y segundo byte con la informacion correspondiente
		proto[0] = id_msn;
		proto[1] = total_sec_msn;
		proto[2] = sec_msn;

		// asigna el mensaje al array, byte por byte
		for (int i = 0; i < msn_by.length; i++)
			proto[i + 3] = msn_by[i];

		// retorna el MSSP como byte[];
		return proto;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID de secuencia: ").append(this.id_msn).append("\n").append("Cantidad de secuencias: ")
				.append(this.total_sec_msn).append("\n").append("Nro. de secuencia: ").append(this.sec_msn).append("\n")
				.append("mensaje: ").append(this.msn);
		return sb.toString();
	}

}
