package hunter333.example.com.retrofittest.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Hunter333 on 3.12.2017 г..
 */
@Entity(tableName = "exchange_rate")
public class ExchangeRate {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "country_code")
    private String countryCode;

    @ColumnInfo(name = "exchange_rate_coefficient")
    private Double exchangeRateCoefficient;

    public ExchangeRate(String countryCode, Double exchangeRateCoefficient) {
        this.countryCode = countryCode;
        this.exchangeRateCoefficient = exchangeRateCoefficient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Double getExchangeRateCoefficient() {
        return exchangeRateCoefficient;
    }

    public void setExchangeRateCoefficient(Double exchangeRateCoefficient) {
        this.exchangeRateCoefficient = exchangeRateCoefficient;
    }
}
