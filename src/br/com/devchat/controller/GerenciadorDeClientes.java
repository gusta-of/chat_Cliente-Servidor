package br.com.devchat.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import br.com.devchat.utils.Validador;

public class GerenciadorDeClientes extends Thread {
	
	private Socket cliente;
	private BufferedReader leitor;
	private PrintWriter escritor;
	private Object cpf;
	
	public GerenciadorDeClientes(Socket cliente) {
		this.cliente = cliente;
		
		start();
	}

	@Override
	public void run() {
		try {
			leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			escritor = new PrintWriter(cliente.getOutputStream(), true);
		
			escritor.println("Digite um cpf:");
			String msg = leitor.readLine();
			this.cpf = msg;
			
			mandaMenssagem(escritor, cpf);
			while(true) {
				msg = leitor.readLine();
				mandaMenssagem(escritor, msg);
			}
			
		} catch (IOException e) {
			System.err.println("[!] " + this.cpf + " saiu da sala!");
		}
	}
	
	private void mandaMenssagem(PrintWriter escritor, Object cpf) {
		boolean cpfValido = Validador.validacpf(String.valueOf(cpf));
		String menssagem  = cpfValido ? " válido" : " não é válido!";
		
		escritor.println(this.cpf + menssagem);
		System.out.println("[!] " + this.cpf + " entrou");
	}
}
