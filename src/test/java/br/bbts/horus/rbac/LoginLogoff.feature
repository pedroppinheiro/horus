#language: pt
Funcionalidade: Login e logoff
Teste de realização de login e logoff no PSIM

	Esquema do Cenario: Procedimento de login e logoff no PSIM
		Dada a pagina de login do PSIM
		Quando eu entro com minhas credenciais "<login>" e "<senha>"
		Entao sou direcionado para a home page 
	
	Exemplos:
    | login | senha  |
    |selenium|1234|
    #| pedro | 1234 |
    #|neilson.teste | 1234|
    #|torres|1234|
    #|psim|psim|
