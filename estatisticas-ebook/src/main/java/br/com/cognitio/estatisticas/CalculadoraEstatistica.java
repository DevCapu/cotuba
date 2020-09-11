package br.com.cognitio.estatisticas;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.Plugin;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CalculadoraEstatistica implements Plugin {
    public String cssDoTema() {
        return null;
    }

    public void aposGeracao(Ebook ebook) {
        for (Capitulo capitulo : ebook.getCapitulos()) {

            String html = capitulo.getConteudoHTML();

            Document doc = Jsoup.parse(html);

            String textoDoCapitulo = doc.body().text();

            String[] palavras = textoDoCapitulo.split("\\s+");

            for (String palavra : palavras) {
                System.out.println(palavra);
            }

        }
    }
}
