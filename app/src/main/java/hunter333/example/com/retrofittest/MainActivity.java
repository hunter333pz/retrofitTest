package hunter333.example.com.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import hunter333.example.com.retrofittest.daos.ExchangeRateDao;
import hunter333.example.com.retrofittest.databaseHolders.AppDatabase;
import hunter333.example.com.retrofittest.entities.ExchangeRate;
import hunter333.example.com.retrofittest.utils.DatabaseInitializer;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView inputTV;
    private TextView outputTV;

    public void Calculate(View view) {
        double inputSum = Double.parseDouble(inputTV.getText().toString());
        double rate;
        if (rates.getExchangeRates().containsKey("HUF")) {
            rate = rates.getExchangeRates().get("HUF");
        } else {
            rate = 0;
        }
        Log.d("rate: ", String.valueOf(rate));
        double result = inputSum / rate;
        outputTV.setText(String.valueOf(result));
    }

    private ExchangeRates rates = new ExchangeRates();

    ExchangeRatesClient client = ServiceGenerator.createService(ExchangeRatesClient.class);

    Call<ExchangeRatesResponse> call = client.exchangeRatesForCountry("BGN");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getExchangeRatesCall();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputTV = (TextView) findViewById(R.id.input_text_view);
        outputTV = (TextView) findViewById(R.id.output_text_view);
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    private void getExchangeRatesCall() {
        call.enqueue(new Callback<ExchangeRatesResponse>() {
            @Override
            public void onResponse(Call<ExchangeRatesResponse> call, Response<ExchangeRatesResponse> response) {

                if (response.body().getRates() != null) {
                    rates.setExchangeRates(response.body().getRates());
                    Log.d("Request", "success");
                    for (Map.Entry<String, Double> rate : rates.getExchangeRates().entrySet()) {
                        ExchangeRate exchangeRate = DatabaseInitializer.getRateByCode(AppDatabase.getAppDatabase(getApplicationContext()), rate.getKey());
                        if (exchangeRate != null) {
                            exchangeRate.setExchangeRateCoefficient(rate.getValue());
                            DatabaseInitializer.updateExchangeRate(AppDatabase.getAppDatabase(getApplicationContext()), exchangeRate);
                            Log.d("RATE_UPDATE", exchangeRate.toString());
                        } else {
                            exchangeRate = new ExchangeRate(rate.getKey(), rate.getValue());
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
}
