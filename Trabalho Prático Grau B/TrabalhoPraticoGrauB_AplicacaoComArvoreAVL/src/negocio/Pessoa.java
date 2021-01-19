package negocio;

public class Pessoa {
	String cpf;
	String rg;
	String nome;
	String data;
	String cidade;
	
	public Pessoa(String cpf, String rg, String nome, String data, String cidade) {
		this.cpf = cpf;
		this.rg = rg;
		this.nome = nome;
		this.data = data;
		this.cidade = cidade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	@Override
	public String toString() {
		return nome + ", CPF: " + cpf + ", RG: " + rg + ", Data Nascimento: " + data + ", Cidade Nascimento: " + cidade;
	}
}
