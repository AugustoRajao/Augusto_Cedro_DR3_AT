package br.com.infnet.ATJava.Util;

import br.com.infnet.ATJava.Exceptions.ResourceNotFoundException;
import br.com.infnet.ATJava.Model.Pessoa;
import br.com.infnet.ATJava.Model.UF;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Data
@Service
public class PessoaService {
    private Map<Integer, Pessoa> pessoas = getPessoas();
    private int lastId = pessoas.size();
    private Map<Integer,Pessoa> getPessoas(){
            EstadoGeradorService estadoGerador= new EstadoGeradorService();
            Map<Integer, Pessoa> pessoas = new HashMap<>();
            String[] caracteristicas = {"loiro", "pele branca", "masculino"};
            Pessoa pedro = new Pessoa(1, "Pedro", 18,caracteristicas, estadoGerador.gerarEstado());
            Pessoa rafael = new Pessoa(2, "Rafael", 18,caracteristicas, estadoGerador.gerarEstado());
            Pessoa augusto = new Pessoa(3, "Augusto", 21,caracteristicas, estadoGerador.gerarEstado());
            pessoas.put(1, pedro);
            pessoas.put(2, rafael);
            pessoas.put(3, augusto);
            return pessoas;
    }

    public List<Pessoa> getAll(){
        return pessoas.values().stream().toList();
    }

    public Pessoa getById(int id) {
        if(verificaId(id)){
            return pessoas.get(id);
        }else{
            throw new ResourceNotFoundException("ID Inexistente");
        }
    }
    public List<Pessoa> getByName(String nome) {
        List<Pessoa> pessoas = getAll();
        List<Pessoa> subLista = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            if (pessoa.getNome().toLowerCase().equals(nome.toLowerCase())) {
                subLista.add(pessoa);
            }
        });
            if(subLista.isEmpty()){
               throw new ResourceNotFoundException("Nome Inexistente");
            }else{
                return subLista;
            }
    }
    public List<Pessoa> getByEstado(String estado) {
        List<Pessoa> pessoas = getAll();
        List<Pessoa> subLista = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            if (pessoa.getEstado().toLowerCase().equals(estado.toLowerCase())) {
                subLista.add(pessoa);
            }
        });
        if(subLista.isEmpty()){
            throw new ResourceNotFoundException("Estado Inexistente");
        }else{
            return subLista;
        }
    }


    public void deleteById(int id){
        if(verificaId(id)){
            pessoas.remove(id);
        }else{
            throw new ResourceNotFoundException("ID Inexistente");
        }
    }

    public void create(Pessoa pessoa){
        if(verificaPessoa(pessoa)){
            EstadoGeradorService estadoGerador = new EstadoGeradorService();
            pessoa.setEstado(estadoGerador.gerarEstado());
            int id = ++this.lastId;
            pessoa.setId(id);
            pessoas.put(pessoa.getId(), pessoa);
        }else{
            throw new ResourceNotFoundException("Dados Incompletos para o Cadastro");
        }

    }public void uptade(int id, Pessoa pessoa) {
        if(verificaId(id)){
            if(verificaPessoa(pessoa)){
                pessoa.setId(id);
                pessoas.replace(id, pessoa);
            }else{
                throw new ResourceNotFoundException("Dados Incompletos para o Cadastro");
            }
        }else{
                throw new ResourceNotFoundException("ID Inexistente");
        }
    }
    public boolean verificaId(int id){
        return pessoas.containsKey(id);
    }
    public boolean verificaPessoa(Pessoa pessoa){
        if(pessoa.getNome() == null){
            return false;
        }else if(pessoa.getCaracteristicas() == null){
            return false;
        }else  if(pessoa.getIdade() == 0){
            return false;
        }else {
            return true;
        }
    }
}
