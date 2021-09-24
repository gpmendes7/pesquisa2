package app.persistencia;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import modelo.Notificacao;
import modelo.Paciente;
import modelo.Sus;

public class SalvarBase {

	
private static ColumnPositionMappingStrategy<Sus> strategy = new ColumnPositionMappingStrategy<Sus>();;
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("sus");
	private static EntityManager em = emf.createEntityManager();
	
	private static long totalNotificacoes = 0;
	private static long totalPacientes = 0;
	private static long lote = 1000;
	
	private static Map<String, Long> map = new HashMap<>();
	
	public static void main(String[] args)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
	
		strategy.setType(Sus.class);

		String[] colunas = { "dataNascimento", "dataNotificacao", "dataInicioSintomas", "dataTeste",
							 "pUsuario", "estrangeiro", "profissionalSaude", "profissionalSeguranca", "cbo",
							 "cpf", "cns", "nomeCompleto", "nomeMae", "paisOrigem", "sexo", "racaCor",
							 "etnia", "cep", "passaporte", "logradouro", "numero", "complemento",
							 "bairro", "estado", "municipio", "telefoneContato", "telefone", "sintomas",
							 "outrosSintomas", "condicoes", "estadoTeste", "tipoTeste", "testeSorologico",
							 "dataTesteSorologico", "resultadoTeste", "tipoTesteSorologico",
							 "resultadoTesteSorologicoIgA", "resultadoTesteSorologicoIgG", "resultadoTesteSorologicoIgM",
							 "resultadoTesteSorologicoTotais", "numeroNotificacao", "cnes", "estadoNotificacao",
							 "municipioNotificacao", "origem", "nomeCompletoDesnormalizado", "createdAt",
							 "updatedAt", "sourceId", "idade", "classificacaoFinal", "evolucaoCaso",
							 "dataEncerramento", "descricaoRacaCor", "pUsuarioAlteracao", "rpa", "idOrigem",
							 "desnormalizarNome", "timestamp", "estadoIBGE", "estadoNotificacaoIBGE",
							 "municipioIBGE", "municipioNotificacaoIBGE", "notificadorCpf", "notificadorEmail",
							 "notificadorNome", "notificadorCNPJ", "codigoClassificacaoFinal", "codigoEvolucaoCaso",
							 "codigoEstadoTeste", "labCnes", "codigoCondicoes", "codigoResultadoTeste",
							 "codigoSintomas", "email", "comunidadeTradicional", "contemComunidadeTradicional",
							 "versaoFormulario", "codigoResultadoTesteSorologicoIgM", "codigoResultadoTesteSorologicoIgG",
							 "codigoTipoTesteSorologico", "codigoTesteSorologico", "codigoTipoTeste",
						  	 "codigoProfissionalSeguranca", "codigoProfissionalSaude", "codigoTemCpf", "codigoSexo",
							 "codigoEstrangeiro", "codigoCbo", "codigoPaisOrigem",
							 "codigoResultadoTesteSorologicoTotais", "codigoResultadoTesteSorologicoIgA",
							 "codigoComunidadeTradicional" };
 
		strategy.setColumnMapping(colunas);

		lerCSVESalvarNoBanco("./arquivos/csv/sus_0.csv");
		lerCSVESalvarNoBanco("./arquivos/csv/sus_1.csv");
		lerCSVESalvarNoBanco("./arquivos/csv/sus_2.csv");
		lerCSVESalvarNoBanco("./arquivos/csv/sus_3.csv");
		lerCSVESalvarNoBanco("./arquivos/csv/sus_4.csv");
		lerCSVESalvarNoBanco("./arquivos/csv/sus_5.csv");
		lerCSVESalvarNoBanco("./arquivos/csv/sus_6.csv");
		lerCSVESalvarNoBanco("./arquivos/csv/sus_7.csv");
		
		System.out.println("Total de notificações: " + totalNotificacoes);
		System.out.println("Total de pacientes: " + totalPacientes);
		
		em.close();
		emf.close();
	}
	
	public static String removeAcentos(String string) {
		return Normalizer.normalize(string, Normalizer.Form.NFD)
						 .replaceAll("[^\\p{ASCII}]", "");
	}
	
	public static void lerCSVESalvarNoBanco(String nomeArquivo) throws IOException, ParseException {
		try (Reader reader = Files.newBufferedReader(Paths.get(nomeArquivo));) {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			CsvToBean<Sus> csvToBean = new CsvToBeanBuilder<Sus>(reader)
											.withMappingStrategy(strategy)
											.withType(Sus.class)
											.withSeparator(';')
											.build();
			
			Iterator<Sus> iterator = csvToBean.iterator();
			
			while(iterator.hasNext()) {
				Sus sus = iterator.next();
				Notificacao notificacao = gerarNotificacao(sus);
				
				String chave = removeAcentos(notificacao.getNomeCompleto().trim()) + notificacao.getCpf().trim() + notificacao.getDataNascimento();
				Long pacienteId = map.get(chave);
				Paciente paciente = null;
				
				if(pacienteId == null) {
					totalPacientes++;
					paciente = gerarPaciente(notificacao);
					paciente.setId(totalPacientes);
					em.persist(paciente);
					map.put(chave, paciente.getId());
				} else {
					paciente = new Paciente();
					paciente.setId(pacienteId);
				}
				
				/*
				List<Paciente> resultado = obterPacienteNotificacao(notificacao);
				Paciente paciente = null;
				
				if(resultado.size() == 0) {
					paciente = gerarPaciente(notificacao);
					em.persist(paciente);
				} else {
					paciente = resultado.get(0);
				}*/
				
				notificacao.setPaciente(paciente);
				em.persist(notificacao);
				totalNotificacoes++;
				
				System.out.println(totalNotificacoes);
				
				if(totalNotificacoes % lote == 0) {
					em.flush();
					em.clear();
					tx.commit();	
					tx.begin();
				}
			}
				
			tx.commit();					
		}
	}
	
	public static boolean naoEhNuloENemVazio(String string) {
		return string != null && !string.equals("");
	}
	
	public static Notificacao gerarNotificacao(Sus sus) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		Date dataNascimento = naoEhNuloENemVazio(sus.getDataNascimento()) ? sdf1.parse(sus.getDataNascimento()) : null;
		Date dataNotificacao = naoEhNuloENemVazio(sus.getDataNotificacao()) ? sdf1.parse(sus.getDataNotificacao()) : null;
		Date dataInicioSintomas = naoEhNuloENemVazio(sus.getDataInicioSintomas()) ? sdf1.parse(sus.getDataInicioSintomas()) : null;
		Date dataTeste = naoEhNuloENemVazio(sus.getDataTeste()) ? sdf1.parse(sus.getDataTeste()) : null;
		Date dataTesteSorologico = naoEhNuloENemVazio(sus.getDataTesteSorologico()) ? sdf1.parse(sus.getDataTesteSorologico()) : null;
		Date dataEncerramento = naoEhNuloENemVazio(sus.getDataEncerramento()) ? sdf1.parse(sus.getDataEncerramento()) : null;
		Date createdAt = naoEhNuloENemVazio(sus.getCreatedAt()) ? sdf1.parse(sus.getCreatedAt()) : null;
		Date updatedAt = naoEhNuloENemVazio(sus.getUpdatedAt()) ? sdf1.parse(sus.getUpdatedAt()) : null;
		
		return new Notificacao(dataNascimento, dataNotificacao, dataInicioSintomas, dataTeste, 
				sus.getpUsuario(), sus.getEstrangeiro(), sus.getProfissionalSaude(), sus.getProfissionalSeguranca(), 
				sus.getCbo(), sus.getCpf(), sus.getCns(), sus.getNomeCompleto(), 
				sus.getNomeMae(), sus.getPaisOrigem(), sus.getSexo(), sus.getRacaCor(), 
				sus.getEtnia(), sus.getCep(), sus.getPassaporte(), sus.getLogradouro(), 
				sus.getNumero(), sus.getComplemento(), sus.getBairro(), sus.getEstado(), 
				sus.getMunicipio(), sus.getTelefoneContato(), sus.getTelefone(), sus.getSintomas(),
				sus.getOutrosSintomas(), sus.getCondicoes(), sus.getEstadoTeste(), sus.getTipoTeste(), 
				sus.getTesteSorologico(), dataTesteSorologico, sus.getResultadoTeste(), sus.getTipoTesteSorologico(),
				sus.getResultadoTesteSorologicoIgA(), sus.getResultadoTesteSorologicoIgG(), sus.getResultadoTesteSorologicoIgM(), sus.getResultadoTesteSorologicoTotais(),
				sus.getNumeroNotificacao(), sus.getCnes(), sus.getEstadoNotificacao(), sus.getMunicipioNotificacao(),
				sus.getOrigem(), sus.getNomeCompletoDesnormalizado(), createdAt, updatedAt,
				sus.getSourceId(), sus.getIdade(), sus.getClassificacaoFinal(), sus.getEvolucaoCaso(),
				dataEncerramento, sus.getDescricaoRacaCor(), sus.getpUsuarioAlteracao(), sus.getRpa(),
				sus.getIdOrigem(), sus.getDesnormalizarNome(), sus.getTimestamp(), sus.getEstadoIBGE(),
				sus.getEstadoNotificacaoIBGE(), sus.getMunicipioIBGE(), sus.getMunicipioNotificacaoIBGE(), sus.getNotificadorCpf(),
				sus.getNotificadorEmail(), sus.getNotificadorNome(), sus.getNotificadorCNPJ(), sus.getCodigoClassificacaoFinal(),
				sus.getCodigoEvolucaoCaso(), sus.getCodigoEstadoTeste(), sus.getLabCnes(), sus.getCodigoCondicoes(),
				sus.getCodigoResultadoTeste(), sus.getCodigoSintomas(), sus.getEmail(), sus.getComunidadeTradicional(),
				sus.getContemComunidadeTradicional(), sus.getVersaoFormulario(), sus.getCodigoResultadoTesteSorologicoIgM(), sus.getCodigoResultadoTesteSorologicoIgG(),
				sus.getCodigoTipoTesteSorologico(), sus.getCodigoTesteSorologico(), sus.getCodigoTipoTeste(), sus.getCodigoProfissionalSeguranca(),
				sus.getCodigoProfissionalSaude(), sus.getCodigoTemCpf(), sus.getCodigoSexo(), sus.getCodigoEstrangeiro(),
				sus.getCodigoCbo(), sus.getCodigoPaisOrigem(), sus.getCodigoResultadoTesteSorologicoTotais(), sus.getCodigoResultadoTesteSorologicoIgA(),
				sus.getCodigoComunidadeTradicional());
	}
	
	public static Paciente gerarPaciente(Notificacao notificacao) throws ParseException {
		return new Paciente(notificacao.getDataNascimento(), notificacao.getDataNotificacao(), notificacao.getDataInicioSintomas(), notificacao.getDataTeste(), 
				notificacao.getpUsuario(), notificacao.getEstrangeiro(), notificacao.getProfissionalSaude(), notificacao.getProfissionalSeguranca(), 
				notificacao.getCbo(), notificacao.getCpf(), notificacao.getCns(), notificacao.getNomeCompleto(), 
				notificacao.getNomeMae(), notificacao.getPaisOrigem(), notificacao.getSexo(), notificacao.getRacaCor(), 
				notificacao.getEtnia(), notificacao.getCep(), notificacao.getPassaporte(), notificacao.getLogradouro(), 
				notificacao.getNumero(), notificacao.getComplemento(), notificacao.getBairro(), notificacao.getEstado(), 
				notificacao.getMunicipio(), notificacao.getTelefoneContato(), notificacao.getTelefone(), notificacao.getSintomas(),
				notificacao.getOutrosSintomas(), notificacao.getCondicoes(), notificacao.getEstadoTeste(), notificacao.getTipoTeste(), 
				notificacao.getTesteSorologico(), notificacao.getDataTesteSorologico(), notificacao.getResultadoTeste(), notificacao.getTipoTesteSorologico(),
				notificacao.getResultadoTesteSorologicoIgA(), notificacao.getResultadoTesteSorologicoIgG(), notificacao.getResultadoTesteSorologicoIgM(), notificacao.getResultadoTesteSorologicoTotais(),
				notificacao.getNumeroNotificacao(), notificacao.getCnes(), notificacao.getEstadoNotificacao(), notificacao.getMunicipioNotificacao(),
				notificacao.getOrigem(), notificacao.getNomeCompletoDesnormalizado(), notificacao.getCreatedAt(), notificacao.getUpdatedAt(),
				notificacao.getSourceId(), notificacao.getIdade(), notificacao.getClassificacaoFinal(), notificacao.getEvolucaoCaso(),
				notificacao.getDataEncerramento(), notificacao.getDescricaoRacaCor(), notificacao.getpUsuarioAlteracao(), notificacao.getRpa(),
				notificacao.getIdOrigem(), notificacao.getDesnormalizarNome(), notificacao.getTimestampNotificacao(), notificacao.getEstadoIBGE(),
				notificacao.getEstadoNotificacaoIBGE(), notificacao.getMunicipioIBGE(), notificacao.getMunicipioNotificacaoIBGE(), notificacao.getNotificadorCpf(),
				notificacao.getNotificadorEmail(), notificacao.getNotificadorNome(), notificacao.getNotificadorCNPJ(), notificacao.getCodigoClassificacaoFinal(),
				notificacao.getCodigoEvolucaoCaso(), notificacao.getCodigoEstadoTeste(), notificacao.getLabCnes(), notificacao.getCodigoCondicoes(),
				notificacao.getCodigoResultadoTeste(), notificacao.getCodigoSintomas(), notificacao.getEmail(), notificacao.getComunidadeTradicional(),
				notificacao.getContemComunidadeTradicional(), notificacao.getVersaoFormulario(), notificacao.getCodigoResultadoTesteSorologicoIgM(), notificacao.getCodigoResultadoTesteSorologicoIgG(),
				notificacao.getCodigoTipoTesteSorologico(), notificacao.getCodigoTesteSorologico(), notificacao.getCodigoTipoTeste(), notificacao.getCodigoProfissionalSeguranca(),
				notificacao.getCodigoProfissionalSaude(), notificacao.getCodigoTemCpf(), notificacao.getCodigoSexo(), notificacao.getCodigoEstrangeiro(),
				notificacao.getCodigoCbo(), notificacao.getCodigoPaisOrigem(), notificacao.getCodigoResultadoTesteSorologicoTotais(), notificacao.getCodigoResultadoTesteSorologicoIgA(),
				notificacao.getCodigoComunidadeTradicional());
	}
	

	
	private static List<Paciente> obterPacienteNotificacao(Notificacao notificacao) {
		String jpql = "\n from Paciente p" 
	                + "\n where p.nomeCompleto = ?1"
				    + "\n and ( p.cpf = ?2 or p.dataNascimento = ?3)";
		TypedQuery<Paciente> query = em.createQuery(jpql, Paciente.class)
									   .setFirstResult(0)
									   .setMaxResults(1);
		query.setParameter(1, removeAcentos(notificacao.getNomeCompleto().trim()));
		query.setParameter(2, notificacao.getCpf().trim());
		query.setParameter(3, notificacao.getDataNascimento());
		return query.getResultList();
	}
	
	
}
