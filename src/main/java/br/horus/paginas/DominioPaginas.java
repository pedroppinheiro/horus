package br.horus.paginas;

import br.horus.driver.DriverVO;
import br.horus.paginas.base.BasePage;

public enum DominioPaginas {
	
	LOGIN_PAGE("login"),
	HOME_PAGE("home");
	
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
	
	public BasePage getPagina(DriverVO driverVO) {
		
		if(this == DominioPaginas.LOGIN_PAGE) {
			return new LoginPage(driverVO);
		}
		if(this == DominioPaginas.HOME_PAGE) {
			return new HomePage(driverVO);
		}
		return null;
	}
}
