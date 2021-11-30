package com.example.voto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
DBmain dBmain;
SQLiteDatabase sqLiteDatabase;
RadioButton rb_nulo, rb_gabriel, rb_jose;
Button votar,ver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dBmain= new DBmain(MainActivity.this);
        rb_nulo =(RadioButton)findViewById(R.id.btn_nulo);
        findid();
        insertData();
    }

    private void insertData() {
        votar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues= new ContentValues();
                if (rb_nulo.isChecked()) {
                    contentValues.put("name", "Nulo");
                } else if (rb_gabriel.isChecked()) {
                    contentValues.put("name", rb_gabriel.getText().toString());
                } else if (rb_jose.isChecked()) {
                    contentValues.put("name", rb_jose.getText().toString());
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Voto en blanco");
                    builder.setMessage("No ha seleccionado ninguna opción, desea votar en blanco?");
                    builder.setPositiveButton("OK.", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            contentValues.put("name", "blanco");
                            Long rec = sqLiteDatabase.insert("tablavotos", null, contentValues);
                        }
                    });
                    builder.setNegativeButton("VOLVER",null);
                    builder.setCancelable(true);
                    builder.show();

                }

                sqLiteDatabase=dBmain.getWritableDatabase();
                Long rec = sqLiteDatabase.insert("tablavotos", null, contentValues);
                if (rec != null) {
                    Toast.makeText(MainActivity.this, "Voto insertado.", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("Voto no se ha podido insertar correctamente.");
                    builder.setPositiveButton("OK.", null);
                    builder.setCancelable(true);
                    builder.show();
                }
            }
        });
        //mostrar votos cuando se presione ver
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisplayData.class);
                startActivity(intent);
            }
        });
    }

    private void findid() {

        rb_gabriel =(RadioButton)findViewById(R.id.btn_gabriel);
        rb_jose =(RadioButton)findViewById(R.id.btn_jose);
        votar = (Button) findViewById(R.id.btn_votar);
        ver = (Button) findViewById(R.id.btn_ver);
    }
}































