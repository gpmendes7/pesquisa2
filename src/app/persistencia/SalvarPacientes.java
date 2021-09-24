package app.persistencia;

import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Notificacao;
import modelo.Paciente;

public class SalvarPacientes {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	public static void main(String[] args) throws ParseException {
		emf = Persistence.createEntityManagerFactory("sus");
		em = emf.createEntityManager();
		
		List<Notificacao> notificacoes;
		
		int offset = 0;
		int totalPacientesSalvos = 0;
			
		while((notificacoes = obterNotificacoes(offset, 100000)).size() > 0) {
			em.getTransaction().begin();
			
			for (Notificacao notificacao : notificacoes) {
				Paciente paciente = gerarPaciente(notificacao);
				em.persist(paciente);
				notificacao.setPaciente(paciente);
				em.merge(notificacao);
				totalPacientesSalvos++;
			}
			
			em.flush();
			em.clear();
			em.getTransaction().commit();
			offset += notificacoes.size();
			System.out.println("offset: " + offset);
		}
		
		System.out.println("Total de pacientes salvos: " + totalPacientesSalvos);
		
		em.close();
		emf.close();
	}
	
	private static List<Notificacao> obterNotificacoes(int offset, int max) {
		return em.createQuery("from Notificacao n", Notificacao.class)
                .setFirstResult(offset)
                .setMaxResults(max)
                .getResultList();
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
	
}
