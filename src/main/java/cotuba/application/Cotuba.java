package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.GeradorEPUBEpublib;
import cotuba.pdf.GeradorPDFIText;

import java.nio.file.Path;
import java.util.List;

public class Cotuba {

    public static void executa(ParametrosCotuba parametros) {
        String formato = parametros.getFormato();
        Path diretorioDosMD = parametros.getDiretorioDosMD();
        Path arquivoDeSaida = parametros.getArquivoDeSaida();

        RenderizadorMDParaHTML renderizador = RenderizadorMDParaHTML.cria();
        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

        Ebook ebook = new Ebook();
		ebook.setFormato(formato);
		ebook.setCapitulos(capitulos);

        GeradorEbook gerador;

        if ("pdf".equals(formato)) {

            gerador = new GeradorPDFIText();

        } else if ("epub".equals(formato)) {

            gerador = new GeradorEPUBEpublib();

        } else {
            throw new RuntimeException("Formato do ebook inválido: " + formato);
        }
        gerador.gera(ebook, arquivoDeSaida);
    }
}
