import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        //fazer uma conexão HTTP  e buscar os top 250 filmes
        String url = "https://legis.senado.leg.br/dadosabertos/senador/lista/atual.json";
        URI address = URI.create(url); 
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request,BodyHandlers.ofString());
        String body = response.body();

        //extrair só os dados que interessam (título, poster, classificação)(expressões regulares)
        JsonParse parser = new JsonParse();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular os dados
        Figurinhas figurinhas = new Figurinhas();
        
        for (int i =0; i < 3; i++){ 
        
            Map<String,String> movie = listaDeFilmes.get(i);
        
            String urlImagem = movie.get("image").replaceAll("(@+)(.*).jpg$","$1.jpg");

            String titulo = movie.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "saida/" + titulo + ".png";
            

            figurinhas.criar(inputStream, nomeArquivo);

            System.out.println(movie.get(titulo));
        };
    }
}