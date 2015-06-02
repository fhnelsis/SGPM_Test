import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Login {

	/* Variáveis de Credenciais */
	private String url = "http://localhost/sgpm/";
	private String valueLogin = "admin";
	private String valuePassword = "admin";
	private String campoLogin = "login";
	private String campoPassword = "senha";

	/* Variáveis de Validação */
	private String expectedTextoBoasVindas = "Bem-vindo ao Sistema Gerenciador de Prontuários Médicos";

	FirefoxDriver driver = new FirefoxDriver();

	public Login(FirefoxDriver driver) {
		this.driver = driver;
	}

	public void Login() throws Exception {
		driver.get(url);
		driver.manage().window().maximize();

		WebElement element = driver.findElement(By.name(campoLogin));
		element.sendKeys(valueLogin);
		driver.findElement(By.name(campoPassword)).sendKeys(valuePassword);
		driver.findElement(By.xpath("//*[@id='submit']")).submit();

		String textBoasVindas = driver.findElement(
				By.xpath("//*[@id='divBoasVindas']/p")).getText();
		assertEquals(expectedTextoBoasVindas, textBoasVindas);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getValueLogin() {
		return valueLogin;
	}

	public void setValueLogin(String valueLogin) {
		this.valueLogin = valueLogin;
	}

	public String getValuePassword() {
		return valuePassword;
	}

	public void setValuePassword(String valuePassword) {
		this.valuePassword = valuePassword;
	}

	public String getCampoLogin() {
		return campoLogin;
	}

	public void setCampoLogin(String campoLogin) {
		this.campoLogin = campoLogin;
	}

	public String getCampoPassword() {
		return campoPassword;
	}

	public void setCampoPassword(String campoPassword) {
		this.campoPassword = campoPassword;
	}

	public String getExpectedTextoBoasVindas() {
		return expectedTextoBoasVindas;
	}

	public void setExpectedTextoBoasVindas(String expectedTextoBoasVindas) {
		this.expectedTextoBoasVindas = expectedTextoBoasVindas;
	}

	public FirefoxDriver getDriver() {
		return driver;
	}

	public void setDriver(FirefoxDriver driver) {
		this.driver = driver;
	}
}