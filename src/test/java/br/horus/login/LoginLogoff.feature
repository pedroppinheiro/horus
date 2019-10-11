#language: pt
Funcionalidade: Login e logoff
Teste de realização de login e logoff

	Esquema do Cenario: Procedimento de login e logoff
		Dada a pagina de login
		Quando eu entro com minhas credenciais "<login>" e "<senha>"
		Entao sou direcionado para a home page
		E e apresentada a mensagem de sucesso "WELCOME :)" 
	
	Exemplos:
    |login|senha|
    |admin|12345|
  
  Cenario: Quando ocorre falha no login e apresentada mensagem de acesso negado
  	Dada a pagina de login
		Quando eu entro com minhas credenciais "xxxx" e "xxxx"
		Entao e apresentada a mensagem de erro "ACCESS DENIED!"
		
	Cenario: Quando eu realizo login e clico no botao de GO BACK, entao retorno a pagina de login
  	Dada a pagina de login
		Quando eu entro com minhas credenciais "admin" e "12345"
		Entao sou direcionado para a home page
		E e apresentada a mensagem de sucesso "WELCOME :)"
		Quando eu clico no botao de retornar
		Entao sou redirecionado a pagina inicial 
