package com.example.country;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.country.adapter.CountriesAdapter;
import com.example.country.data.Api;
import com.example.country.model.CountryModel;
import com.google.gson.Gson;

public class CountryActivity extends AppCompatActivity {

    private final static String TAG = "COUNTRYACT";

    private CountriesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_countries);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new CountriesAdapter();

        recyclerView.setAdapter(mAdapter);

        getCountriesList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /***
     * get country list from http://www.restcountries.eu
     */
    private void getCountriesList() {

        StringRequest request = new StringRequest(
                Request.Method.GET,
                Api.URL_PATH,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        if(!response.isEmpty()) {
                            Gson gson = new Gson();
                            // array of country
                            CountryModel[] countries = gson.fromJson(response, CountryModel[].class);
                            // add it to adapter
                            for(CountryModel country: countries) {
                                mAdapter.addItem(country);
                            }
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.getLocalizedMessage());
                    }
                }
        );

        Volley.newRequestQueue(getApplicationContext()).add(request);
    }

}
