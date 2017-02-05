package me.arifwidi.nilaimahasiswa;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Mahasiswa> {

    private final List<Mahasiswa> list;
    private final Context context;

    public CustomAdapter(Context context, List<Mahasiswa> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Mahasiswa mahasiswa = list.get(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout, parent, false);
        }

        TextView nama = (TextView) convertView.findViewById(R.id.list_nama);
        TextView nim = (TextView) convertView.findViewById(R.id.list_nim);
        nama.setText(mahasiswa.getNama());
        nim.setText(mahasiswa.getNim().toString());

        return convertView;
    }
}
