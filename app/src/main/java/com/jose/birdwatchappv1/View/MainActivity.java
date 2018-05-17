package com.jose.birdwatchappv1.View;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jose.birdwatchappv1.Presenter.MainFragmentPresenter;
import com.jose.birdwatchappv1.R;
import com.jose.birdwatchappv1.Utilities.HttpHandler;

public class MainActivity extends AppCompatActivity implements MainInterface{

    private MainFragmentPresenter mainPresenter;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter= new MainFragmentPresenter();
        //new GetBirds().execute();

    }

    private class GetBirds extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://virtual.lab.inf.uva.es:20072/birds/";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);


            return null;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    // según la opción de menu elegida se inicia una actividad u otra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mainPresenter.onOptionsItemSelected(item);
        return true;
    }

    @Override
    protected void onDestroy() {
        mainPresenter.onDestroy();
        super.onDestroy();
    }

}
