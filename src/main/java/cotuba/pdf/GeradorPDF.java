package cotuba.pdf;

import cotuba.domain.Ebook;

import java.nio.file.Path;

public interface GeradorPDF {

    static GeradorPDF cria() {
        return new GeradorPDFImpl();
    }

    void geraPDF(Ebook ebook, Path arquivoDeSaida);

}
