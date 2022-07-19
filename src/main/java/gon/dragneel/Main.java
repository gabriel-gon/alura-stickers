package gon.dragneel;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import tech.guiyom.anscapes.renderer.AbstractImageRenderer;
import tech.guiyom.anscapes.renderer.RgbImageRenderer;
import tech.guiyom.anscapes.renderer.TerminalImage;


public class Main {
    public static void main(String[] args) throws Exception {
    	
    	//Obtendo as informações da API
	//Properties properties = ConfigProperties.getProp();
        //String url = "https://imdb-api.com/en/API/Top250Movies/"+properties.getProperty("prop.imdb.apiKey");
    	
        String url = "https://api.mocki.io/v2/549a5d8b";
        
        URI uri = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        //Transforma o json recebido na requisição acima em uma lista de maps 
        List<Map<String, String>> listaFilmes = JsonParser.parse(body);

        //Percorre a lista com os filmes imprimindo as informações de cada um
        for (Map<String,String> filme : listaFilmes) {
            System.out.println("\u001b[1m Titulo: \u001b[m"+filme.get("title"));
            
	    // Tranforma imprime a imagem no terminal e caso não tenha imagem exibe a mensagem de imagem não encontrada
            try {
		System.out.println("\u001b[1m Imagem: \u001b[m");
	    	URL urlImage = new URL(filme.get("image"));
	    	BufferedImage sampleImage = ImageIO.read(urlImage );
	    	AbstractImageRenderer converter = new RgbImageRenderer(25, 25);
	    	TerminalImage x = converter.render(sampleImage);
	    	System.out.println(x.getSequence());
            }catch (IIOException e) {
            	System.out.println("Imagem não encontrada");
	    }
            
            System.out.println("\u001b[1m Nota: \u001b[m"+filme.get("imDbRating")+"");
            System.out.println("");
        }
    }
}
