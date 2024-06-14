import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.*;


@DisplayName("Проверка количества результатов в поисковом запросе")
public class MVideoWithParameters {
    SelenideElement input = $("[id=\"1\"]"),
            button = $("[class=\"search-icon ng-tns-c209126767-1\"]");
    ElementsCollection listProduct = $$("[class=\"product-cards-row ng-star-inserted\"]>div");
    String baseUrl = "https://www.mvideo.ru/";
    int quantity = 10;
    @BeforeEach
    void setup() {
        open (baseUrl);
    }
    @ValueSource(strings = {
            "Ноутбуки apple","Ноутбуки honor","гриль"
    })
    @ParameterizedTest(name = "В поисковой выдаче на mvideo должно отображаться 10 результатов по запросу {0}")
    void simpleWithParameters (String testData) {
        input.setValue(testData);
        button.click();
        listProduct.shouldHave(sizeGreaterThanOrEqual(quantity));
    }
}
