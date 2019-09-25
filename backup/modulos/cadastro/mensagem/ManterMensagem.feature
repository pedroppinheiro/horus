#language: pt
Funcionalidade: Manter Mensagem

Este caso de uso tem como objetivo descrever a solução referente ao cadastro de mensagens no PSIM – Gerenciamento de Informações de segurança Física.
Uma mensagem é a identificação de um fato (presença indevida, dano, falha) detectado por algum componente do sistema de segurança.
Se esse fato não exige ação, mas apenas ciência do mesmo, ele é uma INFORMAÇÃO. Se exige tomada de providências, é um EVENTO.

	Cenário: Incluir Mensagem
		Dada a pagina de Mensagem
		Quando eu clico no botão de incluir
		E preencho o formulario para incluir a nova mensagem de nome "teste pedro selenium"
		E clico em salvar
		Entao o sistema deve exibir a mensagem "Operação Realizada com Sucesso."