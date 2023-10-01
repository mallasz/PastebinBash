package pageobject_model.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PastebinMainPage extends BasePage {
    private static final String PAGE_URL = "https://www.pastebin.com";

    @FindBy(id = "postform-text")
    private WebElement textArea;
    @FindBy(id = "select2-postform-expiration-container")
    private WebElement pasteExpirationCombobox;
    @FindBy(id = "select2-postform-format-container")
    private WebElement pasteSyntaxHighlighting;
    @FindBy(id = "postform-name")
    private WebElement pasteTitle;
    @FindBy(xpath = "//button[text()='Create New Paste']")
    private WebElement submitButton;
    @FindBy(xpath = "//button[span[text()='AGREE']]")
    private WebElement agreeButton;
    @FindBy(id = "hideSlideBanner")
    private WebElement closeBanner;

    public PastebinMainPage(WebDriver driver) {
        super(driver);
    }

    public PastebinMainPage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public PastebinMainPage fillInTextArea(String text) {
        wait.until(ExpectedConditions.visibilityOf(textArea));
        textArea.clear();
        textArea.sendKeys(text);
        return this;
    }

    public PastebinMainPage selectExpiration(String value, String expirationTimeForJS) {
        try {
            wait.until(ExpectedConditions.visibilityOf(pasteExpirationCombobox));
            pasteExpirationCombobox.click();
            WebElement listElement = driver.findElement(By.xpath(String.format("//li[text()='%s']", value)));
            wait.until(ExpectedConditions.visibilityOf(listElement));
            listElement.click();
        } catch (Exception ignore) {
            //banners can hide the dropdown menu
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(String.format("document.getElementById('postform-expiration').value='%s';", expirationTimeForJS));

        }
        return this;
    }

    public PastebinMainPage selectSyntaxHighlighting(String syntaxHighlightingForJava, String syntaxHighlightingForJS) {
        try {
            wait.until(ExpectedConditions.visibilityOf(pasteSyntaxHighlighting));
            pasteSyntaxHighlighting.click();
            WebElement listElement = driver.findElement(By.xpath(String.format("//li[text()='%s']", syntaxHighlightingForJava)));
            wait.until(ExpectedConditions.visibilityOf(listElement));
            listElement.click();
        } catch (Exception ignore) {
            //banners can hide the dropdown menu
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(String.format("document.getElementById('postform-format').value='%s';", syntaxHighlightingForJS));

        }
        return this;
    }

    public PastebinMainPage fillInTitle(String textOfTitle) {
        wait.until(ExpectedConditions.visibilityOf(pasteTitle));
        pasteTitle.clear();
        pasteTitle.sendKeys(textOfTitle);
        return this;
    }

    public PastebinMainPage createNewPaste() {
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        submitButton.click();
        return this;
    }

    public PastebinMainPage agreePrivacyTerms() {
        try {
            wait.until(ExpectedConditions.visibilityOf(agreeButton));
            agreeButton.click();
        } catch (Exception ignore) {
        }
        return this;
    }

    public PastebinMainPage closeBanner() {
        try {
            wait.until(ExpectedConditions.visibilityOf(closeBanner));
            closeBanner.click();

        } catch (Exception ignore) {
        }
        return this;
    }
}
