import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        //fazer uma conexão HTTP  e buscar os top 250 filmes
        String url = "https://alura-filmes.herokuapp.com/conteudos";
        URI address = URI.create(url); 
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request,BodyHandlers.ofString());
        String body = response.body();
        //extrair só os dados que interessam (título, poster, classificação)(expressões regulares)
        JsonParse parser = new JsonParse();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        //exibir e manipular os dados 
        for (Map<String,String> movie : listaDeFilmes) {
           System.out.println(movie.get("title")); 
           System.out.println(movie.get("image")); 
           System.out.println(movie.get("imDbRating")); 
        };
    }
}