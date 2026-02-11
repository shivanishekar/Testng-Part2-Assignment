// 2. Set dependsonmethods for test and execute

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class DependsOnTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://testpages.herokuapp.com/apps/button-calculator/");
    }

    @Test
    public void testAddition() {

        driver.findElement(By.id("button01")).click();
        driver.findElement(By.id("buttonPlus")).click();
        driver.findElement(By.id("button02")).click();
        driver.findElement(By.id("buttonEquals")).click();

        String result = driver.findElement(By.id("calculator-display")).getText();
        Assert.assertEquals(result, "3");

        System.out.println("Addition Test Passed");
    }

    @Test(dependsOnMethods = "testAddition")
    public void testSubtraction() {

        driver.findElement(By.id("button05")).click();
        driver.findElement(By.id("buttonMinus")).click();
        driver.findElement(By.id("button02")).click();
        driver.findElement(By.id("buttonEquals")).click();

        String result = driver.findElement(By.id("calculator-display")).getText();
        Assert.assertEquals(result, "3");

        System.out.println("Subtraction Test Passed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

/*Output:
. If testAddition PASSES
 testSubtraction will run
. If testAddition FAILS
 testSubtraction will be SKIPPED

In TestNG report you will see:
1 Passed
1 Skipped */
