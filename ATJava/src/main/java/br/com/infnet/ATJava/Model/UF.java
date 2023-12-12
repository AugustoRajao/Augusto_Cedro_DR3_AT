package br.com.infnet.ATJava.Model;

import lombok.Data;

@Data
public class UF {
    private int id;
    private String sigla;
    private String nome;
    private Regiao regiao;

}
