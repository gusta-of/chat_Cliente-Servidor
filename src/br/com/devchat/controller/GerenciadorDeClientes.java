package br.com.devchat.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GerenciadorDeClientes extends Thread {
	
	private Socket cliente;
	private BufferedReader leitor;
	private PrintWriter escritor;
	private Object nomeCliente;
	
	public GerenciadorDeClientes(Socket cliente) {
		this.cliente = cliente;
		
		start();
	}

	@Override
	public void run() {
		try {
			leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			escritor = new PrintWriter(cliente.getOutputStream(), true);
		
			escritor.println("Digite seu nome para conectar");
			String msg = leitor.readLine();
			this.nomeCliente = msg;
			
			escritor.println(this.nomeCliente + " está conectado ao servidor!");
			System.out.println("[!] " + this.nomeCliente + " entrou");
			while(true) {
				msg = leitor.readLine();
				escritor.println( this.nomeCliente + " disse: " + msg);
			}
			
		} catch (IOException e) {
			System.err.println("[!] " + this.nomeCliente + " saiu da sala!");
		}
	}
}
