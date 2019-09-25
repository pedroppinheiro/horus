#language: pt
Funcionalidade: Exibir imagens VMS
Teste exibição de imagens do VMS

	Cenario: Exibir imagens do VMS
		Dada a pagina de VMS do PSIM
		Quando eu seleciono a dependencia "LABORATORIO BELEM"
		E clico na dependencia "LABORATORIO BELEM" na arvore
		Entao o mosaico deve apresentar todas as cameras da dependencia
