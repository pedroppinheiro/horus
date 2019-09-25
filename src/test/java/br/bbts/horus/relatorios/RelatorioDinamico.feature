#language: pt
@relatorio_dinamico
Funcionalidade: Relatorio dinâmico

	Contexto:
		Dada a pagina de Relatorio Dinamico do PSIM
		
	@auditoria_dependencia
	Cenario: Gerar relatorio de auditoria dependencia
		Quando eu clico no relatorio de "Auditoria Dependência"
		E informo a data inicio "01/10/2018"
		E informo a data final "05/10/2018"
		E seleciono a dependencia "PIRACURUCA-PIRACURUCA,PI"
		
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso
	
	@auditoria_dependencia_operacao
	Cenario: Gerar relatorio de auditoria dependencia operacao
		Quando eu clico no relatorio de "Auditoria Dependência X Operação"
		E informo a data inicio "01/10/2018"
		E informo a data final "05/10/2018"
		E seleciono a dependencia "PIRACURUCA-PIRACURUCA,PI"
		
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso
	
	@auditoria_usuario
	Cenario: Gerar relatorio de auditoria usuario
		Quando eu clico no relatorio de "Auditoria Usuário"
		E informo a data inicio "01/10/2018"
		E informo a data final "05/10/2018"
		E seleciono o usuario "PEDRO"
		
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso
		
	@auditoria_usuario_dependencia
	Cenario: Gerar relatorio de auditoria usuario dependencia
		Quando eu clico no relatorio de "Auditoria Usuário X Dependencia"
		E informo a data inicio "01/10/2018"
		E informo a data final "05/10/2018"
		E seleciono a dependencia "PIRACURUCA-PIRACURUCA,PI"
		
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso	

	@cofre_abertura_nao_realizada
	Cenario: Gerar relatorio de cofre abertura nao realizada
	Quando eu clico no relatorio de "Cofre Abertura Não Realizada"
		E informo a data inicio "01/10/2018"
		E informo a data final "05/10/2018"
		
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso
	
	
	@cofre_log_de_operacoes_de_fechadura
	Cenario: Gerar relatorio de Cofre Log de Operacoes de Fechadura
	Quando eu clico no relatorio de "Cofre Log de Operações de Fechadura"
		E informo a data inicio "01/05/2018"
		E informo a data final "15/10/2018"
		
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso
		
	@cofre_log_de_operacoes_de_fechadura
	Cenario: Gerar relatorio de Cofre Operacoes Realizadas - Resumido
	Quando eu clico no relatorio de "Cofre Operações Realizadas - Resumido"
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso
		
	@dependencia_configuracao 
	Cenario: Gerar relatorio de Dependencia Configuracao 
	Quando eu clico no relatorio de "Dependência Configuração"
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso
		
	@Dependência_emergencia
	Cenario: Gerar relatorio de Dependencia Emergencia
	Quando eu clico no relatorio de "Dependência Emergência"
		E informo a data inicio "01/10/2018"
		E informo a data final "31/10/2018"
		E seleciono a dependencia "PIRACURUCA-PIRACURUCA,PI"

		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso
		
	@dependencia_em_estado_de_atencao
	Cenario: Gerar relatorio de Dependencias em Estado de Atencao
	Quando eu clico no relatorio de "Dependências em Estado de Atenção"
		E informo a data base "01/09/2018"
	
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso	
	
	@dependencia_geral
	Cenario: Gerar relatorio de Dependência Geral
	Quando eu clico no relatorio de "Dependência Geral"
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso
		

	@dependencia_em_intersecao_de_grupos
	Cenario: Gerar relatorio de dependencia em intersecao de grupos
		Quando eu clico no relatorio de "Dependência em Interseção de Grupos"
		
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso

	@grupos_de_monitoramento
	Cenario: Gerar relatorio de Grupos de Monitoramento
	Quando eu clico no relatorio de "Grupos de Monitoramento"
		E seleciono o grupo de monitoramento "SELENIUM"
	
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso
		
	@historico_de_acesso_do_usuario
	Cenario: Gerar relatorio de Historico de Acesso do Usuario
	Quando eu clico no relatorio de "Histórico de Acesso do Usuário"
		E informo a data inicio "01/10/2018"
		E informo a data final "31/10/2018"
		
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso
		
	@intermitencias
	Cenario: Gerar relatorio de Intermitencias
	Quando eu clico no relatorio de "Intermitências"
		E informo a data inicio "01/10/2018"
		E informo a data final "31/10/2018"
		E seleciono a dependencia "PIRACURUCA-PIRACURUCA,PI"

		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso
		
	@mensagem
	Cenario: Gerar relatorio de Mensagem
	Quando eu clico no relatorio de "Mensagem"
		E informo a data de inicio da ocorrencia "01/10/2018"
		E informo a data de fim da ocorrencia "07/10/2018"
		E seleciono a dependencia "PIRACURUCA-PIRACURUCA,PI"

		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso

	@mensagem_sintetico
	Cenario: Gerar relatorio de Mensagens Sintetico
	Quando eu clico no relatorio de "Mensagens Sintético"
		E informo a data inicio "01/10/2018"
		E informo a data final "07/10/2018"
		E seleciono a dependencia "PIRACURUCA-PIRACURUCA,PI"

		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso

	@ocorrencias_abertas
	Cenario: Gerar relatorio de Ocorrencias Abertas
	Quando eu clico no relatorio de "Ocorrências Abertas"
		E informo a data inicio "01/10/2018"
		E informo a data final "31/10/2018"

		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso
		
	@ocorrências_encerradas_procedentes_nao_procedentes_analitico
	Cenario: Gerar relatorio de Ocorrencias Encerradas (Procedentes/Nao Procedentes(Analitico))
	Quando eu clico no relatorio de "Ocorrências Encerradas (Procedentes/Não Procedentes(Analítico))"
		E informo a data inicio "01/10/2018"
		E informo a data final "31/10/2018"

		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso

	@papel_x_permissao_sistema
	Cenario: Gerar relatorio de Papel X Permissao Sistema
	Quando eu clico no relatorio de "Papel X Permissão Sistema"
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso

	@permissoes_do_sistema
	Cenario: Gerar relatorio de Permissoes do Sistema
	Quando eu clico no relatorio de "Permissões do Sistema"
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso

	@produtividade_de_operadores_no_tratamento_quantitativo
	Cenario: Gerar relatorio de Produtividade de Operadores no Tratamento (Quantitativo)
	Quando eu clico no relatorio de "Produtividade de Operadores no Tratamento (Quantitativo)"
		E informo a data inicio "01/10/2018"
		E informo a data final "31/10/2018"

		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso

	@totalizador_dispositivo_ativo
	Cenario: Gerar relatorio de Totalizador Dispositivo Ativo
	Quando eu clico no relatorio de "Totalizador Dispositivo Ativo"
		E informo a data base "01/10/2018"
	
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso

	@usuarios_do_sistema
	Cenario: Gerar relatorio de usuarios do sistema
		Quando eu clico no relatorio de "Usuários do Sistema"
		
		E clico no botao de baixar arquivo "pdf"
		Entao o arquivo e baixado com sucesso
		
		Quando clico no botao de baixar arquivo "xlsx"
		Entao o arquivo e baixado com sucesso