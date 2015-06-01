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

	/* Vari�veis */
	String url = "http://localhost/sgpm/";
	String campoLogin = "login";
	String campoPassword = "senha";
	String expectedTextoBoasVindas = "Bem-vindo ao Sistema Gerenciador de Prontu�rios M�dicos";

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
		// Comanda o driver para acessar a p�gina.
		driver.get(url);
		
		//Maximiza a tela aberta
		driver.manage().window().maximize();

		// Procura na p�gina um elemento pelo nome dele.
		WebElement element = driver.findElement(By.name(campoLogin));

		// Envia um input para o campo encontrado.
		element.sendKeys(valueLogin);

		// As duas linhas acima podem virar s� uma!
		driver.findElement(By.name(campoPassword)).sendKeys(valuePassword);

		// Tamb�m � poss�vel achar o elemento pelo xpath. Isso sim � avan�o!
		driver.findElement(By.xpath("//*[@id='submit']")).submit();

		// Verifica a exist�ncia de um elemento na p�gina.

		String textBoasVindas = driver.findElement(By.xpath("//*[@id='divBoasVindas']/p")).getText();
		assertEquals(expectedTextoBoasVindas, textBoasVindas);
		
		
	}
}