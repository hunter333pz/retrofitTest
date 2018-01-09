package hunter333.example.com.retrofittest.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import hunter333.example.com.retrofittest.entities.ExchangeRate;

/**
 * Created by Hunter333 on 3.12.2017 г..
 */
@Dao
public interface ExchangeRateDao {
    @Query("SELECT * FROM exchange_rate")
    List<ExchangeRate> getAll();

    @Query("SELECT * FROM exchange_rate where to_currency==:toCurrency")
    List<ExchangeRate> findRateByToCurrency(String toCurrency);

    @Query("SELECT * FROM exchange_rate where from_currency==:fromCurrency")
    List<ExchangeRate> findRateByFromCurrency(String fromCurrency);

    @Query("SELECT * FROM exchange_rate where from_currency==:fromCurrency AND to_currency==:toCurrency")
    ExchangeRate findRateByFromAndToCurrency(String fromCurrency, String toCurrency);

    @Query("SELECT COUNT(*) from exchange_rate")
    int countCountries();

    @Insert
    void insertAll(ExchangeRate... exchangeRates);

    @Delete
    void delete(ExchangeRate exchangeRate);

    @Update
    void updateExchangeRate(ExchangeRate exchangeRate);
}
