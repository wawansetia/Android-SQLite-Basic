package net.maswawan.sqlitecrud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainCreate extends AppCompatActivity {

    private DBHandler dbHandler;
    private EditText edNama,edKelas;
    private String $nama,$kelas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        dbHandler = new DBHandler(this);

        edNama = (EditText)findViewById(R.id.edNama);
        edKelas = (EditText)findViewById(R.id.edKelas);
        Button btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                $nama = String.valueOf(edNama.getText());
                $kelas = String.valueOf(edKelas.getText());

                if($nama.equals("")){
                    edNama.requestFocus();
                    Toast.makeText(MainCreate.this,"Silahkan isi nama",Toast.LENGTH_SHORT).show();
                }else if ($kelas.equals("")){
                    edKelas.requestFocus();
                    Toast.makeText(MainCreate.this,"Silahkan isi kelas",Toast.LENGTH_SHORT).show();
                }else{
                    edNama.setText("");
                    edKelas.setText("");
                    Toast.makeText(MainCreate.this,"Data telah ditambahkan",Toast.LENGTH_SHORT).show();
                    dbHandler.CreateMahasiswa(new ModalMahasiswa(null,$nama,$kelas));
                }
            }
        });
    }
}
