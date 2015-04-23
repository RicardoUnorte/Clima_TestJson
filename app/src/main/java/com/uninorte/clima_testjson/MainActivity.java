package com.uninorte.clima_testjson;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.JarEntry;


public class MainActivity extends ActionBarActivity {

    private ProgressDialog progressDialog;
    private static String url = "http://api.openweathermap.org/data/2.5/forecast/daily?id=3689147";
    JarEntry temperatura = null;
    //ArrayList<DataEntry> listatemperatura;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


private boolean isNewtworkAv(){
    ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo =  manager.getActiveNetworkInfo();

    boolean isNetworkAvaible = false;
    if (networkInfo != null && networkInfo.isConnected()) {
        isNetworkAvaible = true;
        Toast.makeText(this, "Network is available ", Toast.LENGTH_LONG)
                .show();
    } else {
        Toast.makeText(this, "Network not available ", Toast.LENGTH_LONG)
                .show();
    }
    return isNetworkAvaible;
}


    public void requestData(View view){

        new obTemp().execute();

    }


    private class obTemp  extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            ServiceHandler SH = new ServiceHandler();

            //Haciendo Request de URL


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

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
