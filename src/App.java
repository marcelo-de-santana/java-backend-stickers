import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        //fazer uma conexão HTTP  e buscar os top 250 filmes
        //String url = "https://imdb-api.com/en/API/Top250Movies/k_a0hev0lm";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        
        //String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        String url = "https://marcelo-linguagens-api.herokuapp.com/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        ClientHttp http = new ClientHttp();
        String json = http.searchData(url);

        //exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        Figurinhas figurinhas = new Figurinhas();
        
        for (int i =0; i < 5; i++){ 
        
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "src/saida/" + conteudo.getTitulo() + ".png";

            figurinhas.criar(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        };
    }
}