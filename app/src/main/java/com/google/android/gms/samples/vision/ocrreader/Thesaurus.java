package com.google.android.gms.samples.vision.ocrreader;

/**
 * Created by rushali on 6/4/18.
 */

public class Thesaurus {

    String[] syn;
    String[] rel;
    String[] ant;
    String[] sim;

    public Thesaurus()
    {
        syn = null;
        rel = null;
        ant = null;
        sim = null;

    }

    public Thesaurus(String[] syn,String[] rel,String[] ant,String[] sim)
    {
        this.syn = syn;
        this.rel = rel;
        this.ant = ant;
        this.sim = sim;
    }

    String[] getSyn()
    {
        return syn;
    }

    String[] getRel()
    {
        return  rel;
    }

    String[] getAnt()
    {
        return  ant;
    }

    String[] getSim()
    {
        return  sim;
    }


}
