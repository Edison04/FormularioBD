package com.df.formulariobd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView id;
    private TextView nombre;
    private TextView poblacion;
    private TextView latitud;
    private TextView longitud;
    private Button agregar;
    MyDbHelper db = new MyDbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        id = findViewById(R.id.txtId);
        nombre = findViewById(R.id.txtNombre);
        poblacion = findViewById(R.id.txtPoblacion);
        latitud = findViewById(R.id.txtLatitud);
        longitud = findViewById(R.id.txtLongitud);
        agregar = findViewById(R.id.btnAgregar);

        agregar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        agregarCiudad();
        listarCiudades();
        limpiarCampos();
    }

    private void agregarCiudad() {
        int _id = Integer.parseInt(String.valueOf(this.id.getText()));
        String _nombre = String.valueOf(this.nombre.getText());
        String _poblacion = String.valueOf(this.poblacion.getText());
        String _latitud = String.valueOf(this.latitud.getText());
        String _longitud = String.valueOf(this.longitud.getText());

        Ciudad ciudad = new Ciudad(_id, _nombre, _poblacion, _latitud, _longitud);

        db.insertArea(db.getWritableDatabase(), ciudad);
    }

    private void listarCiudades(){
        ArrayList<Ciudad> ciudad = db.selectCiudad(db.getWritableDatabase());
        for(Ciudad ciudadSelect : ciudad){
            System.out.println("Ciudad: " + ciudadSelect.getNombre() + " - " + "Población:"+ ciudadSelect.getPoblacion());
        }
    }

    private void limpiarCampos(){
        this.id.setText("");
        this.nombre.setText("");
        this.poblacion.setText("");
        this.latitud.setText("");
        this.longitud.setText("");
    }
}