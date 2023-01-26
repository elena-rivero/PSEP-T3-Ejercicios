package ejercicio4;

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
import java.util.Scanner;

public class ClienteAdivinaNumero {

	public static void main(String[] args) {

		int num;
		String acertado = "";

		try {
			// 1 - Creación de socket de tipo cliente
			System.out.println("(Cliente): Creación de socket...");
			InetAddress direccion = InetAddress.getLocalHost();
			Socket socketCliente = new Socket(direccion, 50000);

			// 2 - Abrir flujos de lectura y escritura
			System.out.println("(Cliente): Apertura de flujos de entrada y salida...");
			OutputStream os = socketCliente.getOutputStream();
			InputStream is = socketCliente.getInputStream();

			// 3 - Intercambio de datos con el servidor
			System.out.println("(Cliente): Envía el número al servidor...");
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			while (!acertado.equals("IGUAL")) {
				num = leeNumero();
				bw.write(String.valueOf(num));
				bw.newLine();
				bw.flush();

				System.out.println("(Cliente): Lectura del mensaje del servidor...");

				acertado = br.readLine();
				mensajeSalida(acertado);
			}
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

	public static int leeNumero() {
		int numero;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("Introduzca un número:");
//			sc.next();
			numero = sc.nextInt();
		} while (numero < 0 || numero > 100);

		// sc.close();

		return numero;
	}

	public static void mensajeSalida(String acertado) {
		if (acertado.equals("IGUAL")) {
			System.out.println("Ha acertado");
		} else if (acertado.equals("MENOR")) {
			System.out.println("El número que has introducido es menor");
		} else {
			System.out.println("El número que has introducido es mayor");
		}
	}
}
