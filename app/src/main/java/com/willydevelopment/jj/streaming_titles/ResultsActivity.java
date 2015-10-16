package com.willydevelopment.jj.streaming_titles;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.lang.WordUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class ResultsActivity extends AppCompatActivity {

    TextView movieTitleTextView;
    ImageView moviePosterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        movieTitleTextView = (TextView) findViewById(R.id.movieTitleTextView);
        moviePosterImageView = (ImageView) findViewById(R.id.moviePosterImageView);
        movieTitleTextView.setText(WordUtils.capitalizeFully(MainActivity.movieTitle));
        try {
            URL posterURL = new URL(MainActivity.moviePosterURL);
            Bitmap bmp = BitmapFactory.decodeStream(posterURL.openConnection().getInputStream());
            moviePosterImageView.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            movieTitleTextView.setText("URL NOT WORKING");
        } catch (IOException e) {
            movieTitleTextView.setText("OPEN CONNECTION NOT WORKING");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
