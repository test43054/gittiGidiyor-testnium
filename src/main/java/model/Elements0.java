package model;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Elements0 {
    public static WebDriver driver;


    public Elements0(WebDriver webDriver) {
        driver = webDriver;

    }

    public WebElement txtEmail() {
        return driver.findElement(By.id("L-UserNameField"));
    }

    public WebElement txtPassword() {
        return driver.findElement(By.id("L-PasswordField"));
    }

    public WebElement btnLoginOl() {
        //return driver.findElement(By.xpath("//button[@type='submit']"));
        return driver.findElement(By.id("gg-login-enter"));
    }

    public WebElement aramaKutucugu() {
        return driver.findElement(By.name("k"));
    }

    public WebElement aramaButonu() {

        return driver.findElement(By.xpath("//header[@id='main-header']/div[3]/div/div/div/div[2]/form/div/div[2]/button/span"));
    }

     public WebElement sayfa2() {return driver.findElement(By.xpath("//div[@id='best-match-right']/div[5]/ul/li[2]/a"));}

     //public WebElement randomurun(){return driver.findElement(By.xpath("//div[@id='item-info-block-660433083']/div/div/div/h3/span")); }

     public WebElement sepetekle(){return driver.findElement(By.id("add-to-basket"));}

     public WebElement sepetegit(){return driver.findElement(By.linkText("Sepete Git"));}
}
