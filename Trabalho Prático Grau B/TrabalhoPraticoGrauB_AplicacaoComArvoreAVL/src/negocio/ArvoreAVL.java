package negocio;

import java.util.ArrayList;

public class ArvoreAVL {
	No noRaiz;
	
	public ArvoreAVL() {
		noRaiz = null;
	}

	public void inserir(String inteiro, int linkPessoa) {
		No noInserir = new No(inteiro, linkPessoa);
		inserir(noRaiz, noInserir);
	}
	
	public void inserir(No noReferencia, No noInserir) {
		if(noReferencia == null)
			noRaiz = noInserir;
		else
			if(noInserir.getValor().compareTo(noReferencia.getValor()) < 0)
				if(noReferencia.getNoEsquerdo() == null) {
					noReferencia.setNoEsquerdo(noInserir);
					noInserir.setNoPai(noReferencia);
					verificarBalanceamento(noReferencia);
				}
				else {
					inserir(noReferencia.getNoEsquerdo(),noInserir);
				}
			else if (noInserir.getValor().compareTo(noReferencia.getValor()) > 0)
				if(noReferencia.getNoDireito() == null) {
					noReferencia.setNoDireito(noInserir);
					noInserir.setNoPai(noReferencia);
					verificarBalanceamento(noReferencia);
				}
				else {
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

	public int buscarCpf(String string) {
		return buscarCpf(noRaiz, string);
	}
	
	private int buscarCpf(No no, String string) {
		if(no.getValor().equals(string)) {
			return no.getLinkPessoa();
		}
		else if (string.compareTo(no.getValor()) < 0 && no.getNoEsquerdo() != null) {
			return buscarCpf(no.getNoEsquerdo(), string);
		}
		else if (string.compareTo(no.getValor()) > 0 && no.getNoDireito() != null) {
			return buscarCpf(no.getNoDireito(), string);
		}
		return -1;
	}
	
	public ArrayList<Integer> buscarNomes(String string) {
		// Lista que vai conter os índices de links entre as árvores e o array das pessoas
		ArrayList<Integer> lista = new ArrayList<Integer>();
		lista = buscarNomes(noRaiz, string, lista);
		return lista;
	}
	
	private ArrayList<Integer> buscarNomes(No no, String string, ArrayList<Integer> lista) {
		// A partir do momento que encontrar o primeiro nome que possui o prefixo procurado no inicio, realiza a busca in-order a partir deste nó
		if(no.getValor().startsWith(string)) {
			return buscarNomesEmOrdem(no, string, lista);
		}
		// Enquanto não encontrar o primeiro nome que possui o prefixo procurado no inicio, continua pela busca binária
		else if (string.compareTo(no.getValor()) < 0 && no.getNoEsquerdo() != null) {
			lista = buscarNomes(no.getNoEsquerdo(), string, lista);
		}
		else if (string.compareTo(no.getValor()) > 0 && no.getNoDireito() != null) {
			lista = buscarNomes(no.getNoDireito(), string, lista);
		}
		return lista;
	}
	
	private ArrayList<Integer> buscarNomesEmOrdem(No no, String string, ArrayList<Integer> lista) {
		if(no != null) {
			buscarNomesEmOrdem(no.getNoEsquerdo(), string, lista);
			if(no.getValor().startsWith(string))
				lista.add(no.getLinkPessoa());
			buscarNomesEmOrdem(no.getNoDireito(), string, lista);
		}
		return lista;
	}
	
	public ArrayList<Integer> buscarDatas(String dataInicial, String dataFinal) {
		// Lista que vai conter os índices de links entre as árvores e o array das pessoas
		ArrayList<Integer> lista = new ArrayList<Integer>();
		// Conversão de DD/MM/YYYY para YYYYMMDD, devido a ordenação
		dataInicial = dataInicial.substring(6, 10) + dataInicial.substring(3, 5) + dataInicial.substring(0, 2);
		dataFinal = dataFinal.substring(6, 10) + dataFinal.substring(3, 5) + dataFinal.substring(0, 2);
		lista = buscarDatas(noRaiz, dataInicial, dataFinal, lista);
		return lista;
	}
	
	private ArrayList<Integer> buscarDatas(No no, String dataInicial, String dataFinal, ArrayList<Integer> lista) {
		// A partir do momento que encontrar a primeiro data dentro do intervalo, realiza a busca in-order a partir deste nó
		if(no.getValor().compareTo(dataInicial) >= 0 && no.getValor().compareTo(dataFinal) <= 0) {
			return buscarDatasEmOrdem(no, dataInicial, dataFinal, lista);
		}
		// Enquanto não encontrar a primeiro data dentro do intervalo, continua pela busca binária
		else if (no.getValor().compareTo(dataFinal) > 0 && no.getNoEsquerdo() != null) {
			lista = buscarDatas(no.getNoEsquerdo(), dataInicial, dataFinal, lista);
		}
		else if (no.getValor().compareTo(dataInicial) < 0 && no.getNoDireito() != null) {
			lista = buscarDatas(no.getNoDireito(), dataInicial, dataFinal, lista);
		}
		return lista;
	}
	
	private ArrayList<Integer> buscarDatasEmOrdem(No no, String dataInicial, String dataFinal, ArrayList<Integer> lista) {
		if(no != null) {
			buscarDatasEmOrdem(no.getNoEsquerdo(), dataInicial, dataFinal, lista);
			if(no.getValor().compareTo(dataInicial) >= 0 && no.getValor().compareTo(dataFinal) <= 0)
				lista.add(no.getLinkPessoa());
			buscarDatasEmOrdem(no.getNoDireito(), dataInicial, dataFinal, lista);
		}
		return lista;
	}

}
