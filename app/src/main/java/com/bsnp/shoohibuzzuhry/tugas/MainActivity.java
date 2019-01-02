package com.bsnp.shoohibuzzuhry.tugas;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import java.util.*;
import com.firebase.client.Firebase;

public class MainActivity extends Activity
{
    ListView list;
    Button addbtn;
    userAdapter adapter;
    ArrayList<User> users;
    String initialTitle;
    List<String> keyArray = new ArrayList<String>();

    Firebase mFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // setup firebase
        try{
            Firebase.getDefaultConfig().setPersistenceEnabled(true);
        }catch(Exception e){}
        Firebase.setAndroidContext(this);
        mFirebase = new Firebase("https://chatc2.firebaseio.com").child("user");
        setContentView(R.layout.main);
        list=(ListView) findViewById(R.id.list);
        addbtn=(Button) findViewById(R.id.addbtn);
        users=new ArrayList<User>();
        adapter=new userAdapter(this, users);
        list.setAdapter(adapter);
        addbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View p1) {
                formulir(null, -1);
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> p1, View p2, int itempos, long p4) {
                formulir((User) p1.getItemAtPosition(itempos), itempos);
            }
        });
        // firebase data listener
        mFirebase.addChildEventListener(new com.firebase.client.ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                keyArray.add(dataSnapshot.getKey());
                users.add(user);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot p1, String p2) {
                User u=p1.getValue(User.class);
                users.set(keyArray.indexOf(p1.getKey()), u);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot p1) {
                users.remove(keyArray.indexOf(p1.getKey()));
                keyArray.remove(p1.getKey());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot p1, String p2) {
                // TODO: Implement this method
            }

            @Override
            public void onCancelled(com.firebase.client.FirebaseError p1) {
                // TODO: Implement this method
            }
        });
    }
    private void formulir(final User inputuser, final int pos){
        View v = LayoutInflater.from(this).inflate(R.layout.form_dialog, null);
        final EditText judul = (EditText) v.findViewById(R.id.form_judul);
        final EditText isi = (EditText) v.findViewById(R.id.form_isi);
        final RadioGroup kategori=(RadioGroup) v.findViewById(R.id.form_kategori);
        if(inputuser!=null){
            judul.setText(inputuser.getJudul());
            isi.setText(inputuser.getIsi());
            kategori.check(inputuser.getKategori());
            initialTitle="Perbarui";
        }else{
            initialTitle="Tambahkan";
        }
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle(initialTitle+" Pengguna");
        dlg.setView(v);
        dlg.setPositiveButton(initialTitle, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface p1, int p2) {
                if(judul.getText().toString().length()<2||isi.getText().toString().length()<2){
                    Toast.makeText(MainActivity.this, "Data tidak valid", Toast.LENGTH_LONG).show();
                    return;
                }

                User user=new User(judul.getText().toString(), kategori.getCheckedRadioButtonId(), isi.getText().toString());
                if(inputuser==null){
                    mFirebase.push().setValue(user);
                }else{
                    mFirebase.child(keyArray.get(pos)).setValue(user);
                }

                Toast.makeText(MainActivity.this, "Pengguna berhasil di"+(initialTitle.toLowerCase()), Toast.LENGTH_SHORT).show();
            }
        });
        if(inputuser!=null){
            dlg.setNeutralButton("Hapus", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface p1, int p2) {
                    mFirebase.child(keyArray.get(pos)).removeValue();
                }
            });
        }
        dlg.setNegativeButton("Batal", null);
        dlg.show();
    }

    @Override
    protected void onDestroy() {
        mFirebase=null;
        super.onDestroy();
    }

}
