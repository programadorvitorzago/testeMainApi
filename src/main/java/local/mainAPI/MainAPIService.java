package local.mainAPI;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import local.mainAPI.credencial.senha.CriterioNumMinimoCaractere;
import local.mainAPI.credencial.senha.CriterioPeloMenosUm;
import local.mainAPI.credencial.senha.CriterioSemRepeticao;
import local.mainAPI.credencial.senha.ICriterio;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path="/mainAPI")
public class MainAPIService {
    
    /**
     * Método rápido para testar se o servidor está on-line
     *   e recebendo requisições HTTP.
     * @return 
     */
    @RequestMapping(method = {RequestMethod.POST}, path = "IsAlive")
    public String IsAlive() {
        return "OK";
    }

//------------------------------------------------    
//  2ª Solução: Elegante
//------------------------------------------------    

    private static final String CARACTERE_ESPECIAL = "!@#$%^&*()-+";
    private static final String CARACTERE_ISDIGITO = "0123456789";
    private static final String CARACTERE_ISMINUSCULO = "abcdefghijklmnopqrstuvwxyz";
    private static final String CARACTERE_ISMAIUSCULO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int NUM_CARACTERES_MINIMO = 9;
    private List<ICriterio> getListaCriterio()
    {
        List<ICriterio> listaCriterio;
        
        // Se a interção for parametricar os critérios
        //    este é o ponto para tal implementação
        //    Ex.: Ler critério de um XML, do banco de dados, etc
        listaCriterio = new LinkedList<>();
        listaCriterio.add(new CriterioNumMinimoCaractere(1, "Nove ou mais caracteres", NUM_CARACTERES_MINIMO));
        listaCriterio.add(new CriterioPeloMenosUm(2, "Ao menos 1 dígito", CARACTERE_ISDIGITO));
        listaCriterio.add(new CriterioPeloMenosUm(3, "Ao menos 1 letra minúscula", CARACTERE_ISMINUSCULO));
        listaCriterio.add(new CriterioPeloMenosUm(4, "Ao menos 1 letra maiúscula", CARACTERE_ISMAIUSCULO));
        listaCriterio.add(new CriterioPeloMenosUm(5, "Ao menos 1 caractere especial", CARACTERE_ESPECIAL));
        listaCriterio.add(new CriterioSemRepeticao(6, "Não possuir caracteres repetidos dentro do conjunto"));
        
        return listaCriterio;
    }
    
    @RequestMapping(method = {RequestMethod.POST}, path = "validacao")
    public String IsValid(@RequestParam String password,
            @RequestParam(required=false) Boolean whyNot)
    {
        List<ICriterio> listaCriterio = getListaCriterio();
        
        int pLen = password.length();
        for (int index = 0; index < pLen; index++) {
            char caractere = password.charAt(index);
            
            for (ICriterio iCriterio : listaCriterio) {
                iCriterio.teste(index, caractere);
            }
        }
        
        boolean resultado = listaCriterio.stream().allMatch(x -> x.getResultado());
        if (Boolean.TRUE.equals(whyNot)) {
            return Objects.toString(resultado)
                    + " : " + WhyNot(password);
        }
        else
        {
            return Objects.toString(resultado);
        }
    }
    
    private String WhyNot(String palavraChave)
    {
        List<ICriterio> listaCriterio = getListaCriterio();
        
        int pLen = palavraChave.length();
        for (int index = 0; index < pLen; index++) {
            char caractere = palavraChave.charAt(index);
            
            for (ICriterio iCriterio : listaCriterio) {
                iCriterio.teste(index, caractere);
            }
        }
        
        boolean teste = listaCriterio.stream().allMatch(x -> x.getResultado());
        if (teste) {
            return "Está OK";
        }
        else
        {
            return listaCriterio.stream()
                    .filter(x -> !x.getResultado())
                    .findFirst().get().getMensagem();
        }
    }
    
////------------------------------------------------    
////  1ª Solução: Obvjetiva    
////------------------------------------------------    
//    
//    private static final String CARACTERE_ESPECIAL = "!@#$%^&*()-+";
//    private static final int NUM_CARACTERES_MINIMO = 9;
//    
//    @RequestMapping(method = {RequestMethod.GET}, path = "IsValid")
//    public boolean IsValid(@RequestParam String password) {
//        // Critério 1)
//        int pLen = password.length();
//        if(password.length() >= NUM_CARACTERES_MINIMO)
//        {
//            int numDigito = 0;            // Critério 02 - pelo menos 1 dígito
//            int numLetraMinuscula = 0;    // Critério 03 - pelo menos 1 letra minúscula
//            int numLetraMaiuscula = 0;    // Critério 04 - pelo menos 1 letra maiuscula
//            int numCaracterEspecial = 0;  // Critério 05 - pelo menos 1 caractere especial
//            Set<Character> conjuntoCaractere = new HashSet<>(); // Critério 06 - Não pode repetir
//            
//            char caractere;
//            for (int index = 0; index < pLen; index++) {
//                caractere = password.charAt(index);
//                if(Character.isDigit(caractere))
//                {
//                    numDigito++;
//                } else if(Character.isLowerCase(caractere))
//                {
//                    numLetraMinuscula++;
//                } else if(Character.isUpperCase(caractere)){
//                    numLetraMaiuscula++;
//                } else if(CARACTERE_ESPECIAL.indexOf(caractere) >= 0)
//                {
//                    numCaracterEspecial++;
//                }
//                
//                conjuntoCaractere.add(caractere);
//                if(conjuntoCaractere.size() - 1 != index)
//                {
//                    //   Se o caractere adicionado no conjunto que não permite repetição
//                    // não for contabilizado (item repetido).  Não atendido critério 6
//                    return false;
//                }
//            }
//            
//            // se atendidos todos os critérios. Senha está OK
//            return numDigito >= 1
//                    && numLetraMinuscula >= 1 
//                    && numLetraMaiuscula >= 1
//                    && numCaracterEspecial >= 1;
//        }
//        else
//        {
//            // Não atendido o critério 01) 9 ou mais caractéres
//            return false;    
//        }
//    }    

}
