package negocio;

public class No {
	private String valor;
	private int fatorBalanceamento;
	private No noEsquerdo;
	private No noDireito;
	private No noPai;
	private int linkPessoa;
	
	public No(String valor, int linkPessoa) {
		this.valor = valor;
		fatorBalanceamento = 0;
		noEsquerdo = null;
		noDireito = null;
		noPai = null;
		this.linkPessoa = linkPessoa;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public int getFatorBalanceamento() {
		return fatorBalanceamento;
	}
	
	public void setFatorBalanceamento(int fatorBalanceamento) {
		this.fatorBalanceamento = fatorBalanceamento;
	}
	
	public No getNoEsquerdo() {
		return this.noEsquerdo;
	}
	
	public void setNoEsquerdo(No noEsquerdo) {
		this.noEsquerdo = noEsquerdo;
	}
	
	public No getNoDireito() {
		return this.noDireito;
	}
	
	public void setNoDireito(No noDireito) {
		this.noDireito = noDireito;
	}
	
	public No getNoPai() {
		return this.noPai;
	}
	
	public void setNoPai(No noPai) {
		this.noPai = noPai;
	}
	
	public int getLinkPessoa() {
		return linkPessoa;
	}
	
	public void setLinkPessoa(int linkPessoa) {
		this.linkPessoa = linkPessoa;
	}
	
}
