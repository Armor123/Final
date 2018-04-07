package com.google.android.gms.samples.vision.ocrreader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by rushali on 6/4/18.
 */

public class ThesaurusLoader extends AsyncTaskLoader<ArrayList<Thesaurus>> {

    private String mUrl;

    public ThesaurusLoader(Context context, String mUrl) {
        super(context);
        this.mUrl = mUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Thesaurus> loadInBackground() {
        if (mUrl == null)
            return null;
        ArrayList<Thesaurus> newItems = QueryUtils.fetchNewsData(mUrl);
        return newItems;
    }
}
