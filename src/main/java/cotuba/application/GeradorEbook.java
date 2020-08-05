package cotuba.application;

import cotuba.domain.Ebook;

import java.nio.file.Path;

public interface GeradorEbook {

    void gera(Ebook ebook, Path arquivoDeSaida);

}
