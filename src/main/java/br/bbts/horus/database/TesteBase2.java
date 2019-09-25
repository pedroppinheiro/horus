package br.bbts.horus.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TesteBase2 {

	public void teste() {

//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("testes");
//		EntityManager em = emf.createEntityManager();

		// em.createQuery("SELECT c FROM CriticidadeArquivo
		// c").getResultList().forEach((o) -> {
		// System.out.println(o);
		// });

		// List<CriticidadeArquivo> arquivos = em.createQuery("from CriticidadeArquivo
		// ca", CriticidadeArquivo.class)
		// .getResultList();

		// arquivos.forEach((a) -> {
		// System.out.println(a);
		// });

//		em.close();
//		emf.close();
	}

	public void teste2() throws Exception {
		Database database = Database.newConnection();
		ResultSet rset = database.execute("SELECT * FROM CRITICIDADE_ARQUIVO");
		System.out.println("Total number of records = " + database.getRowCount());
		System.out.println("The records selected are:");
		while (rset.next()) { // Move the cursor to the next row, return false if no more row
			System.out.println(rset.getString("NOME"));
		}

		database.close();
	}

	public static void main(String args[]) {
		TesteBase2 tb = new TesteBase2();
		try {
			tb.teste2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
