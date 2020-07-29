package cotuba.md;

import cotuba.domain.Capitulo;

import java.nio.file.Path;
import java.util.List;

public interface RenderizadorMDParaHTML {

    static RenderizadorMDParaHTML cria() {
        return new RenderizadorMDParaHTMLCommonMark();

    }

    List<Capitulo> renderiza(Path diretorioDosMD);

}
