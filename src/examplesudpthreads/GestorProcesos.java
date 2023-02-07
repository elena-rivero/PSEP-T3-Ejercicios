package examplesudpthreads;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GestorProcesos extends Thread {
	DatagramSocket socket;
	DatagramPacket datagramaEntrada;

	public GestorProcesos(DatagramSocket socket, DatagramPacket datagramaEntrada) {
		super();
		this.socket = socket;
		this.datagramaEntrada = datagramaEntrada;
	}

	@Override
	public void run() {
		realizarProceso();
	}

	public void realizarProceso() {
		// Recepción de mensaje del cliente
		String mensajeRecibido = new String(datagramaEntrada.getData());
		System.out.println("(Servidor): Mensaje recibido: " + mensajeRecibido);
		
		// 5 - Generación y envío de la respuesta
		System.out.println("(Servidor): Enviando datagrama...");
		byte[] mensajeEnviado = new String("Hola " + mensajeRecibido).getBytes();
		DatagramPacket packetSalida = new DatagramPacket(mensajeEnviado, mensajeEnviado.length,
				datagramaEntrada.getAddress(), datagramaEntrada.getPort());
		try {
			socket.send(packetSalida);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
