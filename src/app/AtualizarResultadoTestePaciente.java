package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Notificacao;
import modelo.Paciente;

public class AtualizarResultadoTestePaciente {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("sus"); 
		em = emf.createEntityManager();

		List<Paciente> pacientes;

		int offset = 0;

		while ((pacientes = obterPacientesComMaisDeUmaNotificacao(offset, 10000)).size() > 0) {
			em.getTransaction().begin();
	
			int total = pacientes.size();
			
			for (Paciente paciente : pacientes) {
				String resultadoTeste = paciente.getResultadoTeste();
				
				List<Notificacao> notificacoes = paciente.getNotificacoes();		
				for (Notificacao notificacao : notificacoes) {
					int grauPaciente = grauResultadoTeste(resultadoTeste);
					int grauNotificacao = grauResultadoTeste(notificacao.getResultadoTeste()) ;
					
					if(grauNotificacao > grauPaciente) {
						resultadoTeste = notificacao.getResultadoTeste();
					}
				}
				
				paciente.setResultadoTeste(resultadoTeste);
			}

			em.flush();
			em.clear(); 
			em.getTransaction().commit();
			offset += total;
			
			System.out.println("offset" + offset);
		}

		em.close(); 
		emf.close();
	}
	
	private static List<Paciente> obterPacientesComMaisDeUmaNotificacao(int offset, int max) {		
	    String jpql = "\n from Paciente p"
	    		   + "\n where exists ("
		           + "\n select count(n.numeroNotificacao), n.paciente"
		           + "\n from Notificacao n"
		           + "\n where n.paciente = p"
		           + "\n group by n.paciente"
	               + "\n having count(n.numeroNotificacao) > 1)";
	
		return em.createQuery(jpql, Paciente.class)
				 .setFirstResult(offset)
				 .setMaxResults(max)
				 .getResultList();
	}
	
	public static int grauResultadoTeste(String resultadoTeste) {
		if(resultadoTeste != null) {
			switch (resultadoTeste.toUpperCase()) {
					case "POSITIVO": 
						return 3;
					case "NEGATIVO": 
						return 2;
					case "INCONCLUSIVO OU INDETERMINADO": 
						return 1;
					default:
						return 0;
		   }
		}
		
		return 0;
	}

}
