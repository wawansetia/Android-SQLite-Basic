package net.maswawan.sqlitecrud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainRead extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView mListView;
    private CustomListAdapter adapter;
    private DBHandler dbHandler;
    private List<ModalMahasiswa> mhsLists = new ArrayList<ModalMahasiswa>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        dbHandler = new DBHandler(this);
        adapter = new CustomListAdapter(this,mhsLists);
        mListView = (ListView)findViewById(R.id.listView);
        mListView.setAdapter(adapter);
        mhsLists.clear();

        List<ModalMahasiswa> listMhs = dbHandler.ReadMahasiswa();
        for (ModalMahasiswa row : listMhs
             ) {
            ModalMahasiswa data = new ModalMahasiswa();
            data.set_id(row.get_id());
            data.set_nama(row.get_nama());
            data.set_kelas(row.get_kelas());

            mhsLists.add(data);

            if (mhsLists.isEmpty()){
                Toast.makeText(MainRead.this,"Tidak ada Data",Toast.LENGTH_SHORT).show();
            }
        }
        mListView.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mListView.setAdapter(adapter);
        mhsLists.clear();
        List<ModalMahasiswa> listMhs = dbHandler.ReadMahasiswa();
        for (ModalMahasiswa row : listMhs
                ) {
            ModalMahasiswa data = new ModalMahasiswa();
            data.set_id(row.get_id());
            data.set_nama(row.get_nama());
            data.set_kelas(row.get_kelas());

            mhsLists.add(data);

            if (mhsLists.isEmpty()){
                Toast.makeText(MainRead.this,"Tidak ada Data",Toast.LENGTH_SHORT).show();
            }
        }
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object obj = mListView.getItemAtPosition(position);
        ModalMahasiswa obj_item = (ModalMahasiswa)obj;
        String $id = obj_item.get_id();
        String $nama = obj_item.get_nama();
        String $kelas = obj_item.get_kelas();

        Intent i = new Intent(MainRead.this,MainUpdel.class);
        i.putExtra("Id",$id);
        i.putExtra("Nama",$nama);
        i.putExtra("Kelas",$kelas);
        startActivity(i);
    }
}
