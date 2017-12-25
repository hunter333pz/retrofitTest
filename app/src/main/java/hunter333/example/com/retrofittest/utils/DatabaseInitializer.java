package hunter333.example.com.retrofittest.utils;

/**
 * Created by Hunter333 on 3.12.2017 г..
 */

import android.support.annotation.NonNull;

import hunter333.example.com.retrofittest.databaseHolders.AppDatabase;
import hunter333.example.com.retrofittest.entities.ExchangeRate;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;


import java.util.List;

public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    public static void populateAsync(@NonNull final AppDatabase db, ExchangeRate rate) {
        PopulateDbAsync task = new PopulateDbAsync(db, rate);
        task.execute();
    }

    public static ExchangeRate getRateByToCurrency(final AppDatabase db, String toCurrency) {
        return db.exchangeRateDao().findRateByToCurrency(toCurrency);
    }

    public static void updateExchangeRate(final AppDatabase db, ExchangeRate exchangeRate) {
        db.exchangeRateDao().updateExchangeRate(exchangeRate);
    }

    public static void populateSync(@NonNull final AppDatabase db) {

    }

    private static ExchangeRate addExchangeRate(final AppDatabase db, ExchangeRate exchangeRate) {
        db.exchangeRateDao().insertAll(exchangeRate);
        return exchangeRate;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;
        private ExchangeRate mRate;

        PopulateDbAsync(AppDatabase db, ExchangeRate rate) {
            mDb = db;
            mRate = rate;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            addExchangeRate(mDb, mRate);

            List<ExchangeRate> exchangeRateList = mDb.exchangeRateDao().getAll();
            Log.d(DatabaseInitializer.TAG, "Rows Count: " + exchangeRateList.size());
            return null;
        }

    }
}
