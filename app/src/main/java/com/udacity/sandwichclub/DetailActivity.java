package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView alsoKnown;
    private TextView ingre;
    private TextView placeOfOrigin;
    private TextView description;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();




        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);//it
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich ) {

        alsoKnown = (TextView)findViewById(R.id.also_known_tv);
        ingre = (TextView)findViewById(R.id.ingredients_tv);
        placeOfOrigin = (TextView)findViewById(R.id.origin_tv);
        description = (TextView)findViewById(R.id.description_tv);

        //Prepare AlosKnown
        List also_list =  sandwich.getAlsoKnownAs();
        String also_str="";

        for(int i=0 ; i<also_list.size() ; i++ ){
            if(i !=also_list.size()-1) {
                also_str += also_list.get(i) + " & ";
            }else{
                also_str += also_list.get(i);
            }
        }


        // prepare ingre
        List ingres =  sandwich.getIngredients();
        String ingres_str="";

        for(int i=0 ; i<ingres.size() ; i++ ){
            if (i !=ingres.size()-1) {
                ingres_str += ingres.get(i) + " & ";
            }else{
                ingres_str += ingres.get(i);
            }
        }

        description.setText(sandwich.getDescription());
        placeOfOrigin.setText(sandwich.getPlaceOfOrigin());
        ingre.setText(ingres_str);
        alsoKnown.setText(also_str);



    }
}
