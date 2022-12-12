package com.example.consumindoapiibge;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.consumindoapiibge.objetos.Estado;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ProgressDialog carregando;
    Spinner spinnerEstados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerEstados = findViewById(R.id.spinnerEstados);

        String respostaIBGE = null;

        SegundoPlano segundoPlano = new SegundoPlano(this);
        try {
            respostaIBGE = segundoPlano.execute().get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Log.d("Estados", respostaIBGE);

        Gson gsonEstados = new GsonBuilder().setPrettyPrinting().create();

        Estado[] estados = gsonEstados.fromJson(respostaIBGE,Estado[].class);

        ArrayList<String> estadosParaSpinner = new ArrayList<>();

        for (Estado estado : estados) {

            estadosParaSpinner.add(estado.getNome());
        }

        Collections.sort(estadosParaSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, estadosParaSpinner);
        spinnerEstados.setAdapter(adapter);

    }
}