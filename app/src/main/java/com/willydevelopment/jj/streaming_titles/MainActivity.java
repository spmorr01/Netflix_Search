package com.willydevelopment.jj.streaming_titles;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.net.codeusa.NetflixRoulette;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    static NetflixRoulette nflxr = new NetflixRoulette();
    TextView textView;
    EditText editText;
    public static String movieTitle;
    public static String moviePosterURL;
    private boolean movieIsFound;
    private ProgressBar progressSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        progressSpinner = (ProgressBar)findViewById(R.id.progressBar);
        progressSpinner.setVisibility(View.GONE);
    }

    public void onGetInfoButtonClicked (View v) throws IOException, JSONException {
        progressSpinner.setVisibility(View.VISIBLE);
        movieTitle = editText.getText().toString();
        try {
            moviePosterURL = nflxr.getMediaPoster(movieTitle);
            movieIsFound = true;
        } catch (IOException e) {
            progressSpinner.setVisibility(View.GONE);
            textView.setText("No such title.");
            movieIsFound = false;
        }
        if (movieIsFound == true) {
            Intent searchResults = new Intent(this, ResultsActivity.class);
            startActivity(searchResults);
            progressSpinner.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
