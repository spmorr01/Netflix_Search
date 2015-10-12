package com.willydevelopment.jj.streaming_titles;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.net.codeusa.NetflixRoulette;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    static NetflixRoulette nflxr = new NetflixRoulette();
    TextView textView;
    EditText editText;
    public String movieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void onGetInfoButtonClicked (View v) throws IOException, JSONException {
        movieTitle = editText.getText().toString();
        //movieInfo = nflxr.getAllData(movieTitle);
        textView.setText(nflxr.getAllData(movieTitle));


    }

    /*public static JSONObject toJsonObject(String json){
        try{
            JSONObject jsonObj = new JSONObject(json);
            return jsonObj;
        }
        catch (JSONException e)
        {
            //do something
        }
        return null;
    }*/

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
