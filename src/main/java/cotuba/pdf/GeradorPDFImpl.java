package cotuba.pdf;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.property.AreaBreakType;
import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GeradorPDFImpl implements GeradorPDF {

    @Override
    public void geraPDF(Ebook ebook, Path arquivoDeSaida) {
        try(PdfWriter writer = new PdfWriter(Files.newOutputStream(arquivoDeSaida));
            PdfDocument pdf = new PdfDocument(writer);
            Document pdfDocument = new Document(pdf)) {

            for (Capitulo capitulo : ebook.getCapitulos()) {

                String html = capitulo.getConteudoHTML();

                List<IElement> convertToElements = HtmlConverter.convertToElements(html);
                for (IElement element : convertToElements) {
                    pdfDocument.add((IBlockElement) element);
                }

                if (ebook.getCapitulos().iterator().hasNext()) {
                    pdfDocument.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                }

            }

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao criar arquivo PDF: " + arquivoDeSaida.toAbsolutePath(), ex);
        }
    }
}
