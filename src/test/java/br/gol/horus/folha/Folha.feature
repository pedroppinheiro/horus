#language: pt
Funcionalidade: Folha
Testes de folha

	Cenário: Abrir geração de folha folha
		Dada a pagina de "Abrir Geração de Folha"
		Dado eu possuo gerações fechadas
		Quando eu seleciono a geração de folha fechada mais atual
		E clico no botão de Abrir Geração de Folha
		E clico em Ok
		E acesso a pagina de "Home > Tabelas > Folha de Pagamento > Outros > Geração da Folha"
		E filtro a tabela pela geração
		Entao encontro a geracao pesquisada