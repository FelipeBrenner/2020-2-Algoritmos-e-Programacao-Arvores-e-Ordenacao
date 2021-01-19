package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import negocio.ArvoreAVL;
import negocio.Pessoa;

public class OpcoesUI {
	private Scanner teclado;
	private int opcao = 0;
	ArvoreAVL cpfs;
	ArvoreAVL nomes;
	ArvoreAVL datas;
	ArrayList<Pessoa> pessoas;
	
	public OpcoesUI() {
		teclado = new Scanner(System.in);
		cpfs = new ArvoreAVL();
		nomes = new ArvoreAVL();
		datas = new ArvoreAVL();
		pessoas = new ArrayList<Pessoa>();
	}
	
	public void menu() throws IOException {
		importarArquivoTexto();
		while(opcao != 4) {
			imprimirOpcoes();
			leOpcao();
		}
	}

	private void imprimirOpcoes() {
		System.out.println(
				"[1] Consultar pessoa pelo CPF\n" +
				"[2] Consultar pessoas pelo nome\n" +
				"[3] Consultar pessoas por intervalo de data de nascimento\n" +
				"[4] Sair");
	}

	private void leOpcao() {
		try {
			System.out.println("\nDigite a instrução que deseja executar: ");
			opcao = leInteiro();
			switch(opcao) {
			case 1:
				consultarCPF();
				break;
			case 2:
				consultarNome();
				break;
			case 3:
				consultarData();
				break;
			case 4:
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
	
	private int leInteiro() {
		int inteiro;
		inteiro = teclado.nextInt();
		teclado.nextLine();
		return inteiro;
	}
	
	private void consultarCPF() {
		System.out.println("Digite o CPF: ");
		String cpf = teclado.nextLine();
		int indice = cpfs.buscarCpf(cpf);
		if(indice>=0) {
			System.out.println(pessoas.get(indice));
		}
		else
			System.out.println("Pessoa não encontrada pelo CPF digitado!");
	}
	
	private void consultarNome() {
		System.out.println("Digite o nome:");
		String nome = teclado.nextLine();
		ArrayList<Integer> indices = nomes.buscarNomes(nome);
		if(indices.size() > 0) {
			for(int i : indices)
				System.out.println(pessoas.get(i));
		}
		else {
			System.out.println("Nenhuma pessoa encontrada com este nome!");
		}
	}
	
	private void consultarData() {
		System.out.println("Data inicial (no formato DD/MM/AAAA):");
		String dataInicial = teclado.nextLine();
		System.out.println("Data final (no formato DD/MM/AAAA):");
		String dataFinal = teclado.nextLine();
		ArrayList<Integer> indices = datas.buscarDatas(dataInicial,dataFinal);
		if(indices.size() > 0) {
			for(int i : indices)
				System.out.println(pessoas.get(i));
		}
		else {
			System.out.println("Nenhuma pessoa encontrada com data de nascimento entre estas digitadas!");
		}
	}
	
	private void importarArquivoTexto() throws IOException {
		File objFile;
		InputStream is;
		InputStreamReader isr;
		BufferedReader br;
		String s[];
		String l;
		int linkPessoa = 0;
		
		objFile= new File("Pessoas.txt");
		
		if (objFile.exists()) {
			is = new FileInputStream("Pessoas.txt");
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			l = br.readLine();
			
			// Enquanto houver linha no arquivo
			while(l != null) {
				// Cria um array com cada campo da linha separado por ;
				s = l.split(";");
				
				// Cria a pessoa e a adiciona no array de pessoas
				pessoas.add(new Pessoa(s[0],s[1],s[2],s[3],s[4]));
				
				// Insere o cpf na árvore de cpfs, juntamente com o índice que relaciona com o array de pessoas
				cpfs.inserir(s[0],linkPessoa);
				
				// Insere o nome na árvore de nomes, juntamente com o índice que relaciona com o array de pessoas
				nomes.inserir(s[2],linkPessoa);
				
				// Conversão de DD/MM/YYYY para YYYYMMDD, devido a ordenação
				String dataFormatada = s[3].substring(6, 10) + s[3].substring(3, 5) + s[3].substring(0, 2);
				// Insere a data na árvore de datas, juntamente com o índice que relaciona com o array de pessoas
				datas.inserir(dataFormatada,linkPessoa);
				
				l = br.readLine();
				linkPessoa++;
			}

			br.close();
			
		}
	}
	
}
