package csv;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import modelo.Paciente;

public class GerarCSVPacientes {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	private static ColumnPositionMappingStrategy<PacienteCSV> strategy;

	static {
		strategy = new ColumnPositionMappingStrategy<PacienteCSV>();
		strategy.setType(PacienteCSV.class);

		String[] colunas = { "cpf", "nomeCompleto", "dataNascimento", "municipioNotificacao"};
 
		strategy.setColumnMapping(colunas);
	}
	
	public static void main(String[] args) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		emf = Persistence.createEntityManagerFactory("sus"); 
		em = emf.createEntityManager();
		
		List<Paciente> pacientes = new ArrayList<Paciente>();
		
		List<PacienteCSV> pacientesCSV = new ArrayList<PacienteCSV>();
		pacientesCSV.add(new PacienteCSV("cpf", "nomeCompleto", "dataNascimento", "municipioNotificacao"));
		
		int offset = 0;
		  
	     while ((pacientes = obterPacientes(offset, 100000)).size() > 0) {
		  em.getTransaction().begin();
		  
		  for (Paciente paciente : pacientes) {
			  String cpf = paciente.getCpf();
			  String nomeCompleto = paciente.getNomeCompleto();
			  String dataNascimento = paciente.getDataNascimento() != null ? paciente.getDataNascimento().toString() : "";
			  String municipioNotificacao = paciente.getMunicipioNotificacao();
			  pacientesCSV.add(new PacienteCSV(cpf, nomeCompleto, dataNascimento, municipioNotificacao));
		  }
		  
		  em.flush();
		  em.clear(); 
		  em.getTransaction().commit(); 
		  offset += pacientes.size(); 
		}
	    
		criarCSV("./arquivos/csv/pacientes.csv", pacientesCSV);	
		
		em.close(); 
		emf.close();
	}
	

	private static List<Paciente> obterPacientes(int offset, int max) {		
	    String jpql = "\n from Paciente p";
	
		return em.createQuery(jpql, Paciente.class)
				 .setFirstResult(offset)
				 .setMaxResults(max)
				 .getResultList();
	}

	public static void criarCSV(String nomeArquivo, List<PacienteCSV> pacientes) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (Writer writer = Files.newBufferedWriter(Paths.get(nomeArquivo));) {
			StatefulBeanToCsv<PacienteCSV> beanToCsv = new StatefulBeanToCsvBuilder<PacienteCSV>(writer)
														.withMappingStrategy(strategy)
														.withSeparator(';')
														.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
														.build();
			beanToCsv.write(pacientes);
		}
	}
}
