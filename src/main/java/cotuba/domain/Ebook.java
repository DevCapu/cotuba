package cotuba.domain;

import java.util.List;

public class Ebook {
    private FormatoEbook formato;
    private List<Capitulo> capitulos;

    public FormatoEbook getFormato() {
        return formato;
    }

    public void setFormato(FormatoEbook formato) {
        this.formato = formato;
    }

    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }
}
