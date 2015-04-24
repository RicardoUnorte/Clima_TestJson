package com.uninorte.clima_testjson;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.jar.JarEntry;


public class MainActivity extends ActionBarActivity {

    private ProgressDialog progressDialog;
    private static String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=Barranquilla&units=metric&cnt=6";
    JSONArray temperatura = null;
    ArrayList<Data> listatemperatura;
    private ListView listView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        long t = 1429977600 * 1000;
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date net = (new Date(t));

        String d = format.format(net);


        listatemperatura = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Network();
            }
        });



    }


private boolean Network(){
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

            String jsonSTR = SH.makeServiceCall(url,ServiceHandler.GET);

            Log.d("Respone:" , ">" + jsonSTR);

            if(jsonSTR != null){

                try {
                    JSONObject jsonObj = new JSONObject(jsonSTR);
                    temperatura = jsonObj.getJSONArray("list");
                    Log.d("Response lenght: ",">"+ temperatura.length());

                for (int i=1;i<temperatura.length();i++){
                    JSONObject c = temperatura.getJSONObject(i).getJSONObject("temp");

                    Data data = new Data();

                    data.setDia(c.getString("day"));
                    data.setMin(c.getString("min"));
                    data.setMax(c.getString("max"));
                    data.setNoche(c.getString("night"));
                    data.setTarde(c.getString("eve"));
                    data.setMañana(c.getString("morn"));

                    listatemperatura.add(data);
                }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else{

                Log.e("ServiceHandler", "No se pudo obtener datos");
            }


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
        @Override
        protected void onPostExecute(Void aVoid) {
          super.onPostExecute(aVoid);
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            Custom_Adapter adapter = new Custom_Adapter(MainActivity.this,listatemperatura);
            listView.setAdapter(adapter);

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
