import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

/*
 * Главная страница Booking.com
 */
public class MainPage {
    private final SelenideElement textBoxInput = $x("//input[@name='ss']");
    private final SelenideElement searchButton = $x("//button[@type='submit']");

    public MainPage(String url){
        Selenide.open(url);
    }

    @Step("Выполняем поиск города: {searchString}")
    public void search(String searchString){
        sleep(2000);
        textBoxInput.setValue(searchString);
        sleep(2000);
        searchButton.click();
    }

}
