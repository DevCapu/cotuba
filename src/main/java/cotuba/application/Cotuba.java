package cotuba.application;

import cotuba.cli.LeitorOpcoesCLI;
import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

import java.nio.file.Path;
import java.util.List;

public class Cotuba {

    public static void executa(LeitorOpcoesCLI parametros) {
        String formato = parametros.getFormato();
        Path diretorioDosMD = parametros.getDiretorioDosMD();
        Path arquivoDeSaida = parametros.getArquivoDeSaida();

        RenderizadorMDParaHTML renderizador = RenderizadorMDParaHTML.cria();
        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

        Ebook ebook = new Ebook();
		ebook.setFormato(formato);
		ebook.setCapitulos(capitulos);

        if ("pdf".equals(formato)) {
            GeradorPDF geradorPDF = GeradorPDF.cria();
            geradorPDF.geraPDF(ebook, arquivoDeSaida);

        } else if ("epub".equals(formato)) {
            GeradorEPUB geradorEPUB = GeradorEPUB.cria();
            geradorEPUB.geraEPUB(ebook, arquivoDeSaida);

        } else {
            throw new RuntimeException("Formato do ebook inválido: " + formato);
        }
    }
}
