package br.com.infnet.ATJava.Util;


import br.com.infnet.ATJava.Model.UF;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Slf4j
public class ApiService {
    public UF getApi(int ufcode){
        try {
            String ufcodeString = String.valueOf(ufcode);
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .version(HttpClient.Version.HTTP_2)
                    .uri(new URI("https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + ufcodeString))
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
            UF uf = mapper.readValue(httpResponse.body(), UF.class);
            log.info("API Consumida com sucesso");
            return uf;
        }
        catch (IOException | InterruptedException | URISyntaxException  ex){
            log.error("Api Não foi consumida: " + ex.getMessage());
            throw new RuntimeException("Erro:" + ex.getMessage());
        }
    }
}