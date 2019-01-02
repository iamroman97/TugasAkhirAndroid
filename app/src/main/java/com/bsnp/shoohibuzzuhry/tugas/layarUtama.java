package com.bsnp.shoohibuzzuhry.tugas;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class layarUtama extends AppCompatActivity implements View.OnClickListener{

    //Variable
    Button ChangeFrag;
    Boolean kondisi = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_utama);
        ChangeFrag = (Button)findViewById(R.id.change);
        ChangeFrag.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.change:
                if(kondisi){
                    FragSatu satu = new FragSatu();
                    FragmentManager FM = getSupportFragmentManager();
                    FragmentTransaction FT = FM.beginTransaction();
                    FT.replace(R.id.main_menu, satu);
                    FT.commit();
                    kondisi = false;
                }else {
                    FragDua dua = new FragDua();
                    FragmentManager FM2 = getSupportFragmentManager();
                    FragmentTransaction FT2 = FM2.beginTransaction();
                    FT2.replace(R.id.main_menu, dua);
                    FT2.commit();
                    kondisi = true;
                }
                break;
        }
    }
}
