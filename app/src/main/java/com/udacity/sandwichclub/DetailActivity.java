package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView descriptionTv;
    private TextView akaTv;
    private TextView originTv;
    private TextView ingredientsTv;

    private ImageView ingredientsIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        descriptionTv = findViewById(R.id.description_tv);
        akaTv = findViewById(R.id.aka_tv);
        originTv = findViewById(R.id.origin_tv);
        ingredientsTv = findViewById(R.id.ingredients_tv);

        ingredientsIv = findViewById(R.id.image_iv);

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

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        descriptionTv.setText(sandwich.getDescription());

        StringBuilder sb = new StringBuilder();
        for (String alias : sandwich.getAlsoKnownAs()){
            sb.append(alias);
            sb.append(", ");
        }
        akaTv.setText(sb.toString().substring(0, sb.toString().length() -2));

        originTv.setText(sandwich.getPlaceOfOrigin());

        //Reallocate to clear and use for ingredient list.
        sb = new StringBuilder();

        for (String ingredient : sandwich.getIngredients()){
            sb.append(ingredient);
            sb.append(", ");
        }

        ingredientsTv.setText(sb.toString().substring(0, sb.toString().length() -2));

    }
}
