package cotuba.epub;

import cotuba.domain.Ebook;

import java.nio.file.Path;

public interface GeradorEPUB {

    public void geraEPUB(Ebook ebook, Path arquivoDeSaida);

}
