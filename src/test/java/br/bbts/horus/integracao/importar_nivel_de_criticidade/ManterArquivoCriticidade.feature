#language: pt
@arquivo_de_criticidade
Funcionalidade: Manter Arquivo Criticidade

Este caso de uso tem como objetivo descrever a solução para a importação do arquivo de nível de criticidade das dependências do BB.
Este arquivo é responsável por transitar informações do nível de criticidade das dependências,
objetivando dinamizar o monitoramento das dependências ao longo do tempo em função de sua criticidade.
	
	Contexto:
		Dada a pagina de Importar Nível de Criticidade
		
#####################################
	@inclusao_criticidade
	Esquema do Cenário: 6.2	Subfluxos - SUB01. Importar Arquivo Criticidade
		Quando eu clico no botão de incluir
		E faço upload do arquivo "<arquivo>"
		E clico em salvar
		Entao o sistema deve exibir a mensagem "Operação Realizada com Sucesso."
		E a listagem irá incluir o novo registro incluído

	Exemplos:
    | arquivo |
    | layout_valido.csv |
    
###########

    #Esquema do Cenário: 6.3	Fluxos de Exceção
		#Quando eu clico no botão de incluir
		#E faço upload do arquivo "<arquivo>"
		#E clico em salvar
		#Entao o sistema deve exibir a mensagem "<mensagem>"

	#Exemplos:
    #| arquivo | mensagem |
	#|layout_valido.csv | Erro no processamento do arquivo: Nome duplicado|
	#|layout_invalido1.csv | Erro no processamento do arquivo: Layout inválido|
	#|layout_invalido2.csv | Erro no processamento do arquivo: Layout inválido|
	#|arquivo_em_branco.csv | Ocorreu um erro inesperado. Por favor, tente novamente mais tarde ou contate o administrador. Código do Erro: 342.783. |
	#|dependencia_inexistente.csv | Erro no processamento do arquivo: Dependência Não Existe|
	#|dataexpiracao_invalido.csv | Ocorreu um erro inesperado. Por favor, tente novamente mais tarde ou contate o administrador. Código do Erro: 342.812.|
	
    
###########

