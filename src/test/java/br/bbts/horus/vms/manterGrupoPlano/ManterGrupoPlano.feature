#language: pt
Funcionalidade: Manter Grupo Plano
Operações de visualização, criação, edição e remoção de grupos de plano

	Cenario: Manter Grupo Plano
		Dada a pagina de VMS do PSIM
		E eu seleciono um grupo de monitoramento

		Quando incluo um novo grupo com nome "GrupoSelenium"
		Entao o grupo "GrupoSelenium" deve aparecer na listagem de grupos de plano

		Quando eu renomeio o grupo de plano "GrupoSelenium" para "GrupoSeleniumComNomeAlterado"
		Entao o grupo "GrupoSeleniumComNomeAlterado" deve aparecer na listagem de grupos de plano
		E o grupo "GrupoSelenium" nao deve aparecer na listagem de grupos de plano
		
		Quando eu excluo o grupo de plano "GrupoSeleniumComNomeAlterado"
		Entao o grupo "GrupoSeleniumComNomeAlterado" nao deve aparecer na listagem de grupos de plano