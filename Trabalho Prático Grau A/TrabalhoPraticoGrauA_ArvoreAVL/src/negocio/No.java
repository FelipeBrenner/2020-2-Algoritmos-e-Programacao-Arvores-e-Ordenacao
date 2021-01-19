package negocio;

public class No {
	private int valor;
	private int fatorBalanceamento;
	private No noEsquerdo;
	private No noDireito;
	private No noPai;
	
	public No(int valor) {
		this.valor = valor;
		fatorBalanceamento = 0;
		noEsquerdo = null;
		noDireito = null;
		noPai = null;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
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
	
}
