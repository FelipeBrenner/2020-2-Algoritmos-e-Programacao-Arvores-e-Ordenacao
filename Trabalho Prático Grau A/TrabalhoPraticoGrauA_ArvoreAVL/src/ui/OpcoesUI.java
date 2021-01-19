package ui;

import java.io.IOException;
import java.util.Scanner;

import negocio.ArvoreAVL;

public class OpcoesUI {
	private Scanner teclado;
	private String opcao = "";
	ArvoreAVL arvore;
	
	public OpcoesUI() {
		teclado = new Scanner(System.in);
		arvore = new ArvoreAVL();
	}
	
	public void menu() throws IOException {
		while(!opcao.equals("5")) {
			imprimirOpcoes();
			leOpcao();
		}
	}

	private void imprimirOpcoes() {
		System.out.println(
				"[0] Importar arquivo texto\n" +
				"[i] Inserir inteiro\n" +
				"[b] Buscar inteiro\n" +
				"[r] Remover inteiro\n" +
				"[1] Exibir árvore conforme encaminhamento Pré-Ordem\n" +
				"[2] Exibir árvore conforme encaminhamento Pós-Ordem\n" +
				"[3] Exibir árvore conforme encaminhamento Em-Ordem\n" +
				"[4] Exibir fatores de balancemento\n" +
				"[5] Sair");
	}

	private void leOpcao() {
		try {
			System.out.println("\nDigite a instrução que deseja executar: ");
			opcao = teclado.nextLine();
			switch(opcao) {
			case "0":
				arvore.importarArquivoTexto();
				break;
			case "i":
				arvore.inserir(leInteiro());
				System.out.print("Exibição da árvore após inserção Em-Ordem: ");
				arvore.exibeEmOrdem();
				break;
			case "b":
				arvore.buscar(leInteiro());
				break;
			case "r":
				arvore.remover(leInteiro());
				System.out.print("Exibição da árvore após remoção Em-Ordem: ");
				arvore.exibeEmOrdem();
				break;
			case "1":
				arvore.exibePreOrdem();
				break;
			case "2":
				arvore.exibePosOrdem();
				break;
			case "3":
				arvore.exibeEmOrdem();
				break;
			case "4":
				arvore.exibeFatorBalanceamento();
				break;
			case "5":
				break;
			default:
				System.out.println("Instrução inválida!");
			}
		}
		catch(Exception e) {
			teclado.nextLine();
			System.out.println("Digitação inválida!");
		}
		System.out.println();
	}
	
	public int leInteiro() {
		int inteiro;
		System.out.println("Digite o inteiro: ");
		inteiro = teclado.nextInt();
		teclado.nextLine();
		return inteiro;
	}
	
}
