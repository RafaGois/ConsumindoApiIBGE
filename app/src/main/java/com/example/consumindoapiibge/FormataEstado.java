package com.example.consumindoapiibge;

public class FormataEstado {

    public static String formata (String estado) {
        char [] vet = estado.toCharArray();

        String estadoFormatado = "";

        for (char letra : vet) {
            if (Character.isUpperCase(letra)) {
                estadoFormatado += " ";
            }
            estadoFormatado += letra;
        }

        return estadoFormatado;
    }

}
