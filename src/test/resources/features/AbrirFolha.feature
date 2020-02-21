#language: pt
Funcionalidade: Abrir Folha
Testes de abertura de folha

	Cenário: Abrir geração de folha
		Dada a pagina de "Abrir Geração de Folha"
		Dado existem gerações fechadas
		Quando abro a geracao de folha mais atual
		E acesso a pagina de "Home > Tabelas > Folha de Pagamento > Outros > Geração da Folha"
		E filtro a tabela pela geração
		Entao encontro a geracao pesquisada