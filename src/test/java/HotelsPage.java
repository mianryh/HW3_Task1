import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import static com.codeborne.selenide.Selenide.*;

/*
 * страница с отелями Анталии Booking.com
 */

public class HotelsPage {

    private final SelenideElement searchInput = $x("//input[@name='ss']");
    private final SelenideElement filterFiveStarsCheckBox = $x("//input[@value='class=5']");
    private final ElementsCollection hotelsFiveStars = $$x("//div[@aria-label='5 из 5']");



    @Step("проверить, что в поиске отображается «Анталья»")
    public boolean isLocationDisplayed(String location){
        System.out.println("Текущее значение поля ввода: " + searchInput.getValue());
        return searchInput.getValue().contains(location);

    }

    @Step("Выбираем фильтр 5 звезд")
    public void selectFiveStars(){
        filterFiveStarsCheckBox.scrollTo();
        filterFiveStarsCheckBox.click();

    }

    @Step("Проверяем, что все отели имеют 5 звезд")
    public boolean allHotelsHaveFiveStars() {
        // Прокручиваем страницу, пока не загрузятся все карточки отелей
        int previousCount = 0;
        int currentCount = 0;

        do {
            previousCount = currentCount;
            // Получаем все элементы отелей на странице
            ElementsCollection hotels = $$x("//div[@data-testid='property-card']");
            currentCount = hotels.size();

            // Прокручиваем страницу вниз
            Selenide.executeJavaScript("window.scrollTo(0, document.body.scrollHeight);");
            sleep(3000); // Ждем, чтобы новые элементы успели загрузиться
        } while (currentCount > previousCount); // Продолжаем, пока количество карточек увеличивается
        System.out.println(currentCount);
        // Проверяем, что все отели имеют 5 звезд
        for (SelenideElement hotel : hotelsFiveStars) {
            // Проверяем наличие атрибута aria-label
            String ariaLabel = hotel.getAttribute("aria-label");
            if (ariaLabel == null || !ariaLabel.equals("5 из 5")) {
                return false;
            }

        }

        return true; // Если все отели имеют 5 звезд, возвращаем true
    }
}
