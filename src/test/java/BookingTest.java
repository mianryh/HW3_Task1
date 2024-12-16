
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import static com.codeborne.selenide.Selenide.sleep;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class BookingTest extends BaseTest{

    private final static String BASE_URL = "https://booking.com/";
    private final static String SEARCH_STRING = "Анталья";

    @Test
    @DisplayName("Проверка отображения отелей")
    @Description("Тест проверяет, отображаются ли отели в Анталье с 5 звездами.")
    public void checkHotels(){

        openBookingPage();
        CorrectSearch();
        applyFiveStarFilter();
        verifyAllHotelsHaveFiveStars();

    }

    @Step("Открываем главную страницу Booking.com и выполняем поиск города")
    public void openBookingPage() {
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.search(SEARCH_STRING);
    }

    @Step("проверить, что в поиске отображается «Анталья»")
    public void CorrectSearch(){
        HotelsPage hotelsPage = new HotelsPage();
        Assertions.assertTrue(hotelsPage.isLocationDisplayed(SEARCH_STRING),
                "Местоположение не отображается корректно");
    }

    @Step("Применяем фильтр пятизвездочных отелей")
    public void applyFiveStarFilter(){
        HotelsPage hotelsPage = new HotelsPage();
        hotelsPage.selectFiveStars();
        sleep(5000);
    }

    @Step("Проверяем, что все отели имеют 5 звезд")
    public void verifyAllHotelsHaveFiveStars() {
        HotelsPage hotelsPage = new HotelsPage();
        boolean allHotelsHaveFiveStars = hotelsPage.allHotelsHaveFiveStars();
        Assertions.assertTrue(allHotelsHaveFiveStars,
                "Не все отели имеют 5 звезд");
    }

}
