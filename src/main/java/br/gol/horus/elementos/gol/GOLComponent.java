package br.gol.horus.elementos.gol;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.gol.horus.constantes.Constantes;
import br.gol.horus.driver.DriverVO;
import br.gol.horus.elementos.base.PageObjectBase;
import br.gol.horus.helpers.ClientesEnum;
import br.gol.horus.helpers.DominioPaginas;

/**
 * Classe que guarda comportamentos e objetos que são específicas para componentes do GOL (modais, elementos específicos da página)
 * @author pedro
 *
 */
public abstract class GOLComponent extends PageObjectBase {

	public GOLComponent(DriverVO driverVO) throws Exception {
		super(driverVO);
	}
}
