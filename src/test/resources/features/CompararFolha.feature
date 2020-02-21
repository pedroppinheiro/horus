#language: pt
Funcionalidade: Comparar diferenças de folha
Testes de comparação de diferenças da folha com a ficha

	Cenário: Verificar diferenças entre ficha e folha
		Dada a pagina de "Salvar e Recuperar Tabelas"
		Quando eu preencho o limite de registros para "10000"
		E recupero tabelas projeto
		
		E acesso a pagina de "Abrir Geração de Folha"
		E abro a geracao de folha mais atual
		
		E acesso a pagina de "Cálculo da Folha (TODOS)"
		E seleciono um empregado aleatório "Jose"
		E calculo todos os empregados
		
		E acesso a pagina de "Visualizar Geração de Folha"
		E clico para ver diferenças da geração aberta
		Entao não deve haver diferenças
		
		