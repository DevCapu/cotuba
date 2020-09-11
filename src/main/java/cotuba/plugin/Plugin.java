package cotuba.plugin;

import cotuba.domain.Ebook;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public interface Plugin {

    ServiceLoader<Plugin> loader = ServiceLoader.load(Plugin.class);

    String cssDoTema();

    void aposGeracao(Ebook ebook);

    static List<String> listaDeTemas() {
        List<String> temas = new ArrayList<>();

        for (Plugin plugin : loader) {
            String css = plugin.cssDoTema();
            temas.add(css);
        }
        return temas;
    }

    static void gerou(Ebook ebook) {
        loader.forEach((plugin) -> plugin.aposGeracao(ebook));
    }
}
