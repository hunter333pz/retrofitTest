package hunter333.example.com.retrofittest;

/**
 * Created by Hunter333 on 9.1.2018 г..
 */

public enum CountryCodeListEnum {
    AUD("Австралийски долар", "AUD"),
    BRL("Бразилски", "BRL"),
    BGN("Български лев", "BGN"),
    CAD("Канадски долар", "CAD"),
    CHF("Швейцарски франк", "CHF"),
    CNY("Китайска йена", "CNY"),
    CZK("Чешка крона", "CZK"),
    DKK("Датска крона", "DKK"),
    GBP("Британски паунд", "GBP"),
    HKD("Хонгконгски", "HKD"),
    HRK("Хърватска куна", "HRK"),
    HUF("Унгарски форинт", "HUF"),
    IDR("ИРД", "IDR"),
    ILS("ИЛС", "ILS"),
    INR("ИНР", "INR"),
    JPY("Японска йена", "JPY"),
    KRW("КРВ", "KRW"),
    MXN("МѝН", "MXN"),
    MYR("МЪР", "MYR"),
    NOK("Норвежка", "NOK"),
    NZD("Новозеландски долар", "NZD"),
    PHP("Филипински", "PHP"),
    PLN("Полска злота", "PLN"),
    RON("Румънска лея", "RON"),
    RUB("Руска рубла", "RUB"),
    SEK("СЕК", "SEK"),
    SGD("СГД", "SGD"),
    THB("Тйаландски", "THB"),
    TRY("Турска лира", "TRY"),
    USD("Щадски долар", "USD"),
    ZAR("ЗАР", "ZAR"),
    EUR("Евро", "EUR");

    private final String CURRENCY_NAME;
    private final String CURRENCY_SHORT_NAME;

    CountryCodeListEnum(String name, String shortName) {
        this.CURRENCY_NAME = name;
        this.CURRENCY_SHORT_NAME = shortName;
    }

    @Override
    public String toString() {
        return CURRENCY_NAME + "(" + CURRENCY_SHORT_NAME + ")";
    }

    public String getCURRENCY_SHORT_NAME() {
        return CURRENCY_SHORT_NAME;
    }

    public String getCURRENCY_NAME() {
        return CURRENCY_NAME;
    }
}
