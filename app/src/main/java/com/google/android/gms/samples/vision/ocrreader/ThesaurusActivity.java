package com.google.android.gms.samples.vision.ocrreader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ThesaurusActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Thesaurus>> {


    ThesaurusAdapter adapter;
    private static final int BOOK_LOADER_ID = 1;
    ProgressBar bar;
    String easy1;


    private String API_INITIAL_QUERY = "http://words.bighugelabs.com/api/2/a6948d126f7a6a6e6229be2e6ce788d0";//like/json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thesaurus);
        Intent i = getIntent();
        easy1 = i.getExtras().getString("easy");

        adapter = new ThesaurusAdapter(this, new ArrayList<Thesaurus>());
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(BOOK_LOADER_ID, null, this);

    }


    public String returnFinalQuery() {


        Uri baseUri = Uri.parse(API_INITIAL_QUERY);
        Uri.Builder uriBuilder = baseUri.buildUpon();

       /*uriBuilder.appendPath(s).appendQueryParameter("order-by", "newest")
                .appendQueryParameter("show-fields", "thumbnail")
                .appendQueryParameter("api-key", "test");*/
       uriBuilder.appendPath(easy1);
       //System.out.print(easy1);
       uriBuilder.appendPath("json");

        String url = uriBuilder.toString();
        return url;

    }


    @Override
    public Loader<ArrayList<Thesaurus>> onCreateLoader(int id, Bundle args) {
        return new ThesaurusLoader(this, returnFinalQuery());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Thesaurus>> loader, ArrayList<Thesaurus> thesauruses) {
        adapter.clear();
        if (thesauruses != null && !thesauruses.isEmpty()) {
            adapter.addAll(thesauruses);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Thesaurus>> loader) {
    adapter.clear();
    }


}
