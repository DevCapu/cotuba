package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.GeradorEPUB;
import cotuba.md.RenderizadorMDParaHTML;
import cotuba.pdf.GeradorPDF;

import java.nio.file.Path;
import java.util.List;

public class Cotuba {

    public static void executa(String formato, Path diretorioDosMD, Path arquivoDeSaida) {
        RenderizadorMDParaHTML renderizador = new RenderizadorMDParaHTML();
        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

        Ebook ebook = new Ebook();
		ebook.setFormato(formato);
		ebook.setCapitulos(capitulos);

        if ("pdf".equals(formato)) {
            GeradorPDF.geraPDF(ebook, arquivoDeSaida);
        } else if ("epub".equals(formato)) {
            GeradorEPUB.geraEPUB(ebook, arquivoDeSaida);
        } else {
            throw new RuntimeException("Formato do ebook inválido: " + formato);
        }
    }
}
