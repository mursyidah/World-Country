package com.example.country.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.example.country.R;
import com.example.country.model.CountryModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder> {

    private ArrayList<CountryModel> mData;
    private Context mContext;
    private String countryAlphaCode;

    public CountriesAdapter() {
        mData = new ArrayList();

    }

    @Override
    public CountriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_country, parent, false);
        return new CountriesViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(CountriesViewHolder holder, int position) {
        CountryModel model = mData.get(position);
        holder.countryName.setText(model.getCountryName());
        holder.tvLanguage.setText("( " + model.getLanguages().get(0).getName() + " )");

        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy);

        try {

                final URL url = new URL(model.getFlag());
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                SVG svg = SVG.getFromInputStream(inputStream);
                Drawable drawable = new PictureDrawable(svg.renderToPicture());

                if (drawable != null) {
                        holder.imgFlag.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                        holder.imgFlag.setImageDrawable(drawable);
                    }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SVGParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addItem(CountryModel country) {

        mData.add(country);
        notifyDataSetChanged();
    }

    class CountriesViewHolder extends RecyclerView.ViewHolder {

        TextView countryName;
        TextView tvLanguage;
        ImageView imgFlag;

        public CountriesViewHolder(View itemView) {
            super(itemView);

            countryName = itemView.findViewById(R.id.tv_country_name);
            tvLanguage = itemView.findViewById(R.id.tv_language);
            imgFlag = itemView.findViewById(R.id.img_flag);

        }
    }
}
