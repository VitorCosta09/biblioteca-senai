import java.time.LocalDate;

public class Livros {
    String nome;
    String categoria;
    String classificacaoIndicativa;
    boolean disponivel = true;
    int numeroPaginas;
    LocalDate dataInicial;
    LocalDate dataFinal;

    @Override
    public String toString() {
        String informacoes = "\n" +
                "Título: " + nome + "\n" +
                "Categoria: " + categoria + "\n" +
                "Classificação: " + classificacaoIndicativa + "\n" +
                "Disponível: " + (disponivel ? "Sim" : "Não") + "\n" +
                "Número de páginas: " + numeroPaginas + "\n";

        if (!disponivel) {
            informacoes +=
                    "Data de início: " + dataInicial + "\n" +
                            "Data de fim: " + dataFinal + "\n";
        }

        return informacoes;
    }
}