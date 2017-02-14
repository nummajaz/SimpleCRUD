package jazuli.com.realm.belajarSQL;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jazuli.com.realm.R;
import jazuli.com.realm.belajarSQL.helper.My;

public class Create extends AppCompatActivity {

    EditText edid,ednama,edasal,edskill;
    My my;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edid = (EditText) findViewById(R.id.id_santri);
        ednama = (EditText) findViewById(R.id.nama_santri);
        edasal = (EditText) findViewById(R.id.asal_kota);
        edskill = (EditText) findViewById(R.id.skill);

        my = new My(this);
        Button button = (Button) findViewById(R.id.btn_simpan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = edid.getText().toString();
                String nama = ednama.getText().toString();
                String asal = edasal.getText().toString();
                String skill = edskill.getText().toString();

                if (id.length()>0 &&
                        nama.length()>2 &&
                        asal.length()>2 &&
                        skill.length()>2){
                    boolean isSaved = my.createDataSantri(id,nama,asal,skill);
                    if (isSaved){
                        //data berhasil di simpan
                        Toast.makeText(Create.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Create.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Create.this, "Lengkapi data dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
