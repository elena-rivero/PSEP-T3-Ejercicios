package conexionCadenasTCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP {

	public static void main(String[] args) {
		
		try {
			// 1 - Creación de socket de tipo cliente
			System.out.println("(Cliente): Creación de socket...");
			InetAddress direccion = InetAddress.getByName("192.168.0.183");
			Socket socketCliente = new Socket(direccion, 50000);
			
			// 2 - Abrir flujos de lectura y escritura
			System.out.println("(Cliente): Apertura de flujos de entrada y salida...");
			OutputStream os = socketCliente.getOutputStream();
			InputStream is = socketCliente.getInputStream();
			
			// 3 - Intercambio de datos con el servidor
			System.out.println("(Cliente): Envía mensaje al servidor...");
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write("Mensaje que el cliente envía al servidor");
			bw.newLine();
			bw.flush();
			
			System.out.println("(Cliente): Lectura del mensaje del servidor...");
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			System.out.println("Mensaje recibido por cliente: " + br.readLine());
			
			// 4 - Cerrar los flujos de lectura y escritura
			System.out.println("(Cliente): Cerramos flujo de lectura y escritura...");
			bw.close();
			osw.close();
			br.close();
			isr.close();
			
			is.close();
			os.close();
			
			// 5 - Cerrar la conexión
			socketCliente.close();
			
		} catch (UnknownHostException e) {
			System.err.println("ERROR: No se encuentra el host");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("ERROR: Problema con la conexión");
			e.printStackTrace();
		}
	}

}
