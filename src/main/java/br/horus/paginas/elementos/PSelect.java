package br.horus.paginas.elementos;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Classe que representa o select do primefaces para lidar melhor com esses elementos
 * @author pedro.pinheiro
 *
 */
public class PSelect {

	private final WebElement element;
	private final WebElement trigger;
	private static final Logger LOGGER = Logger.getLogger(PSelect.class.getName());
	
	public PSelect(WebElement element) {
		this.element = element;
		this.trigger = this.element.findElement(By.className("ui-selectonemenu-trigger"));
	}

	private List<WebElement> getOptions() {
		return element.findElement(By.className("ui-selectonemenu-panel")).findElements(By.tagName("li"));
	}

	public void selectByValue(String value) {
		trigger.click();
		boolean found = false;
		for (WebElement option : getOptions()) {
			if (option.getAttribute("data-label").equalsIgnoreCase(value)) {
				option.click();
				found = true;
				break;
			}
		}
		if (found) {
			LOGGER.log(Level.FINE, "Opção de select \"{0}\" não encontrada.", value);
		}
		trigger.click();
	}
	
	public void selectFirst() {
		trigger.click();
		boolean found = false;
		WebElement defaultOption = null;
		for (WebElement option : getOptions()) {
			if (option.getAttribute("data-label").equalsIgnoreCase("...")) {
				defaultOption = option;
			} else {
				option.click();
				found = true;
				break;
			}
		}
		if (!found) {
			defaultOption.click();
		}
		trigger.click();
	}

	public String getSelectedOption() {
		return element.findElement(By.tagName("label")).getText();
	}
}
