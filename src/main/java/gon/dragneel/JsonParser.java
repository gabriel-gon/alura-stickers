package gon.dragneel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe responsavel por pegar o texto do Json e transformar em uma lista de Maps String
 *
 */
public class JsonParser {

    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");
    
    /**
     * Metodo que recebe o Json em String e transforma em Uma Lista de Maps String
     * @param json Json em String
     * @return um List de Maps string
     */
    public static List<Map<String, String>> parse(String json) {

        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Não encontrou items.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> dados = new ArrayList<Map<String, String>>();

        for (String item : items) {
            Map<String, String> atributosItem = new HashMap<String, String>();

            Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);
            while (matcherAtributosJson.find()) {
                String atributo = matcherAtributosJson.group(1);
                String valor = matcherAtributosJson.group(2);
                atributosItem.put(atributo, valor);
            }

            dados.add(atributosItem);
        }

        return dados;

    }
}