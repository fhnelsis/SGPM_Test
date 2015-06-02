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

public class NovoFuncionario {

	/* Variáveis de Credenciais */
	String url = "http://localhost/sgpm/";
	String valueLogin = "admin";
	String valuePassword = "admin";
	String campoLogin = "login";
	String campoPassword = "senha";

	/* Variáveis de Validação */
	String expectedTextoBoasVindas = "Bem-vindo ao Sistema Gerenciador de Prontuários Médicos";

	String expectedTextoNovoTipoDeAtendimento = "Novo Tipo de Atendimento";
	String expectedTextoRegistroSalvoComSucesso = "Registro Salvo Com Sucesso!";
	String valueNovoTipoDeAtendimento = "Colonoscopia";

	FirefoxDriver driver = new FirefoxDriver();

	@Before
	public void startDriver() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void stopDriver() {
		driver.close();
		driver.quit();
	}

	@Test
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