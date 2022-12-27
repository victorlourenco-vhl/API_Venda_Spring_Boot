package aplicacao;

import dominio.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Programa {
	
	public static void main(String[] args) {
		
//		Pessoa p1 = new Pessoa(null, "Victor Lourenço", "victor@gmail.com");
//		Pessoa p2 = new Pessoa(null, "Iolanda da Silva", "iolanda@gmail.com");
//		Pessoa p3 = new Pessoa(null, "Marly de Souza", "marly@gmail.com");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		
//		em.getTransaction().begin();
//		em.persist(p1);
//		em.persist(p2);
//		em.persist(p3);
//		em.getTransaction().commit();
		
		Pessoa p = em.find(Pessoa.class, 2);
		
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		
		
		System.out.println("Pessoa excluida!");
		
		em.close();
		emf.close();
		
	}
	
	// PS: Um objeto monitorado é um objeto que acabou der ser inserido e a 
	// conexão ainda não foi fechada,  ou é um objeto que foi recuperado do banco.
	// Ex: em.find(obj);

}
