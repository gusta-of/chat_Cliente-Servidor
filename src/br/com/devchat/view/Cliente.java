package br.com.devchat.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	
	private static Socket cliente;

	public static void main(String[] args) {
		
		try {
			cliente = new Socket("127.0.0.1", 6074);
			
			// Le menssagens do servidor
			new Thread() {
				@Override
				public void run() {
					try {
						BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
						
						while(true) {
							String menssagem = leitor.readLine();
							System.out.println("[!]Srevidor: " + menssagem);
						}
						
					} catch (IOException e) {
						System.err.println("[Erro] Não foi possível ler a messagem do servidor!");
						e.printStackTrace();
					}
				};
			}.start();
			
			// Escreve para o servidor
			PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);
			BufferedReader leitorTerminal = new BufferedReader(new InputStreamReader(System.in));
			
			while(true) {
				String menssagemTerminal = leitorTerminal.readLine();
				escritor.println(menssagemTerminal);
			}
			
		} catch (UnknownHostException e) {
			
			System.err.println("[Erro] Endereço solicitado é invalido!");
			e.printStackTrace();
		} catch (IOException e) {
			
			System.err.println("[Erro] O servidor não esta respondendo!");
			e.printStackTrace();
		}
		
	}

}
