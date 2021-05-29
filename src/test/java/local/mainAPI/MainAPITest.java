package local.mainAPI;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class MainAPITest {

    @Test
    public void contextLoads() {

        Dictionary<String, String> oraculo = new Hashtable<>();
        MainAPIService mainApi = new MainAPIService();
        
        oraculo.put("", "false");
        oraculo.put("aa", "false");
        oraculo.put("ab", "false");
        oraculo.put("AAAbbbCc", "false");  
        oraculo.put("AbTp9!foo", "false");
        oraculo.put("AbTp9!foA", "false");
        oraculo.put("AbTp9 fok", "false");
        oraculo.put("AbTp9!fok", "true");
        oraculo.put("abcdefghijklmnopqrstuvwxyz", "false"); // false
        oraculo.put("abcdefghijklmnopqrstuvwxyz 1A!", "true");
        oraculo.put("abcdefghij1A!klmnopqrstuvwxyz", "true"); 
        oraculo.put("1A!abcdefghijklmnopqrstuvwxyz", "true"); 
        oraculo.put("abcd1efghijklAmnopqrstuv!wxyz", "true"); 
        oraculo.put("abcd1efghijklAmnopqrstuv!wxyza", "false"); // repete o a
        oraculo.put("abcd1efghijklAmnopqrstuv!wxyz!", "false"); // repete o !

        Enumeration<String> enumeration = oraculo.keys();
        String palavraChave;
        String resultado;
        while(enumeration.hasMoreElements()) 
        {
            palavraChave = enumeration.nextElement();
            resultado = mainApi.IsValid(palavraChave, false);
            Assert.isTrue( Objects.equals(resultado, oraculo.get(palavraChave)) 
                    , String.format("Senha '%s':%s - IsValid:", palavraChave, oraculo.get(palavraChave), resultado));
        }
    }

}
