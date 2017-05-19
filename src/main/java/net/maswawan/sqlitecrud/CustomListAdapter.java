package net.maswawan.sqlitecrud;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wawan on 5/18/2017.
 */
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ModalMahasiswa> mhsItems;

    public CustomListAdapter(Activity activity, List<ModalMahasiswa> mhsItems){
        this.activity = activity;
        this.mhsItems = mhsItems;
    }

    @Override
    public int getCount() {
        return mhsItems.size();
    }

    @Override
    public Object getItem(int location) {
        return mhsItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null)
                convertView = inflater.inflate(R.layout.costum_list,null);

            TextView nama = (TextView)convertView.findViewById(R.id.tvNama);
            TextView kelas= (TextView)convertView.findViewById(R.id.tvKelas);

            ModalMahasiswa mahasiswa = mhsItems.get(position);

            nama.setText(mahasiswa.get_nama());
            kelas.setText(mahasiswa.get_kelas());
            return convertView;

    }
}
