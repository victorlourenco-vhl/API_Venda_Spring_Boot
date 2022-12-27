package aplicacao;

import dominio.Pessoa;

public class Programa {
	
	public static void main(String[] args) {
		
		Pessoa p1 = new Pessoa(1, "Victor Lourenço", "victor@gmail.com");
		Pessoa p2 = new Pessoa(2, "Iolanda da Silva", "iolanda@gmail.com");
		Pessoa p3 = new Pessoa(3, "Marly de Souza", "marly@gmail.com");
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		
	}

}
