package tests;

import static org.junit.Assert.assertEquals;
import model.Elements0;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.Log4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;

import java.util.List;
import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;

public class gittiGidiyor {
    public static WebDriver driver;
    private static String baseUrl;
    public static String afterWritebilgisayar;
    public static String afterLoginpageurl;
    public static String sayfa2url;
    public static String sepetfiyatı;
    public static String adet;

    public static String email = "mustafa.oral@outlook.com.tr";
    public static String userPassword = "12345gqwerty";


    public static Elements0 elementPage; //Modelimizin bulunduğu paket dosyası
    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        driver = new ChromeDriver();
        baseUrl = "https://www.gittigidiyor.com/";
        afterWritebilgisayar = "https://www.gittigidiyor.com/arama/?k=bilgisayar";
        afterLoginpageurl = "https://www.gittigidiyor.com/";
        sayfa2url = "https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2";

        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        driver.manage().timeouts().pageLoadTimeout(200, SECONDS);
        driver.manage().window().maximize();

        elementPage = new Elements0(driver);
    }

    @Test
    public void testgittiGidiyor() throws Exception {

        Log4j.startLog("Test  is Starting");
        driver.get(baseUrl);

        String urlEquals = "https://www.gittigidiyor.com/";
        assertEquals(urlEquals, baseUrl);

        driver.findElement(By.cssSelector("div[name=\"profile\"] > svg")).click();
        driver.findElement(By.xpath("//header[@id='main-header']/div[3]/div/div/div/div[3]/div/div/div[2]/div/div/div/a/span")).click();

        Log4j.info("Opening Page : " + baseUrl);

        elementPage.txtEmail().clear();
        elementPage.txtEmail().sendKeys(email);
        Log4j.info("Email :" + email);

        //Login
        elementPage.txtPassword().clear();
        elementPage.txtPassword().sendKeys(userPassword);
        elementPage.btnLoginOl().click();

        String currenturl0 = driver.getCurrentUrl();
        assertEquals(afterLoginpageurl, currenturl0);
        Log4j.info("Login Başarılı");

        //Arama kutucuğuna bilgisayar yazılır

        elementPage.aramaKutucugu().clear();
        elementPage.aramaKutucugu().sendKeys("bilgisayar");
        elementPage.aramaButonu().click();
        String currenturl1 = driver.getCurrentUrl();
        assertEquals(afterWritebilgisayar, currenturl1);
        Log4j.info("arama başarılı");


        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("window.scrollBy(0,2000)");
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        //sayfa2 ye basılır
        //sayfanın en aşşağısına inilir basılacak yeri görmek için
        //driver.manage().timeouts().implicitlyWait(35, SECONDS);
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        elementPage.sayfa2().click();
        String currenturl2 = driver.getCurrentUrl();
        assertEquals(sayfa2url,currenturl2);
        Log4j.info("sayfa 2ye geçiş başarılı");

        driver.manage().timeouts().implicitlyWait(10, SECONDS);

        ((JavascriptExecutor) driver).executeScript("scroll(0,250);");

        // random  ürün seçiliyor
        List<WebElement> links = driver.findElements(By.cssSelector("a.product-link"));
        links.get(new Random().nextInt(links.size())).click();
        //**********************
        Log4j.info("Random ürün seçildi");

        /*WebElement nameText = driver.findElement(By.id("name"));
        String name = nameText.getText();*/


        WebElement pricetextx = driver.findElement(By.xpath("//div[@id='sp-price-container']/div[4]"));
        String pricex = pricetextx.getText();
        Log4j.info("ürünün sayfadaki fiyatı" + pricex);

        /*WebElement pricetext1 = driver.findElement(By.id("sp-price-lowPrice"));
        String price1 = pricetext1.getText();
        Log4j.info("ürünün sayfadaki fiyatı(sp-price-lowPrice)" + price1);*/

        //***************************************************************************


        ((JavascriptExecutor) driver).executeScript("scroll(0,250);");
        elementPage.sepetekle().click();
        Log4j.info("Sepete Eklendi");
        //elementPage.sepetegit().click();
        //driver.findElement(By.cssSelector("a.header-cart-hidden-link")).click();
        driver.findElement(By.xpath("//div[@id='header_wrapper']/div[3]/a")).click();
        driver.findElement(By.xpath("//header[@id='main-header']/div[3]/div/div/div/div[3]/div/div[2]/a/div/div[2]")).click();
        Log4j.info("Sepete Gidildi");

        WebElement sepetfiyat = driver.findElement(By.xpath("//strong[2]"));
        sepetfiyatı = sepetfiyat.getText();
        Log4j.info("ürünün sepetteki fiyatı" + sepetfiyatı);


        assertEquals(pricex,sepetfiyatı);
        //***************************************************************************

        /*driver.findElement(By.cssSelector("select.amount")).click();
        new Select(driver.findElement(By.xpath("//select[@value='2']"))).selectByVisibleText("2");*/

        new Select(driver.findElement(By.cssSelector("select.amount"))).selectByVisibleText("2");

        Select comboBox = new Select(driver.findElement(By.cssSelector("select.amount")));
        String selectedComboValue = comboBox.getFirstSelectedOption().getText();

        adet = "2";

        assertEquals(adet,selectedComboValue);
        Log4j.info("Adet doğru(2)");



        driver.findElement(By.cssSelector("i.gg-icon.gg-icon-bin-medium")).click();
        Log4j.info("sepetten silindi");


    }

    @After
    public void endDown(){
        Log4j.endLog("Test is Ending");
        //driver.quit();
    }
}
