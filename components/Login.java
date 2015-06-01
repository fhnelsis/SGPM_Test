import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Login {

	// public static WebDriverWait wait;
	// public static WebDriver driver;

	/* Variáveis */
	String url = "http://localhost/sgpm/";
	String campoLogin = "login";
	String campoPassword = "senha";
	String expectedTextoBoasVindas = "Bem-vindo ao Sistema Gerenciador de Prontuários Médicos";

	String valueLogin = "admin";
	String valuePassword = "admin";

	FirefoxDriver driver = new FirefoxDriver();

	@BeforeTest
	public void startDriver() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@AfterTest
	public void stopDriver() {
		driver.close();
		driver.quit();
	}

	@Test
	public void Login() throws Exception {
		// Comanda o driver para acessar a página.
		driver.get(url);
		
		//Maximiza a tela aberta
		driver.manage().window().maximize();

		// Procura na página um elemento pelo nome dele.
		WebElement element = driver.findElement(By.name(campoLogin));

		// Envia um input para o campo encontrado.
		element.sendKeys(valueLogin);

		// As duas linhas acima podem virar só uma!
		driver.findElement(By.name(campoPassword)).sendKeys(valuePassword);

		// Também é possível achar o elemento pelo xpath. Isso sim é avanço!
		driver.findElement(By.xpath("//*[@id='submit']")).submit();

		// Verifica a existência de um elemento na página.

		String textBoasVindas = driver.findElement(By.xpath("//*[@id='divBoasVindas']/p")).getText();
		assertEquals(expectedTextoBoasVindas, textBoasVindas);
		
		
	}
}