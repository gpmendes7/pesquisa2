package csv;

import com.opencsv.bean.CsvBindByName;

public class SusCSV {
	
	private String dataNascimento;
	private String dataNotificacao;
	private String dataInicioSintomas;
	private String dataTeste;
	@CsvBindByName(column = "_p_usuario")
	private String pUsuario;
	private String estrangeiro;
	private String profissionalSaude;
	private String profissionalSeguranca;
	private String cbo;
	private String cpf;
	private String cns;
	private String nomeCompleto;
	private String nomeMae;
	private String paisOrigem;
	private String sexo;
	private String racaCor;
	private String etnia;
	private String cep;
	private String passaporte;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String estado;
	private String municipio;
	private String telefoneContato;
	private String telefone;
	private String sintomas;
	private String outrosSintomas;
	private String condicoes;
	private String estadoTeste;
	private String tipoTeste;
	private String testeSorologico;
	private String dataTesteSorologico;
	private String resultadoTeste;
	private String tipoTesteSorologico;
	private String resultadoTesteSorologicoIgA;
	private String resultadoTesteSorologicoIgG;
	private String resultadoTesteSorologicoIgM;
	private String resultadoTesteSorologicoTotais;
	private String numeroNotificacao;
	private String cnes;
	private String estadoNotificacao;
	private String municipioNotificacao;
	private String origem;
	private String nomeCompletoDesnormalizado;
	@CsvBindByName(column = "_created_at")
	private String createdAt;
	@CsvBindByName(column = "_updated_at")
	private String updatedAt;
	@CsvBindByName(column = "source_id")
	private String sourceId;
	private String idade;
	private String classificacaoFinal;
	private String evolucaoCaso;
	private String dataEncerramento;
	private String descricaoRacaCor;
	@CsvBindByName(column = "_p_usuarioAlteracao")
	private String pUsuarioAlteracao;
	private String rpa;
	private String idOrigem;
	private String desnormalizarNome;
	@CsvBindByName(column = "@timestamp")
	private String timestamp;
	private String estadoIBGE;
	private String estadoNotificacaoIBGE;
	private String municipioIBGE;
	private String municipioNotificacaoIBGE;
	private String notificadorCpf;
	private String notificadorEmail;
	private String notificadorNome;
	private String notificadorCNPJ;
	private String codigoClassificacaoFinal;
	private String codigoEvolucaoCaso;
	private String codigoEstadoTeste;
	private String labCnes;
	private String codigoCondicoes;
	private String codigoResultadoTeste;
	private String codigoSintomas;
	private String email;
	private String comunidadeTradicional;
	private String contemComunidadeTradicional;
	private String versaoFormulario;
	private String codigoResultadoTesteSorologicoIgM;
	private String codigoResultadoTesteSorologicoIgG;
	private String codigoTipoTesteSorologico;
	private String codigoTesteSorologico;
	private String codigoTipoTeste;
	private String codigoProfissionalSeguranca;
	private String codigoProfissionalSaude;
	private String codigoTemCpf;
	private String codigoSexo;
	private String codigoEstrangeiro;
	private String codigoCbo;
	private String codigoPaisOrigem;
	private String codigoResultadoTesteSorologicoTotais;
	private String codigoResultadoTesteSorologicoIgA;
	private String codigoComunidadeTradicional;
	
	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento.trim();
	}

	public String getDataNotificacao() {
		return dataNotificacao;
	}

	public void setDataNotificacao(String dataNotificacao) {
		this.dataNotificacao = dataNotificacao.trim();
	}

	public String getDataInicioSintomas() {
		return dataInicioSintomas;
	}

	public void setDataInicioSintomas(String dataInicioSintomas) {
		this.dataInicioSintomas = dataInicioSintomas.trim();
	}

	public String getDataTeste() {
		return dataTeste;
	}

	public void setDataTeste(String dataTeste) {
		this.dataTeste = dataTeste.trim();
	}

	public String getpUsuario() {
		return pUsuario;
	}

	public void setpUsuario(String pUsuario) {
		this.pUsuario = pUsuario.trim();
	}

	public String getEstrangeiro() {
		return estrangeiro;
	}

	public void setEstrangeiro(String estrangeiro) {
		this.estrangeiro = estrangeiro.trim();
	}

	public String getProfissionalSaude() {
		return profissionalSaude;
	}

	public void setProfissionalSaude(String profissionalSaude) {
		this.profissionalSaude = profissionalSaude.trim();
	}

	public String getProfissionalSeguranca() {
		return profissionalSeguranca;
	}

	public void setProfissionalSeguranca(String profissionalSeguranca) {
		this.profissionalSeguranca = profissionalSeguranca.trim();
	}

	public String getCbo() {
		return cbo;
	}

	public void setCbo(String cbo) {
		this.cbo = cbo.trim();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf.trim();
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns.trim();
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto.trim();
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae.trim();
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem.trim();
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo.trim();
	}

	public String getRacaCor() {
		return racaCor;
	}

	public void setRacaCor(String racaCor) {
		this.racaCor = racaCor.trim();
	}

	public String getEtnia() {
		return etnia;
	}

	public void setEtnia(String etnia) {
		this.etnia = etnia.trim();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep.trim();
	}

	public String getPassaporte() {
		return passaporte;
	}

	public void setPassaporte(String passaporte) {
		this.passaporte = passaporte.trim();
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro.trim();
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero.trim();
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento.trim();
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro.trim();
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado.trim();
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio.trim();
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato.trim();
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone.trim();
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public String getOutrosSintomas() {
		return outrosSintomas;
	}

	public void setOutrosSintomas(String outrosSintomas) {
		this.outrosSintomas = outrosSintomas.trim();
	}

	public String getCondicoes() {
		return condicoes;
	}

	public void setCondicoes(String condicoes) {
		this.condicoes = condicoes.trim();
	}

	public String getEstadoTeste() {
		return estadoTeste;
	}

	public void setEstadoTeste(String estadoTeste) {
		this.estadoTeste = estadoTeste.trim();
	}

	public String getTipoTeste() {
		return tipoTeste;
	}

	public void setTipoTeste(String tipoTeste) {
		this.tipoTeste = tipoTeste.trim();
	}

	public String getTesteSorologico() {
		return testeSorologico;
	}

	public void setTesteSorologico(String testeSorologico) {
		this.testeSorologico = testeSorologico.trim();
	}

	public String getDataTesteSorologico() {
		return dataTesteSorologico;
	}

	public void setDataTesteSorologico(String dataTesteSorologico) {
		this.dataTesteSorologico = dataTesteSorologico.trim();
	}

	public String getResultadoTeste() {
		return resultadoTeste;
	}

	public void setResultadoTeste(String resultadoTeste) {
		this.resultadoTeste = resultadoTeste.trim();
	}

	public String getTipoTesteSorologico() {
		return tipoTesteSorologico;
	}

	public void setTipoTesteSorologico(String tipoTesteSorologico) {
		this.tipoTesteSorologico = tipoTesteSorologico.trim();
	}

	public String getResultadoTesteSorologicoIgA() {
		return resultadoTesteSorologicoIgA;
	}

	public void setResultadoTesteSorologicoIgA(String resultadoTesteSorologicoIgA) {
		this.resultadoTesteSorologicoIgA = resultadoTesteSorologicoIgA.trim();
	}

	public String getResultadoTesteSorologicoIgG() {
		return resultadoTesteSorologicoIgG;
	}

	public void setResultadoTesteSorologicoIgG(String resultadoTesteSorologicoIgG) {
		this.resultadoTesteSorologicoIgG = resultadoTesteSorologicoIgG.trim();
	}

	public String getResultadoTesteSorologicoIgM() {
		return resultadoTesteSorologicoIgM;
	}

	public void setResultadoTesteSorologicoIgM(String resultadoTesteSorologicoIgM) {
		this.resultadoTesteSorologicoIgM = resultadoTesteSorologicoIgM.trim();
	}

	public String getResultadoTesteSorologicoTotais() {
		return resultadoTesteSorologicoTotais;
	}

	public void setResultadoTesteSorologicoTotais(String resultadoTesteSorologicoTotais) {
		this.resultadoTesteSorologicoTotais = resultadoTesteSorologicoTotais.trim();
	}

	public String getNumeroNotificacao() {
		return numeroNotificacao;
	}

	public void setNumeroNotificacao(String numeroNotificacao) {
		this.numeroNotificacao = numeroNotificacao.trim();
	}

	public String getCnes() {
		return cnes;
	}

	public void setCnes(String cnes) {
		this.cnes = cnes.trim();
	}

	public String getEstadoNotificacao() {
		return estadoNotificacao;
	}

	public void setEstadoNotificacao(String estadoNotificacao) {
		this.estadoNotificacao = estadoNotificacao.trim();
	}

	public String getMunicipioNotificacao() {
		return municipioNotificacao;
	}

	public void setMunicipioNotificacao(String municipioNotificacao) {
		this.municipioNotificacao = municipioNotificacao.trim();
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem.trim();
	}

	public String getNomeCompletoDesnormalizado() {
		return nomeCompletoDesnormalizado;
	}

	public void setNomeCompletoDesnormalizado(String nomeCompletoDesnormalizado) {
		this.nomeCompletoDesnormalizado = nomeCompletoDesnormalizado.trim();
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt.trim();
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt.trim();
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId.trim();
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade.trim();
	}

	public String getClassificacaoFinal() {
		return classificacaoFinal;
	}

	public void setClassificacaoFinal(String classificacaoFinal) {
		this.classificacaoFinal = classificacaoFinal.trim();
	}

	public String getEvolucaoCaso() {
		return evolucaoCaso;
	}

	public void setEvolucaoCaso(String evolucaoCaso) {
		this.evolucaoCaso = evolucaoCaso.trim();
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento.trim();
	}

	public String getDescricaoRacaCor() {
		return descricaoRacaCor;
	}

	public void setDescricaoRacaCor(String descricaoRacaCor) {
		this.descricaoRacaCor = descricaoRacaCor.trim();
	}

	public String getpUsuarioAlteracao() {
		return pUsuarioAlteracao;
	}

	public void setpUsuarioAlteracao(String pUsuarioAlteracao) {
		this.pUsuarioAlteracao = pUsuarioAlteracao.trim();
	}

	public String getRpa() {
		return rpa;
	}

	public void setRpa(String rpa) {
		this.rpa = rpa.trim();
	}

	public String getIdOrigem() {
		return idOrigem;
	}

	public void setIdOrigem(String idOrigem) {
		this.idOrigem = idOrigem.trim();
	}

	public String getDesnormalizarNome() {
		return desnormalizarNome;
	}

	public void setDesnormalizarNome(String desnormalizarNome) {
		this.desnormalizarNome = desnormalizarNome.trim();
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp.trim();
	}

	public String getEstadoIBGE() {
		return estadoIBGE;
	}

	public void setEstadoIBGE(String estadoIBGE) {
		this.estadoIBGE = estadoIBGE.trim();
	}

	public String getEstadoNotificacaoIBGE() {
		return estadoNotificacaoIBGE;
	}

	public void setEstadoNotificacaoIBGE(String estadoNotificacaoIBGE) {
		this.estadoNotificacaoIBGE = estadoNotificacaoIBGE.trim();
	}

	public String getMunicipioIBGE() {
		return municipioIBGE;
	}

	public void setMunicipioIBGE(String municipioIBGE) {
		this.municipioIBGE = municipioIBGE.trim();
	}

	public String getMunicipioNotificacaoIBGE() {
		return municipioNotificacaoIBGE;
	}

	public void setMunicipioNotificacaoIBGE(String municipioNotificacaoIBGE) {
		this.municipioNotificacaoIBGE = municipioNotificacaoIBGE.trim();
	}

	public String getNotificadorCpf() {
		return notificadorCpf;
	}

	public void setNotificadorCpf(String notificadorCpf) {
		this.notificadorCpf = notificadorCpf.trim();
	}

	public String getNotificadorEmail() {
		return notificadorEmail;
	}

	public void setNotificadorEmail(String notificadorEmail) {
		this.notificadorEmail = notificadorEmail.trim();
	}

	public String getNotificadorNome() {
		return notificadorNome;
	}

	public void setNotificadorNome(String notificadorNome) {
		this.notificadorNome = notificadorNome.trim();
	}

	public String getNotificadorCNPJ() {
		return notificadorCNPJ;
	}

	public void setNotificadorCNPJ(String notificadorCNPJ) {
		this.notificadorCNPJ = notificadorCNPJ.trim();
	}

	public String getCodigoClassificacaoFinal() {
		return codigoClassificacaoFinal;
	}

	public void setCodigoClassificacaoFinal(String codigoClassificacaoFinal) {
		this.codigoClassificacaoFinal = codigoClassificacaoFinal.trim();
	}

	public String getCodigoEvolucaoCaso() {
		return codigoEvolucaoCaso;
	}

	public void setCodigoEvolucaoCaso(String codigoEvolucaoCaso) {
		this.codigoEvolucaoCaso = codigoEvolucaoCaso.trim();
	}

	public String getCodigoEstadoTeste() {
		return codigoEstadoTeste;
	}

	public void setCodigoEstadoTeste(String codigoEstadoTeste) {
		this.codigoEstadoTeste = codigoEstadoTeste.trim();
	}

	public String getLabCnes() {
		return labCnes;
	}

	public void setLabCnes(String labCnes) {
		this.labCnes = labCnes.trim();
	}

	public String getCodigoCondicoes() {
		return codigoCondicoes;
	}

	public void setCodigoCondicoes(String codigoCondicoes) {
		this.codigoCondicoes = codigoCondicoes.trim();
	}

	public String getCodigoResultadoTeste() {
		return codigoResultadoTeste;
	}

	public void setCodigoResultadoTeste(String codigoResultadoTeste) {
		this.codigoResultadoTeste = codigoResultadoTeste.trim();
	}

	public String getCodigoSintomas() {
		return codigoSintomas;
	}

	public void setCodigoSintomas(String codigoSintomas) {
		this.codigoSintomas = codigoSintomas.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

	public String getComunidadeTradicional() {
		return comunidadeTradicional;
	}

	public void setComunidadeTradicional(String comunidadeTradicional) {
		this.comunidadeTradicional = comunidadeTradicional.trim();
	}

	public String getContemComunidadeTradicional() {
		return contemComunidadeTradicional;
	}

	public void setContemComunidadeTradicional(String contemComunidadeTradicional) {
		this.contemComunidadeTradicional = contemComunidadeTradicional.trim();
	}

	public String getVersaoFormulario() {
		return versaoFormulario;
	}

	public void setVersaoFormulario(String versaoFormulario) {
		this.versaoFormulario = versaoFormulario.trim();
	}

	public String getCodigoResultadoTesteSorologicoIgM() {
		return codigoResultadoTesteSorologicoIgM;
	}

	public void setCodigoResultadoTesteSorologicoIgM(String codigoResultadoTesteSorologicoIgM) {
		this.codigoResultadoTesteSorologicoIgM = codigoResultadoTesteSorologicoIgM.trim();
	}

	public String getCodigoResultadoTesteSorologicoIgG() {
		return codigoResultadoTesteSorologicoIgG;
	}

	public void setCodigoResultadoTesteSorologicoIgG(String codigoResultadoTesteSorologicoIgG) {
		this.codigoResultadoTesteSorologicoIgG = codigoResultadoTesteSorologicoIgG.trim();
	}

	public String getCodigoTipoTesteSorologico() {
		return codigoTipoTesteSorologico;
	}

	public void setCodigoTipoTesteSorologico(String codigoTipoTesteSorologico) {
		this.codigoTipoTesteSorologico = codigoTipoTesteSorologico.trim();
	}

	public String getCodigoTesteSorologico() {
		return codigoTesteSorologico;
	}

	public void setCodigoTesteSorologico(String codigoTesteSorologico) {
		this.codigoTesteSorologico = codigoTesteSorologico.trim();
	}

	public String getCodigoTipoTeste() {
		return codigoTipoTeste;
	}

	public void setCodigoTipoTeste(String codigoTipoTeste) {
		this.codigoTipoTeste = codigoTipoTeste.trim();
	}

	public String getCodigoProfissionalSeguranca() {
		return codigoProfissionalSeguranca;
	}

	public void setCodigoProfissionalSeguranca(String codigoProfissionalSeguranca) {
		this.codigoProfissionalSeguranca = codigoProfissionalSeguranca.trim();
	}

	public String getCodigoProfissionalSaude() {
		return codigoProfissionalSaude;
	}

	public void setCodigoProfissionalSaude(String codigoProfissionalSaude) {
		this.codigoProfissionalSaude = codigoProfissionalSaude.trim();
	}

	public String getCodigoTemCpf() {
		return codigoTemCpf;
	}

	public void setCodigoTemCpf(String codigoTemCpf) {
		this.codigoTemCpf = codigoTemCpf.trim();
	}

	public String getCodigoSexo() {
		return codigoSexo;
	}

	public void setCodigoSexo(String codigoSexo) {
		this.codigoSexo = codigoSexo.trim();
	}

	public String getCodigoEstrangeiro() {
		return codigoEstrangeiro;
	}

	public void setCodigoEstrangeiro(String codigoEstrangeiro) {
		this.codigoEstrangeiro = codigoEstrangeiro.trim();
	}

	public String getCodigoCbo() {
		return codigoCbo;
	}

	public void setCodigoCbo(String codigoCbo) {
		this.codigoCbo = codigoCbo.trim();
	}

	public String getCodigoPaisOrigem() {
		return codigoPaisOrigem;
	}

	public void setCodigoPaisOrigem(String codigoPaisOrigem) {
		this.codigoPaisOrigem = codigoPaisOrigem.trim();
	}

	public String getCodigoResultadoTesteSorologicoTotais() {
		return codigoResultadoTesteSorologicoTotais;
	}

	public void setCodigoResultadoTesteSorologicoTotais(String codigoResultadoTesteSorologicoTotais) {
		this.codigoResultadoTesteSorologicoTotais = codigoResultadoTesteSorologicoTotais.trim();
	}

	public String getCodigoResultadoTesteSorologicoIgA() {
		return codigoResultadoTesteSorologicoIgA;
	}

	public void setCodigoResultadoTesteSorologicoIgA(String codigoResultadoTesteSorologicoIgA) {
		this.codigoResultadoTesteSorologicoIgA = codigoResultadoTesteSorologicoIgA.trim();
	}

	public String getCodigoComunidadeTradicional() {
		return codigoComunidadeTradicional;
	}

	public void setCodigoComunidadeTradicional(String codigoComunidadeTradicional) {
		this.codigoComunidadeTradicional = codigoComunidadeTradicional.trim();
	}

	@Override
	public String toString() {
		return "Sus [dataNascimento=" + dataNascimento + ", dataNotificacao=" + dataNotificacao
				+ ", dataInicioSintomas=" + dataInicioSintomas + ", dataTeste=" + dataTeste + ", pUsuario=" + pUsuario
				+ ", estrangeiro=" + estrangeiro + ", profissionalSaude=" + profissionalSaude
				+ ", profissionalSeguranca=" + profissionalSeguranca + ", cbo=" + cbo + ", cpf=" + cpf + ", cns=" + cns
				+ ", nomeCompleto=" + nomeCompleto + ", nomeMae=" + nomeMae + ", paisOrigem=" + paisOrigem + ", sexo="
				+ sexo + ", racaCor=" + racaCor + ", etnia=" + etnia + ", cep=" + cep + ", passaporte=" + passaporte
				+ ", logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento + ", bairro="
				+ bairro + ", estado=" + estado + ", municipio=" + municipio + ", telefoneContato=" + telefoneContato
				+ ", telefone=" + telefone + ", sintomas=" + sintomas + ", outrosSintomas=" + outrosSintomas
				+ ", condicoes=" + condicoes + ", estadoTeste=" + estadoTeste + ", tipoTeste=" + tipoTeste
				+ ", testeSorologico=" + testeSorologico + ", dataTesteSorologico=" + dataTesteSorologico
				+ ", resultadoTeste=" + resultadoTeste + ", tipoTesteSorologico=" + tipoTesteSorologico
				+ ", resultadoTesteSorologicoIgA=" + resultadoTesteSorologicoIgA + ", resultadoTesteSorologicoIgG="
				+ resultadoTesteSorologicoIgG + ", resultadoTesteSorologicoIgM=" + resultadoTesteSorologicoIgM
				+ ", resultadoTesteSorologicoTotais=" + resultadoTesteSorologicoTotais + ", numeroNotificacao="
				+ numeroNotificacao + ", cnes=" + cnes + ", estadoNotificacao=" + estadoNotificacao
				+ ", municipioNotificacao=" + municipioNotificacao + ", origem=" + origem
				+ ", nomeCompletoDesnormalizado=" + nomeCompletoDesnormalizado + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", sourceId=" + sourceId + ", idade=" + idade + ", classificacaoFinal="
				+ classificacaoFinal + ", evolucaoCaso=" + evolucaoCaso + ", dataEncerramento=" + dataEncerramento
				+ ", descricaoRacaCor=" + descricaoRacaCor + ", pUsuarioAlteracao=" + pUsuarioAlteracao + ", rpa=" + rpa
				+ ", idOrigem=" + idOrigem + ", desnormalizarNome=" + desnormalizarNome + ", timestamp=" + timestamp
				+ ", estadoIBGE=" + estadoIBGE + ", estadoNotificacaoIBGE=" + estadoNotificacaoIBGE + ", municipioIBGE="
				+ municipioIBGE + ", municipioNotificacaoIBGE=" + municipioNotificacaoIBGE + ", notificadorCpf="
				+ notificadorCpf + ", notificadorEmail=" + notificadorEmail + ", notificadorNome=" + notificadorNome
				+ ", notificadorCNPJ=" + notificadorCNPJ + ", codigoClassificacaoFinal=" + codigoClassificacaoFinal
				+ ", codigoEvolucaoCaso=" + codigoEvolucaoCaso + ", codigoEstadoTeste=" + codigoEstadoTeste
				+ ", labCnes=" + labCnes + ", codigoCondicoes=" + codigoCondicoes + ", codigoResultadoTeste="
				+ codigoResultadoTeste + ", codigoSintomas=" + codigoSintomas + ", email=" + email
				+ ", comunidadeTradicional=" + comunidadeTradicional + ", contemComunidadeTradicional="
				+ contemComunidadeTradicional + ", versaoFormulario=" + versaoFormulario
				+ ", codigoResultadoTesteSorologicoIgM=" + codigoResultadoTesteSorologicoIgM
				+ ", codigoResultadoTesteSorologicoIgG=" + codigoResultadoTesteSorologicoIgG
				+ ", codigoTipoTesteSorologico=" + codigoTipoTesteSorologico + ", codigoTesteSorologico="
				+ codigoTesteSorologico + ", codigoTipoTeste=" + codigoTipoTeste + ", codigoProfissionalSeguranca="
				+ codigoProfissionalSeguranca + ", codigoProfissionalSaude=" + codigoProfissionalSaude
				+ ", codigoTemCpf=" + codigoTemCpf + ", codigoSexo=" + codigoSexo + ", codigoEstrangeiro="
				+ codigoEstrangeiro + ", codigoCbo=" + codigoCbo + ", codigoPaisOrigem=" + codigoPaisOrigem
				+ ", codigoResultadoTesteSorologicoTotais=" + codigoResultadoTesteSorologicoTotais
				+ ", codigoResultadoTesteSorologicoIgA=" + codigoResultadoTesteSorologicoIgA
				+ ", codigoComunidadeTradicional=" + codigoComunidadeTradicional + "]";
	}
	
}
