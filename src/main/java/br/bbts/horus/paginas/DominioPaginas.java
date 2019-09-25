package br.bbts.horus.paginas;

import br.bbts.horus.driver.DriverVO;
import br.bbts.horus.paginas.base.PsimPage;

public enum DominioPaginas {
	
	VMS_PAGE("menuVms"),
	NOTIFICACAO_REGRA_PAGE(""),
	RELATORIO_DINAMICO_PAGE("menuRelatorioDinamico"),
	RECONSTITUICAO_CENARIO_PAGE("menuReconstituicaoCenario"),
	IMPORTAR_NIVEL_CRITICIDADE_PAGE("menuImportarNivelCriticidade"),
	MENSAGEM_PAGE("menuMensagem");
	
	private String submenuId;
	
	DominioPaginas(String submenuId) {

		this.submenuId = submenuId;
	}

	public String getSubmenuId() {

		return this.submenuId;
	}
	
	public String toString() {
		return this.getSubmenuId();
	}
	
	public PsimPage getPagina(DriverVO driverVO) {
		
		if(this == DominioPaginas.VMS_PAGE) {
			return new VmsPage(driverVO);
		}
		if(this == DominioPaginas.RELATORIO_DINAMICO_PAGE) {
			return new RelatorioDinamicoPage(driverVO);
		}
		return null;
	}
}
