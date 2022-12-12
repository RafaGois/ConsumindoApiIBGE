package com.example.consumindoapiibge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.consumindoapiibge.objetos.Estado;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String respostaIBGE = null;

        SegundoPlano segundoPlano = new SegundoPlano();
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

        for (Estado estado : estados) {
            Log.d("Id",String.valueOf(estado.getId()));
            Log.d("Nome", estado.getNome());
            Log.d("Sigla", estado.getSigla());
            Log.d("---","---");
        }
    }
}