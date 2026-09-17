import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Livros {
    private String titulo;
    private String categoria;
    private String classificacaoIndicativa;
    private boolean disponivel = true;
    private int numeroPaginas;
    private LocalDate dataInicial;
    private LocalDate dataFinal;

    public Livros(String titulo, String categoria, String classificacaoIndicativa, int numeroPaginas) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.numeroPaginas = numeroPaginas;
    }

    public String toString() {
        String informacoes = "\n" +
                "Título: " + titulo + "\n" +
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

    public static void listaLivros(ArrayList<Livros> livro) {
        if (livro.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum livro registrado!");
            return;
        }
        int pagina = 1;
        for (int i = 0; i < livro.size(); i += 3) {
            int fim = Math.min(i + 3, livro.size());
            StringBuilder lista = new StringBuilder();
            for (Livros l : livro.subList(i, fim)) {
                lista.append(l).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, "Página " + pagina + "\n\n" + lista, "Lista de livros", JOptionPane.INFORMATION_MESSAGE);
            pagina++;
        }

    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setAlugar(){
        this.dataInicial = LocalDate.now();
        this.dataFinal = dataInicial.plusDays(15);
        this.disponivel = false;
    }
    public void setDevolver(){
        this.dataInicial = null;
        this.dataFinal = null;
        this.disponivel = true;
    }
}
