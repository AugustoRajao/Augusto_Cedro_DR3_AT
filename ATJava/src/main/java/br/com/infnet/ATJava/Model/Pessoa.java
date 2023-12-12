package br.com.infnet.ATJava.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
@NoArgsConstructor@AllArgsConstructor@Builder
public class Pessoa {
    private int id;
    private String nome;
    private int idade;
    private String[] caracteristicas;
    private String estado;
}
