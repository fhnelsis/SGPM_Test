import java.util.concurrent.TimeUnit;
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
	}

	@Test
	public void Login() {
		// Comanda o driver para acessar a página.
		driver.get(url);

		// Procura na página um elemento pelo nome dele.
		WebElement element = driver.findElement(By.name(campoLogin));

		// Envia um input para o campo encontrado.
		element.sendKeys(valueLogin);

		// As duas linhas acima podem virar só uma!
		driver.findElement(By.name(campoPassword)).sendKeys(valuePassword);

		// Também é possível achar o elemento pelo xpath. Isso sim é avanço!
		driver.findElement(By.xpath("//*[@id='submit']")).submit();

		// Check the title of the page
		driver.getPageSource().contains(
				"Bem-vindo ao Sistema Gerenciador de Prontuários Médicos");
	}
}