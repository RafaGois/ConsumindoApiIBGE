package com.example.consumindoapiibge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.consumindoapiibge.adapter.Adapter;
import com.example.consumindoapiibge.objetos.Estado;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ProgressDialog carregando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        String respostaIBGE = null;

        SegundoPlano segundoPlano = new SegundoPlano(this);
        try {
            respostaIBGE = segundoPlano.execute().get();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        Gson gsonEstados = new GsonBuilder().setPrettyPrinting().create();

        Estado[] estados = gsonEstados.fromJson(respostaIBGE,Estado[].class);

        List<Estado> list = Arrays.asList(estados);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        Adapter adapter = new Adapter(list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

    }
}