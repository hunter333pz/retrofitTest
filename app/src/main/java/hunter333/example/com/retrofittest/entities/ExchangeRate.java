package hunter333.example.com.retrofittest.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Date;


/**
 * Created by Hunter333 on 3.12.2017 г..
 */
@Entity(tableName = "exchange_rate")
public class ExchangeRate {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "from_currency")
    private String fromCurrency;

    @ColumnInfo(name = "to_currency")
    private String toCurrency;

    @ColumnInfo(name = "last_update")
    private String lastUpdate;

    @ColumnInfo(name = "exchange_rate_coefficient")
    private Double exchangeRateCoefficient;

    public ExchangeRate(String fromCurrency, String toCurrency, String lastUpdate, Double exchangeRateCoefficient) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.lastUpdate = lastUpdate;
        this.exchangeRateCoefficient = exchangeRateCoefficient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Double getExchangeRateCoefficient() {
        return exchangeRateCoefficient;
    }

    public void setExchangeRateCoefficient(Double exchangeRateCoefficient) {
        this.exchangeRateCoefficient = exchangeRateCoefficient;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id=" + id +
                ", fromCurrency='" + fromCurrency + '\'' +
                ", toCurrency='" + toCurrency + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", exchangeRateCoefficient=" + exchangeRateCoefficient +
                '}';
    }
}
