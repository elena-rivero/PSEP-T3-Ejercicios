package pruebas;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Prueba {

	public static void main(String[] args) {
		try {
			InetAddress direccion = InetAddress.getByName("google.es");
			System.out.println("Dirección IP: " + direccion.getHostAddress());
			
			InetAddress local = InetAddress.getLocalHost();
			System.out.println("Dirección IP: " + local.getHostAddress());
			
		} catch (UnknownHostException e) {
			System.err.println("Error: No se encuentra la dirección de host");
			e.printStackTrace();
		}
	}
}
