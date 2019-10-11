package br.horus.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.horus.constantes.Constantes;

public class Database {

	private Connection conn;
	private Statement stmt;
	private ResultSet rset;
	private ResultSetMetaData rsmd;
	private static Database database;

	private Database() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(Constantes.DATABASE_CONNECTION_URL, Constantes.DATABASE_CONNECTION_USERNAME, Constantes.DATABASE_CONNECTION_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Database newConnection() {
		try {
			if (database == null || database.conn.isClosed()) {
				database = new Database();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return database;
	}

	public ResultSet execute(String sql) {
		System.out.println("Executando sql: " + sql);
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.execute(sql);
			rset = stmt.getResultSet();
			rsmd = rset.getMetaData();
			return rset;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getColumnCount() {
		try {
			return rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getRowCount() {
		try {
			rset.last();
			int count = rset.getRow();
			rset.beforeFirst();
			return count;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				rset.beforeFirst();
			} catch (SQLException exp) {
				exp.printStackTrace();
			}
		}
		return 0;
	}

	public void executeUpdate(String sql) {
		System.out.println("Executando sql: " + sql);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getOnlyResult() {
		try {
			if (rset.next()) {
				String result = rset.getString(1);
				rset.beforeFirst();
				return result;
			}
			return "";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public void close() {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
