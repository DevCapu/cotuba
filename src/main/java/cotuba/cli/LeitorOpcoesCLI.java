package cotuba.cli;

import cotuba.application.ParametrosCotuba;
import cotuba.domain.FormatoEbook;
import org.apache.commons.cli.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LeitorOpcoesCLI implements ParametrosCotuba {

    private Path diretorioDosMD;
    private FormatoEbook formato;
    private Path arquivoDeSaida;
    private boolean modoVerboso = false;

    public LeitorOpcoesCLI(String [] args) {
        Options options = criaPossiveisOpcoes();

        CommandLineParser cmdParser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = cmdParser.parse(options, args);
        } catch (ParseException e) {
            throw new RuntimeException("Opção inválida", e);
        }

        setNomeDoDiretorioDosMD(cmd.getOptionValue("dir"));
        setFormato(cmd.getOptionValue("format"));
        setArquivoDeSaida(cmd.getOptionValue("output"));
        setModoVerboso(cmd.hasOption("verbose"));
    }

    private Options criaPossiveisOpcoes() {
        Options options = new Options();

        Option opcaoDeDiretorioDosMD = new Option("d", "dir", true,
                "Diretório que contem os arquivos md. Default: diretório atual.");
        options.addOption(opcaoDeDiretorioDosMD);

        Option opcaoDeFormatoDoEbook = new Option("f", "format", true,
                "Formato de saída do ebook. Pode ser: pdf ou epub. Default: pdf");
        options.addOption(opcaoDeFormatoDoEbook);

        Option opcaoDeArquivoDeSaida = new Option("o", "output", true,
                "Arquivo de saída do ebook. Default: book.{formato}.");
        options.addOption(opcaoDeArquivoDeSaida);

        Option opcaoModoVerboso = new Option("v", "verbose", false,
                "Habilita modo verboso.");
        options.addOption(opcaoModoVerboso);

        return options;
    }

    private void setNomeDoDiretorioDosMD(String nomeDoDiretorioDosMD) {
        if (nomeDoDiretorioDosMD != null) {
            diretorioDosMD = Paths.get(nomeDoDiretorioDosMD);
            if (!Files.isDirectory(diretorioDosMD)) {
                throw new RuntimeException(nomeDoDiretorioDosMD + " não é um diretório.");
            }
        } else {
            Path diretorioAtual = Paths.get("");
            diretorioDosMD = diretorioAtual;
        }
    }

    private void setFormato(String nomeDoFormatoDoEbook) {
        if (nomeDoFormatoDoEbook != null) {
            formato = FormatoEbook.valueOf(nomeDoFormatoDoEbook.toUpperCase());
        } else {
            formato = FormatoEbook.PDF;
        }
    }

    private void setArquivoDeSaida(String nomeDoArquivoDeSaidaDoEbook) {
        if (nomeDoArquivoDeSaidaDoEbook != null) {
            arquivoDeSaida = Paths.get(nomeDoArquivoDeSaidaDoEbook);
            if (Files.exists(arquivoDeSaida) && Files.isDirectory(arquivoDeSaida)) {
                throw new RuntimeException(nomeDoArquivoDeSaidaDoEbook + " é um diretório.");
            }
        } else {
            arquivoDeSaida = Paths.get("book." + formato.name().toLowerCase());
        }
    }

    private void setModoVerboso(boolean isModoVerboso) {
        modoVerboso = isModoVerboso;
    }

    @Override
    public Path getDiretorioDosMD() {
        return diretorioDosMD;
    }

    @Override
    public FormatoEbook getFormato() {
        return formato;
    }

    @Override
    public Path getArquivoDeSaida() {
        return arquivoDeSaida;
    }

    public boolean isModoVerboso() {
        return modoVerboso;
    }
}
