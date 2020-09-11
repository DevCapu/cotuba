package cotuba.tema;

import cotuba.domain.Capitulo;
import cotuba.plugin.Plugin;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;


public class AplicadorTema {
    public void aplica(Capitulo capitulo) {
        String html = capitulo.getConteudoHTML();

        Document document = Jsoup.parse(html);

        List<String> listaDeTemas = Plugin.listaDeTemas();
        listaDeTemas.forEach((css) -> document.select("head").append("<style> " + css + " </style>"));

        capitulo.setConteudoHTML(document.html());
    }
}
