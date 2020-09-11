package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;
import cotuba.plugin.Plugin;

import java.nio.file.Path;
import java.util.List;
import java.util.ServiceLoader;

public class Cotuba {

    public static void executa(ParametrosCotuba parametros) {
        FormatoEbook formato = parametros.getFormato();
        Path diretorioDosMD = parametros.getDiretorioDosMD();
        Path arquivoDeSaida = parametros.getArquivoDeSaida();

        RenderizadorMDParaHTML renderizador = RenderizadorMDParaHTML.cria();
        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

        Ebook ebook = new Ebook();
		ebook.setFormato(formato);
		ebook.setCapitulos(capitulos);

        GeradorEbook gerador = GeradorEbook.cria(formato);

        gerador.gera(ebook, arquivoDeSaida);

        Plugin.gerou(ebook);
    }
}
