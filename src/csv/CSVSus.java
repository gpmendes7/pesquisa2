package csv;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import modelo.Sus;


public class CSVSus {
	
	private static ColumnPositionMappingStrategy<Sus> strategy;

	static {
		strategy = new ColumnPositionMappingStrategy<Sus>();
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
	}

	public static void carregarCSV(String nomeArquivo) throws IOException {
		try (Reader reader = Files.newBufferedReader(Paths.get(nomeArquivo));) {
			CsvToBean<Sus> csvToBean = new CsvToBeanBuilder<Sus>(reader)
											.withMappingStrategy(strategy)
											.withType(Sus.class)
											.withSeparator(';')
											.withSkipLines(1)
											.build();
			//List<Sus> registros = csvToBean.parse();
			//return registros;
			
			Iterator<Sus> iterator = csvToBean.iterator();
			int count = 0;
			while(iterator.hasNext()) {
				Sus sus = iterator.next();
				System.out.println(sus);
			}
		}
	}

//	public static void criarCSV(String nomeArquivo, List<Sus> registros)
//			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
//		try (Writer writer = Files.newBufferedWriter(Paths.get(nomeArquivo));) {
//			StatefulBeanToCsv<Sus> beanToCsv = new StatefulBeanToCsvBuilder<Sus>(writer)
//					.withMappingStrategy(strategy).withSeparator(';').withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
//					.build();
//
//			beanToCsv.write(registros);
//		}
//	}

}
