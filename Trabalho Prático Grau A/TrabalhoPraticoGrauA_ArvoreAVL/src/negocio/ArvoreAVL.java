package negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ArvoreAVL {
	No noRaiz;
	
	public ArvoreAVL() {
		noRaiz = null;
	}

	public void inserir(int inteiro) {
		No noInserir = new No(inteiro);
		inserir(noRaiz, noInserir);
	}
	
	public void inserir(No noReferencia, No noInserir) {
		if(noReferencia == null)
			noRaiz = noInserir;
		else
			if(noInserir.getValor() < noReferencia.getValor())
				if(noReferencia.getNoEsquerdo() == null) {
					 System.out.println("Inserido " + noInserir.getValor() + " a esquerda de " + noReferencia.getValor());
					noReferencia.setNoEsquerdo(noInserir);
					noInserir.setNoPai(noReferencia);
					verificarBalanceamento(noReferencia);
				}
				else {
					 System.out.println("Inserindo " + noInserir.getValor() + " a esquerda de " + noReferencia.getValor());
					inserir(noReferencia.getNoEsquerdo(),noInserir);
				}
			else if (noInserir.getValor() > noReferencia.getValor())
				if(noReferencia.getNoDireito() == null) {
					 System.out.println("Inserido " + noInserir.getValor() + " a direita de " + noReferencia.getValor());
					noReferencia.setNoDireito(noInserir);
					noInserir.setNoPai(noReferencia);
					verificarBalanceamento(noReferencia);
				}
				else {
					 System.out.println("Inserindo " + noInserir.getValor() + " a direita de " + noReferencia.getValor());
					inserir(noReferencia.getNoDireito(),noInserir);
				}
	}
	
	private void verificarBalanceamento(No no) {
		setBalanceamento(no);
		int fatorBalanceamento = no.getFatorBalanceamento();
		
		if(fatorBalanceamento == 2)

			if(altura(no.getNoEsquerdo().getNoEsquerdo()) >= altura(no.getNoEsquerdo().getNoDireito()))
				no = rotacaoDireita(no);
			else
				no = rotacaoDuplaDireita(no);

		else if(fatorBalanceamento == -2)

			if(altura(no.getNoDireito().getNoDireito()) >= altura(no.getNoDireito().getNoEsquerdo()))
				no = rotacaoEsquerda(no);
			else
				no = rotacaoDuplaEsquerda(no);

		if(no.getNoPai() != null)
			verificarBalanceamento(no.getNoPai());
		else
			noRaiz = no;
	}

	private void setBalanceamento(No no) {
		System.out.println("Nó: " + no.getValor() + " Bal: " + altura(no.getNoEsquerdo()) + " - " + altura(no.getNoDireito()));
		no.setFatorBalanceamento(altura(no.getNoEsquerdo()) - altura(no.getNoDireito()));
	}

	private int altura(No no) {
		if (no == null)
			return 0;

		else if (no.getNoEsquerdo() == null && no.getNoDireito() == null)
			return 1;
		
		else if (no.getNoEsquerdo() == null)
			return 1 + altura(no.getNoDireito());
		
		else if (no.getNoDireito() == null)
			return 1 + altura(no.getNoEsquerdo());
		
		else
			return 1 + Math.max(altura(no.getNoEsquerdo()), altura(no.getNoDireito()));
	}
	
	private No rotacaoDireita(No inicial) {
		No esquerdo = inicial.getNoEsquerdo();
		esquerdo.setNoPai(inicial.getNoPai());

		inicial.setNoEsquerdo(esquerdo.getNoDireito());

		if(inicial.getNoEsquerdo() != null)
			inicial.getNoEsquerdo().setNoPai(inicial);

		esquerdo.setNoDireito(inicial);
		inicial.setNoPai(esquerdo);

		if(esquerdo.getNoPai() != null) 
			if(esquerdo.getNoPai().getNoDireito() == inicial)
				esquerdo.getNoPai().setNoDireito(esquerdo);
			else if(esquerdo.getNoPai().getNoEsquerdo() == inicial)
				esquerdo.getNoPai().setNoEsquerdo(esquerdo);

		setBalanceamento(inicial);
		setBalanceamento(esquerdo);

		return esquerdo;
	}

	private No rotacaoDuplaDireita(No inicial) {
		inicial.setNoEsquerdo(rotacaoEsquerda(inicial.getNoEsquerdo()));
		return rotacaoDireita(inicial);
	}

	private No rotacaoEsquerda(No inicial) {
		No direita = inicial.getNoDireito();
		direita.setNoPai(inicial.getNoPai());

		inicial.setNoDireito(direita.getNoEsquerdo());

		if(inicial.getNoDireito() != null)
			inicial.getNoDireito().setNoPai(inicial);

		direita.setNoEsquerdo(inicial);
		inicial.setNoPai(direita);

		if(direita.getNoPai() != null)
			if(direita.getNoPai().getNoDireito() == inicial)
				direita.getNoPai().setNoDireito(direita);
			else if(direita.getNoPai().getNoEsquerdo() == inicial)
				direita.getNoPai().setNoEsquerdo(direita);

		setBalanceamento(inicial);
		setBalanceamento(direita);

		return direita;
	}

	private No rotacaoDuplaEsquerda(No inicial) {
		inicial.setNoDireito(rotacaoDireita(inicial.getNoDireito()));
		return rotacaoEsquerda(inicial);
	}

	public void buscar(int inteiro) {
		buscar(noRaiz, inteiro);
	}
	
	public void buscar(No no, int inteiro) {
		if(no.getValor() == inteiro) {
			System.out.println("Número econtrado, " + inteiro + " está na árvore!");
		}
		else if (inteiro < no.getValor() && no.getNoEsquerdo() != null) {
			System.out.println("Buscando a esquerda de " + no.getValor());
			buscar(no.getNoEsquerdo(), inteiro);
		}
		else if (inteiro > no.getValor() && no.getNoDireito() != null) {
			System.out.println("Buscando a direita de " + no.getValor());
			buscar(no.getNoDireito(), inteiro);
		}
		else {
			System.out.println("Número " + inteiro + " não está na árvore!");
		}
	}

	public void remover(int inteiro) {
		remover(noRaiz, inteiro);		
	}

	private void remover(No noReferencia, int inteiro) {
		if(noReferencia == null)
			System.out.println("Inteiro não está na árvore!");
		else
			if(inteiro < noReferencia.getValor())
				remover(noReferencia.getNoEsquerdo(), inteiro);
			else if (inteiro > noReferencia.getValor()) 
				remover(noReferencia.getNoDireito(), inteiro);
			else if (noReferencia.getValor() == inteiro) {
				removerNoEncontrado(noReferencia);
				System.out.println("Inteiro removido com sucesso!");
			}
	}

	private void removerNoEncontrado(No noRemover) {
		No r;
		// Caso em que o nó não possui dois filhos
		if (noRemover.getNoEsquerdo() == null || noRemover.getNoDireito() == null) {
			if (noRemover.getNoPai() == null) {
				noRaiz = null;
				noRemover = null;
				return;
			}
			r = noRemover;
		} else {
			// Caso em que o nó possui dois filhos
			r = fusao(noRemover);
			noRemover.setValor(r.getValor());
		}

		No p;
		if(r.getNoEsquerdo() != null)
			p = r.getNoEsquerdo();
		else
			p = r.getNoDireito();

		if(p != null)
			p.setNoPai(r.getNoPai());

		if(r.getNoPai() == null)
			noRaiz = p;
		else {
			if (r == r.getNoPai().getNoEsquerdo())
				r.getNoPai().setNoEsquerdo(p);
			else
				r.getNoPai().setNoDireito(p);
			
			verificarBalanceamento(r.getNoPai());
		}
		r = null;
	}

	private No fusao(No noRemover) {
		// Exclusão por Fusão, procurando o nó com menor valor da sub-árvore direita
		No r = noRemover.getNoDireito();
		while (r.getNoEsquerdo() != null) {
			r = r.getNoEsquerdo();
		}
		return r;
	}

	public void exibePreOrdem() {
		if(noRaiz != null) {
			exibePreOrdem(noRaiz);
			System.out.println();
		}
		else
			System.out.println("Árvore vazia!");
	}

	public void exibePreOrdem(No no) {
		if(no != null) {
			System.out.print(no.getValor() + " ");
			exibePreOrdem(no.getNoEsquerdo());
			exibePreOrdem(no.getNoDireito());
		}
	}

	public void exibePosOrdem() {
		if(noRaiz != null) {
			exibePosOrdem(noRaiz);
			System.out.println();
		}
		else
			System.out.println("Árvore vazia!");
	}

	public void exibePosOrdem(No no) {
		if(no != null) {
			exibePosOrdem(no.getNoEsquerdo());
			exibePosOrdem(no.getNoDireito());
			System.out.print(no.getValor() + " ");
		}
	}

	public void exibeEmOrdem() {
		if(noRaiz != null) {
			exibeEmOrdem(noRaiz);
			System.out.println();
		}
		else
			System.out.println("Árvore vazia!");
	}

	public void exibeEmOrdem(No no) {
		if(no != null) {
			exibeEmOrdem(no.getNoEsquerdo());
			System.out.print(no.getValor() + " ");
			exibeEmOrdem(no.getNoDireito());
		}
	}
	
	public void exibeFatorBalanceamento() {
		if(noRaiz != null) {
			exibeFatorBalanceamento(noRaiz);
			System.out.println();
		}
		else
			System.out.println("Árvore vazia!");
	}
	
	public void exibeFatorBalanceamento(No no) {
		if(no != null) {
			exibeFatorBalanceamento(no.getNoEsquerdo());
			System.out.print("\n" + no.getValor() + " Fator Balanceamento: " + no.getFatorBalanceamento());
			exibeFatorBalanceamento(no.getNoDireito());
		}
	}

	public void importarArquivoTexto() throws IOException {
		File objFile;
		InputStream is;
		InputStreamReader isr;
		BufferedReader br;
		String arvore[];
		
		objFile= new File("Arvore.txt");
		if (objFile.exists()) {
			is = new FileInputStream("Arvore.txt");
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			arvore = br.readLine().split(",");
			for(String s : arvore)
				inserir(Integer.valueOf(s));

			br.close();
			
			System.out.println("Arquivo importato com sucesso!");
		}
		else
			System.out.println("Arquivo não existe!");
	}

}
