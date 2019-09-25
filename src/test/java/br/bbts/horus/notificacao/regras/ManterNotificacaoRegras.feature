#language: pt
Funcionalidade: Manter Notificacao Regras
Operações de visualização, criação, edição e remoção de regras de notificação

	Cenario: Manter Notificacao Regras
		Dada a pagina de Notificacao Regras do PSIM
		
		Quando incluo uma nova regra "Regra Selenium" com valores quaisquer
		E pesquiso pelo nome "Regra Selenium"
		Entao a regra "Regra Selenium" deve aparecer na listagem de regras

		Quando eu renomeio a regra "Regra Selenium" para "Regra Selenium Renomeada"
		E pesquiso pelo nome "Regra Selenium Renomeada"
		Entao a regra "Regra Selenium Renomeada" deve aparecer na listagem de regras

		Quando eu excluo a regra "Regra Selenium Renomeada"
		Entao a regra "Regra Selenium Renomeada" nao deve aparecer na listagem de regras