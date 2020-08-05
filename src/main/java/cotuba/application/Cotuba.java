package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

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

        GeradorEbook gerador = GeradorEbook.cria(formato);

        gerador.gera(ebook, arquivoDeSaida);
    }
}
