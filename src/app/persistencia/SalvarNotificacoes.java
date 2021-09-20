package app.persistencia;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import modelo.Notificacao;
import modelo.Sus;


public class SalvarNotificacoes {
	
	private static ColumnPositionMappingStrategy<Sus> strategy = new ColumnPositionMappingStrategy<Sus>();;
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("sus");
	private static EntityManager em = emf.createEntityManager();

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
		
		em.getTransaction().begin();
		
		//lerCSVESalvarNoBanco("./arquivos/csv/sus_0.csv");
		//lerCSVESalvarNoBanco("./arquivos/csv/sus_1.csv");
		//lerCSVESalvarNoBanco("./arquivos/csv/sus_2.csv");
		//lerCSVESalvarNoBanco("./arquivos/csv/sus_3.csv");
		//lerCSVESalvarNoBanco("./arquivos/csv/sus_4.csv");
		//lerCSVESalvarNoBanco("./arquivos/csv/sus_5.csv");
		//lerCSVESalvarNoBanco("./arquivos/csv/sus_6.csv");
		lerCSVESalvarNoBanco("./arquivos/csv/sus_7.csv");
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
	public static void lerCSVESalvarNoBanco(String nomeArquivo) throws IOException, ParseException {
		try (Reader reader = Files.newBufferedReader(Paths.get(nomeArquivo));) {
			CsvToBean<Sus> csvToBean = new CsvToBeanBuilder<Sus>(reader)
											.withMappingStrategy(strategy)
											.withType(Sus.class)
											.withSeparator(';')
											.build();
			
			Iterator<Sus> iterator = csvToBean.iterator();
			
			long totalNotificacoesSalvas = 0;
	
			while(iterator.hasNext()) {
				Sus sus = iterator.next();
				em.persist(gerarNotificacao(sus));
				totalNotificacoesSalvas++;
			}
				
			System.out.println("Total de notificações salvas (" + nomeArquivo + "): " + totalNotificacoesSalvas);
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

}
