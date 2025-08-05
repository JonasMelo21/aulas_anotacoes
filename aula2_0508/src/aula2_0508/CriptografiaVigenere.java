package aula2_0508;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;

public class CriptografiaVigenere {
	public static void main(String[] args) {
		// declarar variaveis
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
		
		String mensagem = "";
		String senha = "";
		String cifra = "";
		int opcao = 0;
		
		try {
			System.out.println("Digite a mensagem: ");
			mensagem = leitor.readLine();
			
			System.out.println("Digita a senha: ");
			senha = leitor.readLine();
			

			System.out.println("Digite <1> para encriptar ou <2> para decriptar");
			opcao = Integer.parseInt(leitor.readLine());
			
			if (opcao == 1) {
				cifra = encriptar(mensagem,senha);
			}else {
				cifra = decriptar(mensagem,senha);
			}
			
			System.out.println("Resultado: "+cifra);
		} catch (Exception erro) {
			System.out.println(erro);
		}
	}

	private static String decriptar(String cifra, String senha) {
		String mensagem = "";
		for(int i = 0;i < cifra.length(); i+= 2) {
			int letraCifra = Integer.parseInt(cifra.substring(i,i + 2), 16);
			int letraSenha = senha.charAt((i / 2) % senha.length());
			int letraMensagem = letraCifra ^ letraSenha;
			
			mensagem += ((char) letraMensagem);
		}
		return mensagem;
	}

	private static String encriptar(String mensagem, String senha) {
		String cifra = "";
		for(int i =0;i < mensagem.length();i++) {
			int letraMensagem = mensagem.charAt(i);
			int letraSenha = senha.charAt(i % senha.length());
			int letraCifra = letraMensagem ^ letraSenha;
			
			String temp = Integer.toHexString(letraCifra & 0xff);
			if (temp.length() == 1) {
				temp = "0" + temp;
			}cifra += temp;
		}
		return cifra;
	}
}
