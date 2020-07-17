package cotuba.epub;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GeradorEPUB {

    public static void geraEPUB(Ebook ebook, Path arquivoDeSaida) {

        Book epub = new Book();

        for (Capitulo capitulo : ebook.getCapitulos()) {

            String html = capitulo.getConteudoHTML();
            String tituloDoCapitulo = capitulo.getTitulo();
            epub.addSection("Capítulo", new Resource(html.getBytes(), MediatypeService.XHTML));

        }

        EpubWriter epubWriter = new EpubWriter();

        try {
            epubWriter.write(epub, Files.newOutputStream(arquivoDeSaida));
        } catch (IOException ex) {
            throw new RuntimeException("Erro ao criar arquivo EPUB: " + arquivoDeSaida.toAbsolutePath(), ex);
        }
    }
}
