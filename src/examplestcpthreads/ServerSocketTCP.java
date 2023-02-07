package examplestcpthreads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTCP {

	public static void main(String[] args) {

		try {
			// 1 - Crear socket de tipo servidor y le indicamos el puerto
			ServerSocket servidor = new ServerSocket(49200);
			Socket peticion;
			while (true) {
				// 2 - Queda a la espera de peticiones y las acepta cuando las recibe
				System.out.println("Servidor se encuentra a la escucha de peticiones...");
				peticion = servidor.accept();
				System.out.println("(Servidor) conexión establecida...");
				new GestorProcesos(peticion).start();
			}

		} catch (IOException e) {
			System.err.println("Ha habido algún error en la creación del Socket Servidor");
			e.printStackTrace();
		}
	}

}
