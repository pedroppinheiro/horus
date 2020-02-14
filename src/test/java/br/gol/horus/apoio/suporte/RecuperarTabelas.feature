#language: pt
Funcionalidade: Recuperar Tabelas
Testes de Recuperar Tabelas

	Cenário: Recuperar Tabelas sem erros
		Dada a pagina de "Salvar e Recuperar Tabelas"
		Quando eu preencho o limite de registros para "10000"
		E clico no botão de Recuperar Tabelas Projeto
		E clico em Ok no popup de confirmação
		Entao nenhuma mensagem de erro é exibida