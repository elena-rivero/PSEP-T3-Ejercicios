package ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorFactorial {

	public static void main(String[] args) {
		int numero;
		String mensaje;
		
		try {
			// 1 - Creación del socket servidor
			System.out.println("(Servidor): Abriendo conexión...");
			ServerSocket socketServidor = new ServerSocket(50000);

			while (true) {
				// 2 - Espera y acepta conexiones
				System.out.println("(Servidor): Esperando peticiones...");
				Socket socketCliente = socketServidor.accept();

				// 3 - Flujos de entrada y salida
				System.out.println("(Servidor): Abriendo flujos de entrada y de salida...");
				InputStream is = socketCliente.getInputStream();
				OutputStream os = socketCliente.getOutputStream();

				// 4 - Intercambiar datos con el cliente
				System.out.println("(Servidor): Leo mensaje del cliente...");
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				BufferedReader br = new BufferedReader(isr);

				numero = br.read();
				mensaje = esPrimo(numero);
				
				System.out.println("(Servidor): Envío mensaje al cliente...");
				OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
				BufferedWriter bw = new BufferedWriter(osw);
				
				bw.write(mensaje);
				bw.newLine();
				bw.flush();

				// 5 - Cerrar flujos de lectura y escritura
				System.out.println("(Servidor): Cierre de flujos de lectura y escritura...");
				br.close();
				isr.close();
				is.close();
				bw.close();
				osw.close();
				os.close();

				socketCliente.close();
			}
			// 6 - Cerrar la conexión
//			System.out.println("(Servidor): Cierre de la conexión...");
//			socketCliente.close();
//			socketServidor.close();

		} catch (IOException e) {
			System.err.println("ERROR: Error al crear el socket en el puerto 50000");
			e.printStackTrace();
		}
	}

	public static String esPrimo(int num) {
		String resul = "Es primo";
		boolean enc = false;

		if (num <= 1) {
			resul = "No es primo";
		}
		for (int i = 2; i <= num/2 && !enc; i++) {
			if (num % i == 0) {
				resul = "No es primo";
				enc = true;
			}
		}
		return resul;
	}
}
