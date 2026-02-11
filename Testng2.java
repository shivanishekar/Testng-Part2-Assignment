// 1. Create groups for Functional and Non-functional and run only functional group using testng.xml

//. Creating Groups
  
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class CalculatorPart2Test {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://testpages.herokuapp.com/apps/button-calculator/");
    }

    @Test(groups = "Functional")
    public void testAddition() {

        driver.findElement(By.id("button01")).click();
        driver.findElement(By.id("buttonPlus")).click();
        driver.findElement(By.id("button02")).click();
        driver.findElement(By.id("buttonEquals")).click();

        String result = driver.findElement(By.id("calculator-display")).getText();
        Assert.assertEquals(result, "3");
    }
  // Functional

    @Test(groups = "Functional", dependsOnMethods = "testAddition")
    public void testSubtraction() {

        driver.findElement(By.id("button05")).click();
        driver.findElement(By.id("buttonMinus")).click();
        driver.findElement(By.id("button02")).click();
        driver.findElement(By.id("buttonEquals")).click();

        String result = driver.findElement(By.id("calculator-display")).getText();
        Assert.assertEquals(result, "3");
    }
 // Non- Functional

    @Test(groups = "Non-Functional")
    public void testPageTitle() {

        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Calculator"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

//.file testng.xml

  <!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">

<suite name="Calculator Suite">

    <test name="Functional Tests Only">
        
        <groups>
            <run>
                <include name="Functional"/>
            </run>
        </groups>

        <classes>
            <class name="CalculatorPart2Test"/>
        </classes>

    </test>

</suite>

/* When we run testng.xml:

. testAddition runs
. testSubtraction runs
. testPageTitle will NOT run */


