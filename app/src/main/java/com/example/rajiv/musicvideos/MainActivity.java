package com.example.rajiv.musicvideos;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Music> array;
    ListView listView;
    TextView txtTst;
    CustomViewAdapter adapter;
    EditText inputSearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtTst=(TextView)findViewById(R.id.txtTest);



        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo datac = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null & datac != null)
                && (wifi.isConnected() | datac.isConnected())) {
            setContentView(R.layout.activity_main);
        }else{
            //no connection
            Toast toast = Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_LONG);
            toast.show();
        }


        array=new ArrayList<>();


        listView=(ListView) findViewById(R.id.listV);
        listView.setOnItemClickListener(this);

        inputSearch = (EditText) findViewById(R.id.inputSearch);
        adapter = new CustomViewAdapter(getApplicationContext(),R.layout.list_item, array);



        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = inputSearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);

//                    onRefresh();

//                    if (HomeFragment.this.array111 != null)
//                        ((HomeFragment) HomeFragment.this.array111).getFilter().filter(arg0);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
//                    if (YourClassName.this.adapter != null)
//                        ((YourAdapterClassName) YourClassName.this.adapter).getFilter().filter(arg0);
//
            }

        });

        listView.setAdapter(adapter);

        runOnUiThread(  new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("https://script.google.com/macros/s/AKfycbygukdW3tt8sCPcFDlkMnMuNu9bH5fpt7bKV50p2bM/exec?id=1wLX4gpmiyx8X7a-ByJBH_FB00r3bf32J1yodVNKOdek&sheet=music");
            }
        });




    }





    class ReadJSON extends AsyncTask<String,Integer,String> {


        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content){

            try {
                JSONObject jsonObject=new JSONObject(content);
                JSONArray jsonArray=jsonObject.getJSONArray("music");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject prdobj = jsonArray.getJSONObject(i);

                    String image1= prdobj.getString("image");
                    String name1=prdobj.getString("name");
                    String video1=prdobj.getString("video");
                    String id1=prdobj.getString("id");


                    array.add(new Music(image1,name1,video1,id1));


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

//            CustomViewAdapter adapter=new CustomViewAdapter(getApplicationContext(),R.layout.list_item, array);
//            listView.setAdapter(adapter);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Music prod =array.get(position);
        String image=prod.getImage();
        String name=prod.getName();
        String video=prod.getVideo();

        Intent intent=new Intent(getApplicationContext(),Video_Page.class);

        intent.putExtra("image",image);
        intent.putExtra("name",name);
        intent.putExtra("video",video);

        startActivity(intent);
    }



    private static String readURL(String theUrl) {

        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(theUrl);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line + "\n");
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();

    }

}