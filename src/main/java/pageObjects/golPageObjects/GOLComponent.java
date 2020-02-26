package pageObjects.golPageObjects;

import pageObjects.PageObject;
import valueObjects.DriverVO;

/**
 * Classe que guarda comportamentos e objetos que são específicas para componentes do GOL (modais, elementos específicos da página)
 * @author pedro
 *
 */
public abstract class GOLComponent extends PageObject {

	public GOLComponent(DriverVO driverVO) throws Exception {
		super(driverVO);
	}
}
