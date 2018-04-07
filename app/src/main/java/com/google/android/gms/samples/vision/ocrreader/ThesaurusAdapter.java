package com.google.android.gms.samples.vision.ocrreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rushali on 6/4/18.
 */

public class ThesaurusAdapter extends ArrayAdapter<Thesaurus> {

    public ThesaurusAdapter(Context context, ArrayList<Thesaurus> Items) {
        super(context, 0, Items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.the_item, parent, false);
        }
        // Get the {@link NewsItem} object located at this position in the list
        Thesaurus currentItem = getItem(position);

        // Find the TextView in the book_list_item.xml layout with the ID book_title.

        // Get the Title from the currentNewsItem object and set this text on the TextView.


        TextView SynTextView = (TextView) convertView.findViewById(R.id.syn);

        if(currentItem.getSyn()!=null) {
            String s1 = "";
            if(position==0)
             s1+= "ADJECTIVE"+"\n";
            else if(position==1)
                s1+= "NOUN"+"\n";
            else
                s1+= "VERB"+"\n";
                    s1+="Synonyms" + "\n"+"\n" ;
            String a[]=currentItem.getSyn();
            for(int i=0;i<a.length;i++)
                s1+=a[i]+"\n";
            SynTextView.setText(s1);
           // SynTextView.setText(currentItem.getSyn().toString());
        }
        else
            SynTextView.setText("");

        // Find the TextView in the book_list_item.xml layout with the ID book_author.
        TextView AntTextView = (TextView) convertView.findViewById(R.id.ant);
        // Get the Author from the currentNewsItem object and set this text on the TextView.
        if(currentItem.getAnt()!=null) {
            String s1 = "Antonyms"+"\n"+"\n";
            String a[]=currentItem.getAnt();
            for(int i=0;i<a.length;i++)
                s1+=a[i]+"\n";
            AntTextView.setText(s1);

        }
        else
            AntTextView.setText("");

        // Find the TextView in the book_list_item.xml layout with the ID book_page_count.
        TextView RelTextView = (TextView) convertView.findViewById(R.id.rel);
        // Get the Page Count from the currentNewsItem object and set this text on the TextView.
        if(currentItem.getRel()!=null){
            String s1 = "Related words "+"\n"+"\n";
            String a[]=currentItem.getRel();
            for(int i=0;i<a.length;i++)
                s1+=a[i]+"\n";
            RelTextView.setText(s1);}
        //RelTextView.setText(currentItem.getRel().toString());}
        else
            RelTextView.setText("");

        TextView SimView = (TextView) convertView.findViewById(R.id.sim);

        if(currentItem.getSim()!=null){
            String s1 = "Similar words"+"\n"+"\n";
            String a[]=currentItem.getSim();
            for(int i=0;i<a.length;i++)
                s1+=a[i]+"\n";
            SimView.setText(s1);}
            //SimView.setText(currentItem.getSim().toString());}
        else
            SimView.setText("");


        return convertView;
    }

}
