package cotuba.pdf;

import cotuba.domain.Ebook;

import java.nio.file.Path;

public interface GeradorPDF {

    void geraPDF(Ebook ebook, Path arquivoDeSaida);

}
