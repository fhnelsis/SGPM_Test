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

public class NovoTipoDeAtendimento {

	/* Variáveis de Validação */
	String expectedTextoNovoTipoDeAtendimento = "Novo Tipo de Atendimento";
	String expectedTextoRegistroSalvoComSucesso = "Registro Salvo Com Sucesso!";
	String valueNovoTipoDeAtendimento = "Colonoscopia";

	FirefoxDriver driver = new FirefoxDriver();
	driver.quit();

	@Before
	public void startDriver() throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Login login = new Login(driver);
		login.Login();
	}

	@After
	public void stopDriver() {
		driver.close();
		driver.quit();
	}

	@Test
	public void NovoTipoDeAtendimento() throws Exception {

		driver.findElementByXPath("//*[@id='cssmenu']/ul/li[2]/ul/li[4]/a")
				.click();

		String textoNovoTipoDeAtendimento = driver
				.findElement(
						By.xpath("//*[@id='tituloPaginaTipoAtendimentoCadastroAlteracao']/center"))
				.getText();
		assertEquals(expectedTextoNovoTipoDeAtendimento,
				textoNovoTipoDeAtendimento);

		driver.findElementByXPath("//*[@id='nome_tipo_atendimento']").sendKeys(
				valueNovoTipoDeAtendimento);
		driver.findElementByXPath("//*[@id='descricao']")
				.sendKeys(
						"Exame de Colonoscopia feito em pacientes clínicos apresentando sintomas.");
		driver.findElementByXPath("//*[@id='enviar_cadastro']").click();

		String textoRegistroSalvoComSucesso = driver.findElementByXPath(
				"//*[@id='formBuscaTipoAtendimento']/div/div[1]").getText();
		assertEquals(expectedTextoRegistroSalvoComSucesso,
				textoRegistroSalvoComSucesso);
	}
}