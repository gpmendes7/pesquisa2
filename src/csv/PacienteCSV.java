package csv;

public class PacienteCSV {
	
	private String cpf;
	private String nomeCompleto;
	private String dataNascimento;
	private String municipioNotificacao;
	private String racaCor;

	public PacienteCSV(String cpf, String nomeCompleto, 
			           String dataNascimento, String municipioNotificacao, 
			           String racaCor) {
		this.cpf = cpf.trim();
		this.nomeCompleto = nomeCompleto.trim();
		this.dataNascimento = dataNascimento.trim();
		this.municipioNotificacao = municipioNotificacao.trim();
		this.racaCor = racaCor.trim();
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf.trim();
	}


	public String getNomeCompleto() {
		return nomeCompleto;
	}


	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto.trim();
	}


	public String getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento.trim();
	}


	public String getMunicipioNotificacao() {
		return municipioNotificacao;
	}


	public void setMunicipioNotificacao(String municipioNotificacao) {
		this.municipioNotificacao = municipioNotificacao.trim();
	}

	public String getRacaCor() {
		return racaCor;
	}

	public void setRacaCor(String racaCor) {
		this.racaCor = racaCor.trim();
	}
}
