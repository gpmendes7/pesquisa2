package app.persistencia;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import modelo.Notificacao;
import modelo.Paciente;

public class RemoverPacientesDuplicados {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("sus"); 
		em = emf.createEntityManager();

		List<Paciente> pacientes;

		int offset = 0;
		int totalPacientesExcluidos = 0;

		while ((pacientes = obterPacientes(offset, 10000)).size() > 0) {
			em.getTransaction().begin();
	
			int total = pacientes.size();
			
			System.out.println(pacientes.size());
			
			while (pacientes.size() > 0) {
				Paciente paciente = pacientes.remove(0);
				
				//List<Paciente> pacientesDuplicados = obterPacientesDuplicados(paciente, offset, 1000);
				
				List<Paciente> pacientesDuplicados = pacientes.stream() 
																.filter(p ->p.ehCopia(paciente)) 
																.collect(Collectors.toList());
		
				String sql1 = "\n delete from paciente where";
				String sql2 = "\n update notificacao set paciente_id = " + paciente.getId() +
						      "\n where";
				
				//System.out.println(pacientesDuplicados.size());

				if (pacientesDuplicados.size() > 0) {
					
					Paciente ultimo = pacientesDuplicados.remove(pacientesDuplicados.size() - 1);
					
					for (Paciente pacienteDuplicado : pacientesDuplicados) {
						sql1 += "\n id = " + pacienteDuplicado.getId() + " or ";
						sql2 += "\n paciente_id = " + pacienteDuplicado.getId() + " or ";
						List<Notificacao> notificacoes = paciente.getNotificacoes();
						notificacoes.stream().forEach(n -> { 
														n.setPaciente(paciente); 
														em.merge(n);
													  });
						em.remove(pacienteDuplicado);
					}

					sql1 += "\n id = " + ultimo.getId();
					sql2 += "\n paciente_id = " + ultimo.getId();

					Query query1 = em.createNativeQuery(sql1);
					Query query2 = em.createNativeQuery(sql2);
					
					//query2.executeUpdate();
					//totalPacientesExcluidos += query1.executeUpdate();		
					
					pacientes.remove(ultimo);
					pacientes.removeAll(pacientesDuplicados);
				}
				
			}

			em.flush();
			em.clear(); 
			em.getTransaction().commit();
			offset += total;
			System.out.println("offset: " + offset);
		}
		
		System.out.println("Total de pacientes excluídos (duplicadas removidas): " + totalPacientesExcluidos);

		em.close(); 
		emf.close();
	}

	private static List<Paciente> obterPacientesDuplicados(Paciente paciente, int offset, int max) {
		String jpql = "\n from Paciente p" 
	                + "\n where p.nomeCompleto = ?1"
				    + "\n and ( p.cpf = ?2 or p.dataNascimento = ?3)" 
	                + "\n and p.id != ?4";
		TypedQuery<Paciente> query = em.createQuery(jpql, Paciente.class)
									   .setFirstResult(offset)
									   .setMaxResults(max);
		query.setParameter(1, paciente.getNomeCompleto());
		query.setParameter(2, paciente.getCpf());
		query.setParameter(3, paciente.getDataNascimento());
		query.setParameter(4, paciente.getId());
		return query.getResultList();
	}
	
	private static List<Paciente> obterPacientes(int offset, int max) {
		return em.createQuery("from Paciente p", Paciente.class)
				 .setFirstResult(offset)
				 .setMaxResults(max)
				 .getResultList();
	}


	private static List<Paciente> obterPacientesComAlgumaDuplicidade(int offset, int max) {
		String jpql = "\n from Paciente p1" 
	                + "\n where exists ("
	                + "\n from Paciente p2"
	                + "\n where p1.nomeCompleto = p2.nomeCompleto" 
	                + "\n and (p1.cpf = p2.cpf or p1.dataNascimento = p2.dataNascimento)"
	                + "\n and p1.id != p2.id)";
	
		return em.createQuery(jpql, Paciente.class)
				 .setFirstResult(offset)
				 .setMaxResults(max)
				 .getResultList();
	}

}
