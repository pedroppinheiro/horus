package managers;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import config.Constantes;

public class DatabaseManager {

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private ResultSetMetaData resultSetMetaData;
	private static DatabaseManager databaseManager;

	public static final Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());
	
	/**
	 * Usado para testar se a classe está conseguindo se conectar ao banco com sucesso
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String args[]) throws SQLException {
		DatabaseManager databaseManager = DatabaseManager.createDatabaseManager();
		
		Connection connection = databaseManager.getConnection();
		if (connection != null) {
			DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
			System.out.println("Driver name: " + dm.getDriverName());
			System.out.println("Driver version: " + dm.getDriverVersion());
			System.out.println("Product name: " + dm.getDatabaseProductName());
			System.out.println("Product version: " + dm.getDatabaseProductVersion());
		}
	}

	private DatabaseManager() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			String connectionURL = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;user=%s;password=%s",
					Constantes.DATABASE_CONNECTION_HOST,
					Constantes.DATABASE_CONNECTION_PORT,
					Constantes.DATABASE_NAME,
					Constantes.DATABASE_CONNECTION_USERNAME,
					Constantes.DATABASE_CONNECTION_PASSWORD);
			
			connection = DriverManager.getConnection(connectionURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DatabaseManager createDatabaseManager() {
		try {
			if (databaseManager == null || databaseManager.connection.isClosed()) {
				databaseManager = new DatabaseManager();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return databaseManager;
	}

	public ResultSet execute(String sql) {
		System.out.println("Executando sql: " + sql);
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			LOGGER.info("Executando SQL: " + sql);
			statement.execute(sql);
			resultSet = statement.getResultSet();
			resultSetMetaData = resultSet != null ? resultSet.getMetaData() : null;
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getColumnCount(ResultSet resultSet) {
		try {
			return resultSetMetaData.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getRowCount(ResultSet resultSet) {
		try {
			resultSet.last();
			int count = resultSet.getRow();
			resultSet.beforeFirst();
			return count;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				resultSet.beforeFirst();
			} catch (SQLException exp) {
				exp.printStackTrace();
			}
		}
		return 0;
	}

	public void executeUpdate(String sql) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			LOGGER.info("Executando SQL: " + sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getOnlyResultAsString() {
		try {
			if (resultSet.next()) {
				String result = resultSet.getString(1);
				resultSet.beforeFirst();
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
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public ResultSetMetaData getResultSetMetaData() {
		return resultSetMetaData;
	}

	public void setResultSetMetaData(ResultSetMetaData resultSetMetaData) {
		this.resultSetMetaData = resultSetMetaData;
	}
}
