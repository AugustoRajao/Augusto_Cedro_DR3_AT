package br.com.infnet.ATJava.Controller;

import br.com.infnet.ATJava.Exceptions.ResourceNotFoundException;
import br.com.infnet.ATJava.Model.Pessoa;
import br.com.infnet.ATJava.Model.ResponsePayload;
import br.com.infnet.ATJava.Util.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    PessoaService pessoaService;

    @GetMapping
    public ResponseEntity GetAll(@RequestParam(required = false) String nome,
                                 @RequestParam(required = false) String estado){
        try {
            List<Pessoa> pessoas = new ArrayList<>();
            if (nome != null) {
                pessoas = pessoaService.getByName(nome);
            }
            else if(estado != null){
                pessoas = pessoaService.getByEstado(estado);
            }
            else {
                pessoas = pessoaService.getAll();
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(pessoas);

        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponsePayload(ex.getMessage()));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity GetById(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(pessoaService.getById(id));
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponsePayload(ex.getMessage()));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity DeleteById(@PathVariable int id){
        try{
            pessoaService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponsePayload("Cliente deletado com sucesso"));
        }catch (ResourceNotFoundException ex){
            ResponsePayload responsePayload = new ResponsePayload(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responsePayload);
        }
    }
    @PostMapping("/criar")
    public ResponseEntity Post(@RequestBody Pessoa pessoa){
        try {
            pessoaService.create(pessoa);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePayload("Cliente Criado com Sucesso"));
        } catch (ResourceNotFoundException ex) {
            ResponsePayload responsePayload = new ResponsePayload(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responsePayload);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity Update(@PathVariable int id, @RequestBody Pessoa pessoa)
    {
      try{
          pessoaService.uptade(id, pessoa);
          return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponsePayload("Cliente alterado com sucesso"));
      }catch (ResourceNotFoundException ex){
          ResponsePayload responsePayload = new ResponsePayload(ex.getMessage());
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responsePayload);
      }
    }
}

