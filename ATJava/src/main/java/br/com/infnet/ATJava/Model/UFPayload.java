package br.com.infnet.ATJava.Model;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Data
public class UFPayload {
    private ArrayList<Integer> ufList = getUFList();

    public ArrayList<Integer> getUFList(){
        ArrayList<Integer> ufList = new ArrayList<>();
        ufList.addAll(Arrays.asList( 12, 27, 13, 16, 29, 23, 53, 32, 52, 21, 51, 50, 31, 15, 25, 41,
                26, 22, 33, 24, 43, 11, 14, 42, 35, 28, 17));
        return ufList;
    }

}
