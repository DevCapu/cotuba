package cotuba.application;

import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;

import java.nio.file.Path;

public interface GeradorEbook {

    void gera(Ebook ebook, Path arquivoDeSaida);

    static GeradorEbook cria(FormatoEbook formato) {

        GeradorEbook gerador = formato.getGerador();

        return gerador;
    }
}
