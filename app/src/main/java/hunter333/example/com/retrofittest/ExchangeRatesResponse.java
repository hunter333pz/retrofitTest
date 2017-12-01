package hunter333.example.com.retrofittest;

import java.util.Map;

/**
 * Created by Hunter333 on 30.11.2017 г..
 */

public class ExchangeRatesResponse {
    private String currency;
    private String date;
    Map<String, Double> rates;

    public ExchangeRatesResponse(String currency, String date, Map<String, Double> exchangeRates) {
        this.currency = currency;
        this.date = date;
        this.rates = exchangeRates;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
