package com.example.myapplicationbiofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import Objetos.Insumos;

public class Insumos_act extends AppCompatActivity {

    private Spinner insumos;
    private TextView detalle;
    private RatingBar calificar;
    private Insumos in=new Insumos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insumos);

        insumos=findViewById(R.id.sp);
        detalle=findViewById(R.id.ti);
        calificar=findViewById(R.id.rb);

        detalle.setVisibility(View.INVISIBLE);

        //Recibo los extras
        Bundle bun = getIntent().getExtras(); //recibo el intent
        String[] listado = bun.getStringArray("insumos"); //obtengo mi referencia

        ArrayAdapter adaptInsumos = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listado);
        insumos.setAdapter(adaptInsumos);
    }
    //Método para calcular insumos
    public void Calcular(View view){
        String opcion = insumos.getSelectedItem().toString();//Obtener lo seleccionado
        int resultado = 0;
        int valor = 0;
        int stock = 0;
        //Recorre las opciones
        for(int i = 0; i < opcion.length(); i++) //(declaración, condición, incremento)
        {
            if(opcion.equals(in.getInsumos()[i]))
            {
                stock = in.getStock()[i];
                valor = in.getPrecios()[i]; //Obtener precio
                resultado = in.anadirAdicional(in.getPrecios()[i],500);
                calificar.setRating(i);
                break;
            }
        }
        detalle.setVisibility(View.VISIBLE);
        detalle.setText("Producto: " + opcion + "\n"+ stock +" unidades en stock.\nValor : "+ valor +"\nEl valor total es: " + resultado); //muestro las opciones
    }
}