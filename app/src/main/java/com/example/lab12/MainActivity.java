package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Double eur_rate;
    private Double usd_rate;
    private Double chf_rate;

    public Double getChf_rate() {
        return chf_rate;
    }

    public Double getEur_rate() {
        return eur_rate;
    }

    public Double getUsd_rate() {
        return usd_rate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencies, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        getIPInfo();
}

    public void buttonPress(View view) {
        Spinner spinner  = (Spinner) findViewById(R.id.spinner);
        TextView textView = (TextView) findViewById(R.id.sum);
        EditText eur = (EditText) findViewById(R.id.euro_value);
        EditText pln = (EditText) findViewById(R.id.pln_value);
        EditText usd = (EditText) findViewById(R.id.usd_value);
        EditText chf = (EditText) findViewById(R.id.chf_value);
        Double eurInPln = (Double) 0.0;
        Double usdInPln = (Double) 0.0;
        Double chfInPln = (Double) 0.0;
        Double plnInPln = (Double) 0.0;

        String eur_val = eur.getText().toString();
        if(eur_val.matches("") || eur_val == "0")
            eurInPln = (Double) 0.0;
        else
            eurInPln = Double.parseDouble(eur_val)*eur_rate;

        String usd_val = usd.getText().toString();
        if(usd_val.matches("") || usd_val == "0")
            usdInPln = (Double) 0.0;
        else
            usdInPln = Double.parseDouble(usd_val)*usd_rate;

        String chf_val = chf.getText().toString();
        if(chf_val.matches("") || chf_val == "0")
            chfInPln = (Double) 0.0;
        else
            chfInPln = Double.parseDouble(chf_val)*chf_rate;

        String pln_val = pln.getText().toString();
        if(pln_val.matches("") || pln_val == "0")
            plnInPln = (Double) 0.0;
        else
            plnInPln = Double.parseDouble(pln_val);


        String actual_currency = spinner.getSelectedItem().toString();

        Double pocket = usdInPln + chfInPln + eurInPln + plnInPln;

        if(actual_currency.equals ((String) "USD")){
            pocket = pocket/usd_rate;
        }
        if(actual_currency.equals((String) "EURO")){
            pocket = pocket/eur_rate;
        }
        if(actual_currency.equals((String) "CHF")){
            pocket = pocket/chf_rate;
        }

        String strPocket = String.format("%.2f", pocket);
        textView.setText(strPocket);
    }
    private void setEur_rate(IPInfo info){
        this.eur_rate = info.getRates().get(0).getMid();
    }
    private void setUsd_rate(IPInfo info){
        this.usd_rate = info.getRates().get(0).getMid();
    }
    private void setChf_rate(IPInfo info){
        this.chf_rate = info.getRates().get(0).getMid();
    }

    private void getIPInfo(){
        ApiInterface apiInterfaceEur = ServiceGenerator.eur(ApiInterface.class);
        ApiInterface apiInterfaceUsd = ServiceGenerator.usd(ApiInterface.class);
        ApiInterface apiInterfaceChf = ServiceGenerator.chf(ApiInterface.class);
        Call<IPInfo> callEur = apiInterfaceEur.getIPInfo();
        Call<IPInfo> callUsd = apiInterfaceUsd.getIPInfo();
        Call<IPInfo> callChf = apiInterfaceChf.getIPInfo();
        callEur.enqueue(new Callback<IPInfo>() {
            @Override
            public void onResponse(Call<IPInfo> callEur, Response<IPInfo> response) {
                setEur_rate(response.body());
            }
            @Override
            public void onFailure(Call<IPInfo> callEur, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something goes wrong", Toast.LENGTH_SHORT).show();
            }
        });

        callChf.enqueue(new Callback<IPInfo>() {
            @Override
            public void onResponse(Call<IPInfo> callChf, Response<IPInfo> response) {
                setChf_rate(response.body());
            }
            @Override
            public void onFailure(Call<IPInfo> callChf, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something goes wrong", Toast.LENGTH_SHORT).show();
            }
        });

        callUsd.enqueue(new Callback<IPInfo>() {
            @Override
            public void onResponse(Call<IPInfo> callUsd, Response<IPInfo> response) {
                setUsd_rate(response.body());
            }
            @Override
            public void onFailure(Call<IPInfo> callUsd, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something goes wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}