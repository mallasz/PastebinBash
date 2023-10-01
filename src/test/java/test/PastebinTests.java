package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import pageobject_model.pagefactory.PastebinMainPage;
import pageobject_model.pagefactory.PastebinResultPage;
import utils.FileLoader;


public class PastebinTests extends BaseTest {


    @ParameterizedTest
    @MethodSource("provideValues")
    public void syntaxHighlightExpireTextAreaTest(String text, String expiration, String resultExpiration,
                                                  String expirationTimeForJS, String syntaxHighlightingForJava,
                                                  String syntaxHighlightingForJS, String textOfTitle) {
        PastebinMainPage pastebinMainPage = new PastebinMainPage(driver);
        PastebinResultPage pastebinResultPage = new PastebinResultPage(driver);
        pastebinMainPage
                .openPage()
                .agreePrivacyTerms()
                .closeBanner()
                .fillInTextArea(text)
                .selectExpiration(expiration, expirationTimeForJS)
                .selectSyntaxHighlighting(syntaxHighlightingForJava, syntaxHighlightingForJS)
                .fillInTitle(textOfTitle)
                .createNewPaste();
        Assertions.assertEquals(resultExpiration, pastebinResultPage.getExpireText());
        Assertions.assertEquals(syntaxHighlightingForJava, pastebinResultPage.getSyntaxHighlighting());
        Assertions.assertEquals(text, pastebinResultPage.getPastedTextLines());
        Assertions.assertTrue(pastebinResultPage.getTitle().contains(textOfTitle));
    }

    static Stream<Arguments> provideValues() {
        return Stream.of(
                Arguments.of(FileLoader.loadResourceAsString("values.txt"),
                        "10 Minutes", "10 MIN", "10M", "Bash", "8", "how to gain dominance among developers")
        );
    }
}
