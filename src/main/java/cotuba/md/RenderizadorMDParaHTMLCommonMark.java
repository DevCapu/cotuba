package cotuba.md;

import cotuba.application.RenderizadorMDParaHTML;
import cotuba.domain.Capitulo;
import cotuba.tema.AplicadorTema;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RenderizadorMDParaHTMLCommonMark implements RenderizadorMDParaHTML {

    private final List<Capitulo> capitulos = new ArrayList<>();
    private Node document;
    private AplicadorTema aplicadorTema = new AplicadorTema();

    @Override
    public List<Capitulo> renderiza(Path diretorioDosMD) {

        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
        try (Stream<Path> arquivosMD = Files.list(diretorioDosMD)) {
            arquivosMD
                    .filter(matcher::matches)
                    .sorted()
                    .forEach(arquivoMD -> {

                        Capitulo capitulo = parse(arquivoMD);
                        aplicadorTema.aplica(capitulo);

                    });
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(), ex);
        }
    return capitulos;
   }

    private Capitulo parse(Path arquivoMD) {
        Capitulo capitulo = new Capitulo();
        Parser parser = Parser.builder().build();
        try {
            document = parser.parseReader(Files.newBufferedReader(arquivoMD));
            document.accept(new AbstractVisitor() {
                @Override
                public void visit(Heading heading) {
                    if (ehCapitulo(heading)) {
                        String tituloDoCapitulo = ((Text) heading.getFirstChild()).getLiteral();
                        capitulo.setTitulo(tituloDoCapitulo);
                    } else if (ehSecao(heading)) {
                        // coisas com a seção
                    } else if (ehTitulo(heading)) {
                        // coisas com o título
                    }
                }

            });
            renderizaCapitulo(capitulo);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fazer parse do arquivo " + arquivoMD, ex);
        }
        return capitulo;
    }

    private boolean ehTitulo(Heading heading) {
        return heading.getLevel() == 3;
    }

    private boolean ehSecao(Heading heading) {
        return heading.getLevel() == 2;
    }

    private boolean ehCapitulo(Heading heading) {
        return heading.getLevel() == 1;
    }

    private void renderizaCapitulo(Capitulo capitulo) {

            HtmlRenderer renderer = HtmlRenderer.builder().build();
            String html = renderer.render(document);

            capitulo.setConteudoHTML(html);
            capitulos.add(capitulo);
    }
}
