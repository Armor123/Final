package com.google.android.gms.samples.vision.ocrreader;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TransActivity extends AppCompatActivity implements OnItemSelectedListener {

    private static final String API_KEY = "AIzaSyCVPpB29yueOXrutpOipksvB_Gly7T17rQ";
    Spinner spinner;
    Spinner words;
    String type;
    Button b;
    Button b1;
    String easy;
    TextView textView;
    LinearLayout l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View view = this.getWindow().getDecorView();
       // view.setBackgroundColor(Color.WHITE);
        setContentView(R.layout.activity_trans);
        l=(LinearLayout)findViewById(R.id.layouts);

        Intent i = getIntent();
        easy = i.getExtras().getString("sent");
        textView = (TextView) findViewById(R.id.text);
        spinner = (Spinner)findViewById(R.id.spinner);
        words = (Spinner)findViewById(R.id.words);
        b = (Button)findViewById(R.id.btrans);
        b1 = (Button)findViewById(R.id.Thesaurus);

        List<String> categories1 = new ArrayList<String>();

        String[] arr = easy.split(" ");

        for ( String ss : arr) {

            categories1.add(ss);
        }
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        words.setAdapter(dataAdapter1);
        words.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Arabic");
        categories.add("French");
        categories.add("Tamil");
        categories.add("Telugu");
        categories.add("Hindi");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);
        textView.setText(easy);
        final Handler textViewHandler = new Handler();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        TranslateOptions options = TranslateOptions.newBuilder()
                                .setApiKey(API_KEY)
                                .build();
                        Translate translate = options.getService();
                        final Translation translation = translate.translate(easy,
                                        Translate.TranslateOption.targetLanguage(type));
                        textViewHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (textView != null) {
                                    textView.setText(translation.getTranslatedText());
                                }
                            }
                        });
                        return null;
                    }
                }.execute();


            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TransActivity.this,ThesaurusActivity.class);
                i.putExtra("easy",easy);
                startActivity(i);
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

       String s = adapterView.getItemAtPosition(i).toString();
       if(s=="Arabic")
          type="ar";
       else if(s=="French")
           type="fr";
       else if(s=="Tamil")
           type="ta";
       else if(s=="Telugu")
           type="te";
       else if(s=="Hindi")
           type="hi";
       else{

           easy = s;
           textView.setText(easy);

       }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

