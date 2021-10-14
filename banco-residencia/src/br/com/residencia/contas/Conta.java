package br.com.residencia.contas;

public abstract class Conta {
	
	/*private String nome;
	private int numero;
	private String sobrenome;
	private double saldo;
	
	//*JULIOOOOOOOOOOOOOOOOO	

	@Override
	public String toString() {
		return "Conta [nome=" + nome + " " + sobrenome + ", numero=" + numero + ", saldo=" + saldo + "]";
	}
// heheh
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	*/
	
	private String cpf;
	private String agencia;
	private String numero;
	private double saldo;
	
	private boolean sacar(double valor) {
		if(this.saldo < valor) {
			return false;
		}
		else {
			this.saldo -= valor;
			return true;
		}
	}

	protected void depositar(double valor) {
		this.saldo += valor;
	}

	protected boolean transferir(double valor, Conta destinatario) {
		//boolean retirou = this.sacar(valor);
		if(this.sacar(valor)) {
			destinatario.depositar(valor);
			return true;
		}
		else {
			return false;
		}
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getAgencia() {
		return agencia;
	}
	
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return saldo;
	}
}
