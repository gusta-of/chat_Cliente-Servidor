package br.com.devchat.view;

import java.io.IOException;
import java.net.ServerSocket;

import br.com.devchat.controller.GerenciadorDeClientes;

public class Servidor {
	
	private static ServerSocket servidor;

	public static void main(String[] args) {
		int porta = 6074;
		try {
			System.out.println("[!] Startando o servidor");
			setServidor(new ServerSocket(porta));
			System.out.println("[!] Servidor foi iniciado na porta " + porta);
			
			while(true) {
				new GerenciadorDeClientes(servidor.accept());
			}
			
		} catch (IOException e) {
			System.err.println("[Erro]  A porta do servidor pode estar ocupada ou o mesmo foi fechado");
			e.printStackTrace();
		}
		
		
		
	}

	public static ServerSocket getServidor() {
		return servidor;
	}

	public static void setServidor(ServerSocket servidor) {
		Servidor.servidor = servidor;
	}
}
