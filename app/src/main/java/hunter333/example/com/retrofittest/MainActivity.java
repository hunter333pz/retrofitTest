package hunter333.example.com.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ExchangeRatesClient client = ServiceGenerator.createService(ExchangeRatesClient.class);

    Call<ExchangeRatesResponse> call = client.exchangeRatesForCountry("BGN");

    public void func(View view) {
        call.enqueue(new Callback<ExchangeRatesResponse>() {
            @Override
            public void onResponse(Call<ExchangeRatesResponse> call, Response<ExchangeRatesResponse> response) {

                if (response.body().getExchangeRates() != null) {

                    Map<String,Double> rates = response.body().getExchangeRates();

                    for(Map.Entry<String,Double> rate : rates.entrySet()){
                        Log.d("Key",rate.getKey());
                        Log.d("Value", rate.getValue().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ExchangeRatesResponse> call, Throwable t) {
                Log.e("Request", "error");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
