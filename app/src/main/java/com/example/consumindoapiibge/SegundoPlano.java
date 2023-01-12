package com.example.consumindoapiibge;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class SegundoPlano extends AsyncTask<Void, Void, String> {

    MainActivity mainActivity = new MainActivity();
    ProgressDialog carregando = mainActivity.carregando;

    Context spContext;

    public SegundoPlano(Context context) {
        this.spContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        carregando = ProgressDialog.show(spContext, "Carregando estados...","Por favor, aguarde.");
    }

    @Override
    protected String doInBackground(Void... voids) {

        StringBuilder respostaIBGE = new StringBuilder();

        try {

            URL urlEstados = new URL("https://servicodados.ibge.gov.br/api/v1/localidades/estados/");

            HttpURLConnection conexao = (HttpURLConnection) urlEstados.openConnection();

            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("Content-type","application/json");
            conexao.setDoOutput(true);
            conexao.setConnectTimeout(3000);
            conexao.connect();

            Scanner scanner = new Scanner(urlEstados.openStream());

            while (scanner.hasNext()) {
                respostaIBGE.append(scanner.next());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return respostaIBGE.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            carregando.dismiss();
        }
    }
}
