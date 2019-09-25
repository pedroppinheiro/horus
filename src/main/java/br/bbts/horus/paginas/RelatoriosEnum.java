package br.bbts.horus.paginas;

import java.util.Arrays;
import java.util.List;

public enum RelatoriosEnum {
	
	AUDITORIA_DEPENDENCIA("Auditoria Dependência"),
	AUDITORIA_DEPENDENCIA_OPERACAO("Auditoria Dependência X Operação"),
	AUDITORIA_USUARIO("Auditoria Usuário"),
	AUDITORIA_USUARIO_DEPENDENCIA("Auditoria Usuário X Dependencia"),
	COFRE_ABERTURA_NAO_REALIZADA("Cofre Abertura Não Realizada"),
	COFRE_LOG_DE_OPERACOES_DE_FECHADURA("Cofre Log de Operações de Fechadura"),
	COFRE_OPERACOES_REALIZADAS_RESUMIDO("Cofre Operações Realizadas - Resumido"),
	DEPENDENCIA_CONFIGURACAO("Dependência Configuração"),
	DEPENDENCIA_EMERGENCIA("Dependência Emergência"),
	DEPENDENCIA_EM_ESTADO_DE_ATENCAO("Dependências em Estado de Atenção"),
	
	DEPENDENCIA_GERAL("Dependência Geral"),
	DEPENDENCIA_EM_INTERSECAO_DE_GRUPOS("Dependência em Interseção de Grupos"),
	GRUPOS_DE_MONITORAMENTO("Grupos de Monitoramento"),
	HISTORICO_DE_ACESSO_DO_USUARIO("Histórico de Acesso do Usuário"),
	INTERMITENCIAS("Intermitências"),
	MENSAGEM("Mensagem"),
	MENSAGENS_SINTETICOS("Mensagens Sintético"),
	OCORRENCIAS_ABERTASO("Ocorrências Abertas"),
	OCORRENCIAS_ENCERRADAS_PROCEDENTES_NAO_PROCEDENTES_ANALITICO("Ocorrências Encerradas (Procedentes/Não Procedentes(Analítico))"),
	PAPEL_PERMISSAO_SISTEMA("Papel X Permissão Sistema"),
	
	PERMISSOES_DO_SISTEMA("Permissões do Sistema"),
	PRODUTIVIDADE_DE_OPERADORES_NO_TRATAMENTO_QUANTITATIVO("Produtividade de Operadores no Tratamento (Quantitativo)"),
	TOTALIZADOR_DISPOSITIVO_ATIVO("Totalizador Dispositivo Ativo"),
	USUARIOS_DO_SISTEMA("Usuários do Sistema");
	
	private String elementId;
	private String nome;
	private String nomeArquivoEsperado;
	
	RelatoriosEnum(String nome) {
		this.nome = nome;
		this.elementId = "formulario:listagem:" + this.ordinal() + ":btnSelecionar";
		this.nomeArquivoEsperado = this.nome.replaceAll(" ", "_").replaceAll("/", "-");
	}
	
	public static RelatoriosEnum getRelatorioPorNome(String nome) {
		List<RelatoriosEnum> relatoriosEnumList = Arrays.asList(RelatoriosEnum.values());
		
		RelatoriosEnum relatorioEncontrado = null;
		for (RelatoriosEnum relatorioEnum : relatoriosEnumList) {
			if(relatorioEnum.getNome().equalsIgnoreCase(nome)) {
				relatorioEncontrado = relatorioEnum;
				break;
			}
		}
		
		return relatorioEncontrado;
	}
	
	public String getElementId() {
		return this.elementId;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getNomeArquivoEsperado() {
		return this.nomeArquivoEsperado;
	}
}