import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class Figurinhas {
    
    public void criar(InputStream inputStream, String nomeArquivo) throws IOException{

        //leitura da imagem
        //InputStream inputStream = new URL("https://").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //configurar a fonte
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        //escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 100, novaAltura - 100);

        //escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", (ImageOutputStream) new FileAlreadyExistsException(nomeArquivo));

    }
 
}
