package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dataNascimento;
	private Date dataNotificacao;
	private Date dataInicioSintomas;
	private Date dataTeste;
	@Column(columnDefinition="TEXT")
	private String pUsuario;
	@Column(columnDefinition="TEXT")
	private String estrangeiro;
	@Column(columnDefinition="TEXT")
	private String profissionalSaude;
	@Column(columnDefinition="TEXT")
	private String profissionalSeguranca;
	@Column(columnDefinition="TEXT")
	private String cbo;
	@Column(columnDefinition="TEXT")
	private String cpf;
	@Column(columnDefinition="TEXT")
	private String cns;
	@Column(columnDefinition="TEXT")
	private String nomeCompleto;
	@Column(columnDefinition="TEXT")
	private String nomeMae;
	@Column(columnDefinition="TEXT")
	private String paisOrigem;
	@Column(columnDefinition="TEXT")
	private String sexo;
	@Column(columnDefinition="TEXT")
	private String racaCor;
	@Column(columnDefinition="TEXT")
	private String etnia;
	@Column(columnDefinition="TEXT")
	private String cep;
	@Column(columnDefinition="TEXT")
	private String passaporte;
	@Column(columnDefinition="TEXT")
	private String logradouro;
	@Column(columnDefinition="TEXT")
	private String numero;
	@Column(columnDefinition="TEXT")
	private String complemento;
	@Column(columnDefinition="TEXT")
	private String bairro;
	@Column(columnDefinition="TEXT")
	private String estado;
	@Column(columnDefinition="TEXT")
	private String municipio;
	@Column(columnDefinition="TEXT")
	private String telefoneContato;
	@Column(columnDefinition="TEXT")
	private String telefone;
	@Column(columnDefinition="TEXT")
	private String sintomas;
	@Column(columnDefinition="TEXT")
	private String outrosSintomas;
	@Column(columnDefinition="TEXT")
	private String condicoes;
	@Column(columnDefinition="TEXT")
	private String estadoTeste;
	@Column(columnDefinition="TEXT")
	private String tipoTeste;
	@Column(columnDefinition="TEXT")
	private String testeSorologico;
	private Date dataTesteSorologico;
	@Column(columnDefinition="TEXT")
	private String resultadoTeste;
	@Column(columnDefinition="TEXT")
	private String tipoTesteSorologico;
	@Column(columnDefinition="TEXT")
	private String resultadoTesteSorologicoIgA;
	@Column(columnDefinition="TEXT")
	private String resultadoTesteSorologicoIgG;
	@Column(columnDefinition="TEXT")
	private String resultadoTesteSorologicoIgM;
	@Column(columnDefinition="TEXT")
	private String resultadoTesteSorologicoTotais;
	@Column(columnDefinition="TEXT")
	private String numeroNotificacao;
	@Column(columnDefinition="TEXT")
	private String cnes;
	@Column(columnDefinition="TEXT")
	private String estadoNotificacao;
	@Column(columnDefinition="TEXT")
	private String municipioNotificacao;
	@Column(columnDefinition="TEXT")
	private String origem;
	@Column(columnDefinition="TEXT")
	private String nomeCompletoDesnormalizado;
	private Date createdAt;
	private Date updatedAt;
	@Column(columnDefinition="TEXT")
	private String sourceId;
	@Column(columnDefinition="TEXT")
	private String idade;
	@Column(columnDefinition="TEXT")
	private String classificacaoFinal;
	@Column(columnDefinition="TEXT")
	private String evolucaoCaso;
	private Date dataEncerramento;
	@Column(columnDefinition="TEXT")
	private String descricaoRacaCor;
	@Column(columnDefinition="TEXT")
	private String pUsuarioAlteracao;
	@Column(columnDefinition="TEXT")
	private String rpa;
	@Column(columnDefinition="TEXT")
	private String idOrigem;
	@Column(columnDefinition="TEXT")
	private String desnormalizarNome;
	@Column(columnDefinition="TEXT")
	private String timestamp;
	@Column(columnDefinition="TEXT")
	private String estadoIBGE;
	@Column(columnDefinition="TEXT")
	private String estadoNotificacaoIBGE;
	@Column(columnDefinition="TEXT")
	private String municipioIBGE;
	@Column(columnDefinition="TEXT")
	private String municipioNotificacaoIBGE;
	@Column(length = 80)
	private String notificadorCpf;
	@Column(length = 80)
	private String notificadorEmail;
	@Column(length = 80)
	private String notificadorNome;
	@Column(columnDefinition="TEXT")
	private String notificadorCNPJ;
	@Column(columnDefinition="TEXT")
	private String codigoClassificacaoFinal;
	@Column(columnDefinition="TEXT")
	private String codigoEvolucaoCaso;
	@Column(columnDefinition="TEXT")
	private String codigoEstadoTeste;
	@Column(columnDefinition="TEXT")
	private String labCnes;
	@Column(columnDefinition="TEXT")
	private String codigoCondicoes;
	@Column(columnDefinition="TEXT")
	private String codigoResultadoTeste;
	@Column(columnDefinition="TEXT")
	private String codigoSintomas;
	@Column(columnDefinition="TEXT")
	private String email;
	@Column(columnDefinition="TEXT")
	private String comunidadeTradicional;
	@Column(columnDefinition="TEXT")
	private String contemComunidadeTradicional;
	@Column(columnDefinition="TEXT")
	private String versaoFormulario;
	@Column(columnDefinition="TEXT")
	private String codigoResultadoTesteSorologicoIgM;
	@Column(columnDefinition="TEXT")
	private String codigoResultadoTesteSorologicoIgG;
	@Column(columnDefinition="TEXT")
	private String codigoTipoTesteSorologico;
	@Column(columnDefinition="TEXT")
	private String codigoTesteSorologico;
	@Column(columnDefinition="TEXT")
	private String codigoTipoTeste;
	@Column(columnDefinition="TEXT")
	private String codigoProfissionalSeguranca;
	@Column(columnDefinition="TEXT")
	private String codigoProfissionalSaude;
	@Column(columnDefinition="TEXT")
	private String codigoTemCpf;
	@Column(columnDefinition="TEXT")
	private String codigoSexo;
	@Column(columnDefinition="TEXT")
	private String codigoEstrangeiro;
	@Column(columnDefinition="TEXT")
	private String codigoCbo;
	@Column(columnDefinition="TEXT")
	private String codigoPaisOrigem;
	@Column(columnDefinition="TEXT")
	private String codigoResultadoTesteSorologicoTotais;
	@Column(columnDefinition="TEXT")
	private String codigoResultadoTesteSorologicoIgA;
	@Column(columnDefinition="TEXT")
	private String codigoComunidadeTradicional;
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
	private List<Notificacao> notificacoes;
	
	public Paciente() {
		super();
		notificacoes = new ArrayList<>();
	}
	
}
