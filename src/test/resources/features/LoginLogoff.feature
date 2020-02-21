#language: pt
@loginlogoff
Funcionalidade: Login e logoff
Teste de realização de login e logoff no PSIM
	
	@login
	Esquema do Cenario: Procedimento de login e logoff no PSIM
		Dada a pagina de login do GOL
		Quando eu entro com minhas credenciais "<login>" e "<senha>"
		Entao sou direcionado para a home page
		E o header exibe o nome do cliente correto
	
	Exemplos:
    | login | senha  |
    |gol@golsoftware.com.br|condocloud30|
  
  @logoff
  Cenário: Realizo o logoff
		Dada a pagina de "DashBoard"
		Quando eu realizo o logoff
		Entao sou redirecionado para a página de login