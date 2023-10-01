package pageobject_model.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class PastebinResultPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class, 'expire')]")
    private WebElement expire;
    @FindBy(xpath="//a[@class='btn -small h_800' and text()='Bash']")
    private WebElement syntaxHighlighting;
    @FindAll({@FindBy(xpath = "//div[@class='de1']")})
    private List<WebElement> pastedTextLines;


    public PastebinResultPage(WebDriver driver) {
        super(driver);
    }

    public String getExpireText() {
        wait.until(ExpectedConditions.visibilityOf(expire));
        return expire.getText().trim();
    }



    public String getSyntaxHighlighting() {
        wait.until(ExpectedConditions.visibilityOf(syntaxHighlighting));
        return syntaxHighlighting.getText();
    }

    public String getPastedTextLines() {
        wait.until(ExpectedConditions.visibilityOfAllElements(pastedTextLines));
        List<WebElement> divs = driver.findElements(By.xpath("//div[@class='de1']"));
        List<String> result = new ArrayList<>();
        for (WebElement e : divs) {
            result.add(e.getText());
        }
        return String.join("\r\n", result);
    }
}
