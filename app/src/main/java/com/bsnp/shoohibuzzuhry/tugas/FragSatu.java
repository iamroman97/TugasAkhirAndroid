package com.bsnp.shoohibuzzuhry.tugas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragSatu extends Fragment {

    public FragSatu () {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewFrag1 = inflater.inflate(R.layout.activity_frag_satu, container, false);
        return viewFrag1;
    }
}
