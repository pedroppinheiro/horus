package enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enum utilizado para guardar os clientes da GOL. 
 * O nome dos cliente representa o texto que é apresentado na tela inicial após o usuário realizar o login
 * @author pedro
 *
 */
public enum ClientesEnum {
	COREN(26, "0026 CONSELHO REGIONAL DE ENFERMAGEM PARANÁ"),
	ALBA(80, "0080 ALBA"),
	LONDRINA(20, "LONDRINA"),
	TCE(25, "TCE"),
	CFMV(15, "CFMV"),
	GOL(10, "GOL");
	
	private int id;
	private String nome;
	
	private ClientesEnum(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public static Optional<ClientesEnum> getById(int id) {
        return Arrays.stream(values())
            .filter(cliente -> cliente.getId() == id)
            .findFirst();
    }
	
	public static Optional<ClientesEnum> getByNome(String nome) {
        return Arrays.stream(values())
            .filter(cliente -> cliente.getNome().equals(nome))
            .findFirst();
    }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ClientesEnum getDefaultCliente() {
		return ClientesEnum.ALBA;
	}
}
