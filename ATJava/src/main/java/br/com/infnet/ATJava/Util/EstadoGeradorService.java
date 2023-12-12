package br.com.infnet.ATJava.Util;

import br.com.infnet.ATJava.Model.UFPayload;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EstadoGeradorService {
    public String gerarEstado(){
        Random random = new Random();
        ApiService apiService = new ApiService();
        UFPayload ufPayload = new UFPayload();
        return apiService.getApi(ufPayload.getUFList().get(random.nextInt(27))).getNome();
    }
}
