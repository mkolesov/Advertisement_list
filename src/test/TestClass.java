import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestClass {
    @Test
    public void t(){

    }

    public static void Auth(ChromeDriver driver){
        driver.get("http://127.0.0.1:8080");
        WebElement element = driver.findElementByLinkText("Log in");
        element.click();
        WebElement element1 = driver.findElementByName("j_username");
        element1.click();
        element1.sendKeys("admin");
        WebElement element2 = driver.findElementByName("j_password");
        element2.click();
        element2.sendKeys("123");
        WebElement element3 = driver.findElementByName("submit");
        element3.click();
    }


    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "C://Users/Администратор/Downloads/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        Auth(driver);
        for (int i=0; i<1; i++ ) {
            Add(driver);
        }
//        Ser(driver);
        driver.quit();
    }

    public static void Add(ChromeDriver driver){
        WebElement element = driver.findElementByXPath("//form[@id='add_page']/button");
        element.click();
        WebElement element1 = driver.findElementByName("name");
        element1.sendKeys("LOLA");
        WebElement element2 = driver.findElementByName("shortDesc");
        element2.sendKeys("TEILOR");
        WebElement element3 = driver.findElementByName("longDesc");
        element3.sendKeys("NICE");
        WebElement element4 = driver.findElementByName("phone");
        element4.sendKeys("visimnulwis9tweest");
        WebElement element5 = driver.findElementByName("price");
        element5.sendKeys("40");
        WebElement element6 = driver.findElementByXPath("//form[@id='new_adv']/table/tbody/tr[3]/td/button");
        element6.click();
    }
    public static void Ser(ChromeDriver driver){
        WebElement element;
        String s;
        element = driver.findElementByXPath("//form[@id='check']/div/table/tbody");
        s = element.getAttribute("childElementCount");
        int c = Integer.parseInt(s);
        System.out.println(s);
        for(int i = 1; i <= c; i++){
            element = driver.findElementByXPath("//form[@id='check']/div/table/tbody/tr["+i+"]/td[2]");
            s = element.getText();
            if(s.equalsIgnoreCase("LOLA")){
                element = driver.findElementByXPath("//form[@id='check']/div/table/tbody/tr["+i+"]/td[7]/input");
                element.click();

            }
        }
        element = driver.findElementByName("action");
        element.click();
    }


}