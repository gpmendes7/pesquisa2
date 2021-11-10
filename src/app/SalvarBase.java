package app;

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
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SusCSV;
import modelo.Notificacao;
import modelo.Paciente;

public class SalvarBase {

	
private static ColumnPositionMappingStrategy<SusCSV> strategy = new ColumnPositionMappingStrategy<SusCSV>();;
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("sus");
	private static EntityManager em = emf.createEntityManager();
	
	private static long totalNotificacoes = 0;
	private static long totalPacientes = 0;
	private static long lote = 1000;
	
	private static Map<String, Long> map = new HashMap<>();
	
	public static void main(String[] args)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
	
		strategy.setType(SusCSV.class);

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
			
			CsvToBean<SusCSV> csvToBean = new CsvToBeanBuilder<SusCSV>(reader)
											.withMappingStrategy(strategy)
											.withType(SusCSV.class)
											.withSeparator(';')
											.build();
			
			Iterator<SusCSV> iterator = csvToBean.iterator();
			
			while(iterator.hasNext()) {
				SusCSV susCSV = iterator.next();
				Notificacao notificacao = gerarNotificacao(susCSV);
				
				String nomeCompleto = removeAcentos(notificacao.getNomeCompleto().toUpperCase());
				String cpf = notificacao.getCpf().trim();
				Date dataNascimento = notificacao.getDataNascimento();
				
				String chave = nomeCompleto + cpf + dataNascimento;
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

				notificacao.setPaciente(paciente);
				em.persist(notificacao);
				totalNotificacoes++;
						
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
		
	public static Notificacao gerarNotificacao(SusCSV susCSV) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		Date dataNascimento = naoEhNuloENemVazio(susCSV.getDataNascimento()) ? sdf1.parse(susCSV.getDataNascimento()) : null;
		Date dataNotificacao = naoEhNuloENemVazio(susCSV.getDataNotificacao()) ? sdf1.parse(susCSV.getDataNotificacao()) : null;
		Date dataInicioSintomas = naoEhNuloENemVazio(susCSV.getDataInicioSintomas()) ? sdf1.parse(susCSV.getDataInicioSintomas()) : null;
		Date dataTeste = naoEhNuloENemVazio(susCSV.getDataTeste()) ? sdf1.parse(susCSV.getDataTeste()) : null;
		Date dataTesteSorologico = naoEhNuloENemVazio(susCSV.getDataTesteSorologico()) ? sdf1.parse(susCSV.getDataTesteSorologico()) : null;
		Date dataEncerramento = naoEhNuloENemVazio(susCSV.getDataEncerramento()) ? sdf1.parse(susCSV.getDataEncerramento()) : null;
		Date createdAt = naoEhNuloENemVazio(susCSV.getCreatedAt()) ? sdf1.parse(susCSV.getCreatedAt()) : null;
		Date updatedAt = naoEhNuloENemVazio(susCSV.getUpdatedAt()) ? sdf1.parse(susCSV.getUpdatedAt()) : null;
		
		return new Notificacao(dataNascimento, dataNotificacao, dataInicioSintomas, dataTeste, 
				susCSV.getpUsuario(), susCSV.getEstrangeiro(), susCSV.getProfissionalSaude(), susCSV.getProfissionalSeguranca(), 
				susCSV.getCbo(), susCSV.getCpf(), susCSV.getCns(), susCSV.getNomeCompleto(), 
				susCSV.getNomeMae(), susCSV.getPaisOrigem(), susCSV.getSexo(), susCSV.getRacaCor(), 
				susCSV.getEtnia(), susCSV.getCep(), susCSV.getPassaporte(), susCSV.getLogradouro(), 
				susCSV.getNumero(), susCSV.getComplemento(), susCSV.getBairro(), susCSV.getEstado(), 
				susCSV.getMunicipio(), susCSV.getTelefoneContato(), susCSV.getTelefone(), susCSV.getSintomas(),
				susCSV.getOutrosSintomas(), susCSV.getCondicoes(), susCSV.getEstadoTeste(), susCSV.getTipoTeste(), 
				susCSV.getTesteSorologico(), dataTesteSorologico, susCSV.getResultadoTeste(), susCSV.getTipoTesteSorologico(),
				susCSV.getResultadoTesteSorologicoIgA(), susCSV.getResultadoTesteSorologicoIgG(), susCSV.getResultadoTesteSorologicoIgM(), susCSV.getResultadoTesteSorologicoTotais(),
				susCSV.getNumeroNotificacao(), susCSV.getCnes(), susCSV.getEstadoNotificacao(), susCSV.getMunicipioNotificacao(),
				susCSV.getOrigem(), susCSV.getNomeCompletoDesnormalizado(), createdAt, updatedAt,
				susCSV.getSourceId(), susCSV.getIdade(), susCSV.getClassificacaoFinal(), susCSV.getEvolucaoCaso(),
				dataEncerramento, susCSV.getDescricaoRacaCor(), susCSV.getpUsuarioAlteracao(), susCSV.getRpa(),
				susCSV.getIdOrigem(), susCSV.getDesnormalizarNome(), susCSV.getTimestamp(), susCSV.getEstadoIBGE(),
				susCSV.getEstadoNotificacaoIBGE(), susCSV.getMunicipioIBGE(), susCSV.getMunicipioNotificacaoIBGE(), susCSV.getNotificadorCpf(),
				susCSV.getNotificadorEmail(), susCSV.getNotificadorNome(), susCSV.getNotificadorCNPJ(), susCSV.getCodigoClassificacaoFinal(),
				susCSV.getCodigoEvolucaoCaso(), susCSV.getCodigoEstadoTeste(), susCSV.getLabCnes(), susCSV.getCodigoCondicoes(),
				susCSV.getCodigoResultadoTeste(), susCSV.getCodigoSintomas(), susCSV.getEmail(), susCSV.getComunidadeTradicional(),
				susCSV.getContemComunidadeTradicional(), susCSV.getVersaoFormulario(), susCSV.getCodigoResultadoTesteSorologicoIgM(), susCSV.getCodigoResultadoTesteSorologicoIgG(),
				susCSV.getCodigoTipoTesteSorologico(), susCSV.getCodigoTesteSorologico(), susCSV.getCodigoTipoTeste(), susCSV.getCodigoProfissionalSeguranca(),
				susCSV.getCodigoProfissionalSaude(), susCSV.getCodigoTemCpf(), susCSV.getCodigoSexo(), susCSV.getCodigoEstrangeiro(),
				susCSV.getCodigoCbo(), susCSV.getCodigoPaisOrigem(), susCSV.getCodigoResultadoTesteSorologicoTotais(), susCSV.getCodigoResultadoTesteSorologicoIgA(),
				susCSV.getCodigoComunidadeTradicional());
	}
	
	public static Paciente gerarPaciente(Notificacao notificacao) {
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
}
