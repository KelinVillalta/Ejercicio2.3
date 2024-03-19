package com.example.ejercicio23;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.BitmapFactory;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;



import com.example.ejercicio23.transacciones.photograh;

import java.util.ArrayList;

public class ListaFotografia extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_imagenes);

        ListView simpleGridView = (ListView) findViewById(R.id.listView);

        SQLiteConexion help = new SQLiteConexion(this, photograh.NameDataBase, null, 1);
        ArrayList<Fotografia> listaFotos = new ArrayList<>();
        Cursor c= help.getAll();
        int i=0;
        if(c.getCount()>0)
        {
            c.moveToFirst();
            while(c.isAfterLast()==false)
            {

                byte[] bytes=c.getBlob(c.getColumnIndexOrThrow(photograh.blobImagen));
                String descripcion = c.getString(c.getColumnIndexOrThrow(photograh.descripcion));

                Fotografia fotografia = new Fotografia(BitmapFactory.decodeByteArray(bytes, 0, bytes.length), descripcion);
                listaFotos.add(fotografia);
                c.moveToNext();
                i++;
            }

            Adaptador myAdapter=new Adaptador(this,R.layout.grid_view_items,listaFotos);
            simpleGridView.setAdapter(myAdapter);
        }
    }
}
