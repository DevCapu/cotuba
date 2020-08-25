package cotuba.tema;

import cotuba.domain.Capitulo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class AplicadorTema {
    public void aplica(Capitulo capitulo) {
        String html = capitulo.getConteudoHTML();

        Document document = Jsoup.parse(html);

        String css = "h1 { border-bottom: 1px dashed black; }";

        document.select("head").append("<style> " + css + " </style>");

        capitulo.setConteudoHTML(document.html());
    }
}
