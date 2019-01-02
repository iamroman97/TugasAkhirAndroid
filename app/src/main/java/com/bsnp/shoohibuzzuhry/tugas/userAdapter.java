package com.bsnp.shoohibuzzuhry.tugas;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class userAdapter extends ArrayAdapter<User>
{
	HashMap<Integer, Drawable> kategori_icon;
	public userAdapter(Context ctx, ArrayList<User> users){
		super(ctx, 0, users);
		kategori_icon=new HashMap<Integer, Drawable>();
		kategori_icon.put(R.id.form_makanan, ctx.getDrawable(R.drawable.makanan));
		kategori_icon.put(R.id.form_olahraga, ctx.getDrawable(R.drawable.olahraga2));
	}
	public static class ViewHolder{
		TextView judul;
		TextView isi;
		ImageView kategori;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		User pengguna=getItem(position);
		ViewHolder holder;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
			holder.judul=(TextView) convertView.findViewById(R.id.judul);
			holder.isi=(TextView) convertView.findViewById(R.id.isi);
			holder.kategori=(ImageView) convertView.findViewById(R.id.kategori);
			convertView.setTag(holder);
		}else{
			holder=(userAdapter.ViewHolder) convertView.getTag();
		}
		holder.judul.setText(pengguna.getJudul());
		holder.isi.setText(pengguna.getIsi());
		holder.kategori.setImageDrawable(kategori_icon.get(pengguna.getKategori()));
		return convertView;
	}
}
