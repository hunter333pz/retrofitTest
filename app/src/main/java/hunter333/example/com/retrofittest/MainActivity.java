package hunter333.example.com.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;
import java.util.List;
import java.util.Map;

import hunter333.example.com.retrofittest.databaseHolders.AppDatabase;
import hunter333.example.com.retrofittest.entities.ExchangeRate;
import hunter333.example.com.retrofittest.helper.DateFormatter;
import hunter333.example.com.retrofittest.utils.DatabaseInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText inputTextView;
    private EditText outputTextView;
    private Spinner inputCurrencySpinner;
    private Spinner outputCurrencySpinner;

    private ExchangeRates rates = new ExchangeRates();

    private ExchangeRatesClient client = ServiceGenerator.createService(ExchangeRatesClient.class);

    public void Calculate(View view) {
        getExchangeRates();
        double inputSum;
        try {
            inputSum = Double.parseDouble(inputTextView.getText().toString());
        } catch (Exception e) {
            Log.e("Input is empty", inputTextView.getText().toString());
            inputSum = 0;
        }
        double rate;
        String result;
        if (rates.getExchangeRates() != null && rates.getExchangeRates().containsKey("HUF")) {
            rate = rates.getExchangeRates().get("HUF");
            result = String.valueOf(inputSum / rate);
        } else {
            result = "No exchange rate data!";
        }

        outputTextView.setText(result);
    }

    public void clearInput(View view) {
        inputTextView.getText().clear();
        outputTextView.getText().clear();
    }

    public void closeApp(View view) {
        finish();
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputTextView = (EditText) findViewById(R.id.input_text_view);
        outputTextView = (EditText) findViewById(R.id.output_text_view);

        inputCurrencySpinner = (Spinner) findViewById(R.id.input_currency_spinner);
        outputCurrencySpinner = (Spinner) findViewById(R.id.output_currency_spinner);
        loadCurrencyNames(inputCurrencySpinner);
        loadCurrencyNames(outputCurrencySpinner);
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    private void getExchangeRates() {
        String fromCurrency = ((CountryCodeListEnum) inputCurrencySpinner.getSelectedItem()).getCURRENCY_SHORT_NAME();
        String toCurrency = ((CountryCodeListEnum) outputCurrencySpinner.getSelectedItem()).getCURRENCY_SHORT_NAME();
        List<ExchangeRate> exchangeRateList = DatabaseInitializer.getRateByFromCurrency(AppDatabase.getAppDatabase(getApplicationContext()), fromCurrency);

        getExchangeRatesCall(fromCurrency);
    }

    private void getExchangeRatesCall(final String inputCurrency) {
        Call<ExchangeRatesResponse> call = client.exchangeRatesForCountry(inputCurrency);
        call.enqueue(new Callback<ExchangeRatesResponse>() {
            @Override
            public void onResponse(Call<ExchangeRatesResponse> call, Response<ExchangeRatesResponse> response) {

                if (response.body().getRates() != null) {
                    rates.setExchangeRates(response.body().getRates());
                    Log.d("Request", "success");
                    for (Map.Entry<String, Double> rate : rates.getExchangeRates().entrySet()) {
                        ExchangeRate exchangeRate = DatabaseInitializer.getRateByFromAndToCurrency(AppDatabase.getAppDatabase(getApplicationContext()), inputCurrency, rate.getKey());
                        if (exchangeRate != null) {
                            exchangeRate.setExchangeRateCoefficient(rate.getValue());
                            DatabaseInitializer.updateExchangeRate(AppDatabase.getAppDatabase(getApplicationContext()), exchangeRate);
                            Log.d("RATE_UPDATE", exchangeRate.toString());
                        } else {
                            exchangeRate = new ExchangeRate(inputCurrency, rate.getKey(), new DateFormatter(new Date()).getDateString(), rate.getValue());
                            DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(getApplicationContext()), exchangeRate);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ExchangeRatesResponse> call, Throwable t) {
                Log.e("Request", "error");
            }
        });
    }

    private void loadCurrencyNames(Spinner spinner) {
        spinner.setAdapter(new ArrayAdapter<CountryCodeListEnum>(this, android.R.layout.simple_list_item_1, CountryCodeListEnum.values()));
    }
}
