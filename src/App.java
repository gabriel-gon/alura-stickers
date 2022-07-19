import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI uri = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        List<Map<String, String>> listaFilmes = JsonParser.parse(body);

        for (Map<String,String> lista : listaFilmes) {
            System.out.println("\u001b[1m Titulo: \u001b[m"+lista.get("title"));
            System.out.println("\u001b[1m Imagem link: \u001b[m"+lista.get("image"));
            System.out.println("\u001b[1m Nota: \u001b[m"+lista.get("imDbRating")+"");
        }
    }
}
