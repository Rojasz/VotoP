package com.example.voto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DisplayData extends AppCompatActivity {
DBmain dBmain;
SQLiteDatabase sqLiteDatabase;
TextView nulo, gabriel, jose, blanco;
String []name;

int[]id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        dBmain = new DBmain(DisplayData.this);
        nulo= (TextView) findViewById(R.id.txt_nulo);
        gabriel= (TextView) findViewById(R.id.txt_gabriel);
        jose= (TextView) findViewById(R.id.txt_jose);
        blanco = (TextView) findViewById(R.id.txt_blanco);


        displayData();
    }


    private void displayData() {
        //----------TRAER LA INFO DE LA BD -------------
        Integer TotalNulo=0,TotalGabriel=0,TotalJose=0, TotalBlanco=0;
        SQLiteDatabase db;
        DBmain conn = new DBmain(getApplicationContext());
        db= conn.getReadableDatabase();
        Cursor C =db.query("tablavotos",null,null,null,null,null,null);
        if(C!=null)
        {
            if(C.moveToFirst())
            {
                do{
                    if(C.getString(1).equals("Nulo"))
                    {
                        TotalNulo++;
                    }
                    else if(C.getString(1).equals("Gabriel Boric"))
                    {
                        TotalGabriel++;

                    } else if (C.getString(1).equals("José Antonio Kast")){
                        TotalJose++;
                    } else if (C.getString(1).equals("blanco")){
                        TotalBlanco++;
                    }
                }
                while(C.moveToNext());
            }

        }
        nulo.setText("Votos nulos: "+TotalNulo);
        gabriel.setText("Gabriel: "+TotalGabriel);
        jose.setText("José: "+TotalJose);
        blanco.setText("Votos en Blanco: "+TotalBlanco);
    }
}














