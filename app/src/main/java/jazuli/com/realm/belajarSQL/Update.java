package jazuli.com.realm.belajarSQL;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jazuli.com.realm.R;
import jazuli.com.realm.belajarSQL.helper.My;

import static android.R.attr.id;
import static android.R.id.input;

public class Update extends AppCompatActivity {
    private List<String> listsantri;
    private My my;
    private ListView listView;
    private List<SantriModel> listModelSantri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        listView = (ListView) findViewById(R.id.listvie);
        listsantri= new ArrayList<>();
        my = new My(this);
        listModelSantri = new ArrayList<>();

        showData();
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Update.this, Create.class);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                new AlertDialog.Builder(Update.this).setTitle("Konfirmasi").
                        setMessage("Hapus Data" + listsantri.get(position)+ " ?").
                        setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String itemId = listModelSantri.get(position).getId_santri();

                        my.deleteAllDataSantri(itemId);
                        listsantri.clear();
                        Toast.makeText(getApplicationContext(), "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                        showData();
                    }
                }).show();

                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,final int position, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Update.this);
                String nama_santri = listModelSantri.get(position).getNama_santri();
                builder.setTitle("Update Name : "+ nama_santri);

                final EditText input = new EditText(Update.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setText(nama_santri);
                builder.setView(input);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {

                                String namUpdate = input.getText().toString();
                                my.updateNameSantri(listModelSantri.get(position).getId_santri(), namUpdate);
                                listModelSantri.clear();
                                listsantri.clear();
                                showData();
                            }
                        });
                        builder.setNegativeButton("kembali", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                    dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });
    }
    public void showData(){

        My my = new My(this);
        Cursor cursor = my.readAllDataSantri();

        if (cursor.moveToFirst()){
            do {
                SantriModel santriModel = new SantriModel();

                String id = cursor.getString(cursor.getColumnIndex(My.ID_SANTRI));
                String nama = cursor.getString(cursor.getColumnIndex(My.NAMA_SANTRI));
                String asal = cursor.getString(cursor.getColumnIndex(My.ASAL_KOTA));
                String skill = cursor.getString(cursor.getColumnIndex(My.SKILL));

                listsantri.add(nama + " - "+asal+" - "+skill);

                santriModel.setId_santri(id);
                santriModel.setNama_santri(nama);
                santriModel.setAsal_santri(asal);
                santriModel.setSkill_santri(skill);

                listModelSantri.add(santriModel);
            }while (cursor.moveToNext());
        }else {
            //tidak ada data
            Toast.makeText(this, "Coba Lagi", Toast.LENGTH_SHORT).show();
        }
        //prepare array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listsantri);
        //set adapter listview
        listView.setAdapter(adapter);
    }
}
