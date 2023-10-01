package pageobject_model.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public abstract class BasePage {
    private static final long TIMEOUT = 10;
    private static final long POLLING_TIME = 1;
    protected WebDriver driver;
    protected Wait<WebDriver> wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofSeconds(POLLING_TIME))
                .ignoring(Exception.class);
        PageFactory.initElements(driver, this);
    }
    public String getTitle() {
        return driver.getTitle();
    }
}
