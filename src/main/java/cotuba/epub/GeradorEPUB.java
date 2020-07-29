package cotuba.epub;

import cotuba.domain.Ebook;

import java.nio.file.Path;

public interface GeradorEPUB {

    static GeradorEPUB cria() {
        return new GeradorEPUBImpl();
    }

    public void geraEPUB(Ebook ebook, Path arquivoDeSaida);

}