package net.maswawan.sqlitecrud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainUpdel extends AppCompatActivity {

    private EditText etNama, etKelas;
    private Button btnSave;
    private String $id,$nama,$kelas;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updel);

        dbHandler = new DBHandler(this);

        etNama = (EditText)findViewById(R.id.edNamaEdit);
        etKelas =(EditText)findViewById(R.id.edKelasEdit);
        btnSave = (Button)findViewById(R.id.btnSaveEdit);

        Intent i = getIntent();
        $id = i.getStringExtra("Id");
        $nama = i.getStringExtra("Nama");
        $kelas = i.getStringExtra("Kelas");

        etNama.setText($nama);
        etKelas.setText($kelas);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                $nama = String.valueOf(etNama.getText());
                $kelas = String.valueOf(etKelas.getText());

                if ($nama.isEmpty()){
                    etNama.requestFocus();
                    Toast.makeText(MainUpdel.this,"Silahkan isi nama",Toast.LENGTH_SHORT).show();
                }else if($kelas.isEmpty()){
                    etKelas.requestFocus();
                    Toast.makeText(MainUpdel.this,"Silahkan isi kelas", Toast.LENGTH_SHORT).show();
                }else{
                    dbHandler.UpdateMahasiswa(new ModalMahasiswa($id,$nama,$kelas));
                    Toast.makeText(MainUpdel.this,"Data berhasil diupdate",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        Button btnDelete = (Button)findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.DeleteMahasiswa(new ModalMahasiswa($id,$nama,$kelas));
                Toast.makeText(MainUpdel.this,"Data berhasil dihapus",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}
