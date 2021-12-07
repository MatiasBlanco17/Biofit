package com.example.myapplicationbiofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplicationbiofit.database.AdminSQLiteOpenHelper;

public class Clases_act extends AppCompatActivity {

    private EditText icode,iclass,intensing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clases);

        icode=findViewById(R.id.icod);
        iclass=findViewById(R.id.iclas);
        intensing= findViewById(R.id.intens);
    }
    //Método para guardar clases
    public void guardarClases(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"biofit",null,1);
        SQLiteDatabase db = admin.getWritableDatabase(); //Le entrega permisos de sobreescritura
        //Valores que ingresa el cliente
        String codigo = icode.getText().toString();
        String clase = iclass.getText().toString();
        String intensidad = intensing.getText().toString();
        if(!codigo.isEmpty() && !clase.isEmpty() && !intensidad.isEmpty()){
            //guardo en la database
            ContentValues cont = new ContentValues(); //Me permite añadir valores
            cont.put("codigo", codigo);
            cont.put("clase", clase);
            cont.put("intensidad", intensidad);
            db.insert("clases",null,cont); //Guardo en la database
            db.close();
            Clean(); //Método para limpiar campos
            Toast.makeText(getBaseContext(), "Has guardado una clase", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getBaseContext(), "Tiene campos vacíos, por favor rellenar ", Toast.LENGTH_SHORT).show();
        }



    }
    //Método para consultar clases
    public void mostrarClases(View view)
    {
        //Obtener mi database
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"biofit",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String codigo = icode.getText().toString();
        if (!codigo.isEmpty())
        {
            Cursor file =
                    db.rawQuery("SELECT clase, intensidad FROM clases WHERE codigo="+codigo,null);
            if(file.moveToFirst()) //Revisa si nuestra consulta tiene valores
            {
                //Mostramos los campos
                iclass.setText(file.getString(0)); //consulta por posición
                intensing.setText(file.getString(1));
            }

        }else
        {
            Toast.makeText(getBaseContext(), "No hay clase asociada", Toast.LENGTH_SHORT).show();
        }

    }
    //Método para eliminar clases
    public void eliminarClases(View view)
    {
        //Obtener mi database
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"biofit",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String codigo = icode.getText().toString();
        if(!codigo.isEmpty())
        {
            //Eliminamos
            db.delete("clases","codigo="+codigo,null);
            db.close();
            Clean();
            Toast.makeText(getBaseContext(), "Has eliminado la clase: "+ codigo, Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(getBaseContext(), "El código no debe venir vacío", Toast.LENGTH_SHORT).show();
        }


    }
    //Método para actualizar clases
    public void actualizarClases(View view)
    {
        //Obtener mi database
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"biofit",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        //Valores que ingresa el cliente
        String codigo = icode.getText().toString();
        String clase = iclass.getText().toString();
        String intensidad = intensing.getText().toString();
        if(!codigo.isEmpty() && !clase.isEmpty() && !intensidad.isEmpty())
        {
            //Actualizar
            ContentValues cont = new ContentValues(); //Me permite añadir valores
            cont.put("codigo", codigo);
            cont.put("clase", clase);
            cont.put("intensidad", intensidad);
            db.update("clases", cont,"codigo="+codigo,null);
            db.close();
            Clean(); //Método para limpiar campos
            Toast.makeText(getBaseContext(), "Has actualizado una clase", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getBaseContext(), "Tiene campos vacíos, por favor rellenar ", Toast.LENGTH_SHORT).show();
        }

    }
    //Método para limpiar los campos
    public void Clean()
    {
        icode.setText("");
        iclass.setText("");
        intensing.setText("");
    }
}