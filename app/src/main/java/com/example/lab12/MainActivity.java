package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String eur_rate;
    private String usd_rate;
    private String chf_rate;

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

//    public void buttonPress(View view) {getIPInfo();}

//    private String getIP() {
//        EditText euro = (EditText) findViewById(R.id.euro_value);
//        EditText pln = (EditText) findViewById(R.id.pln_value);
//        EditText usd = (EditText) findViewById(R.id.usd_value);
//        EditText chf = (EditText) findViewById(R.id.chf_value);
//
//        return"ad";
//    }

    private void printInfo(IPInfo info) {
        TextView textView = (TextView) findViewById(R.id.sum);
        String s;
        if(info == null) s = "Faild";
        else {
            //przeliczyc kurs
            s = "asd";
        }
        textView.setText(s);
    }

    private void getIPInfo(){
        ApiInterface apiInterfaceEur = ServiceGenerator.eur(ApiInterface.class);
        Call<IPInfo> call = apiInterfaceEur.getIPInfo();
        call.enqueue(new Callback<IPInfo>() {
            @Override
            public void onResponse(Call<IPInfo> call, Response<IPInfo> response) {
                printInfo(response.body());
            }
            @Override
            public void onFailure(Call<IPInfo> call, Throwable t) {
                printInfo(null);
            }
        });
    }
}