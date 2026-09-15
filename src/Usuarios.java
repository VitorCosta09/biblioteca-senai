import java.util.ArrayList;

public class Usuarios {
    ArrayList<Livros> livrosPossuidos = new ArrayList<>();
    String nome;
    String CPF;
    int livrosAlugados;
    @Override
    public String toString() {
        String informacoes = "\n" +
                "Nome: " + nome + "\n" +
                "CPF: " + CPF + "\n" +
                "Livros alugados: " + livrosAlugados + "\n";
        if (!livrosPossuidos.isEmpty()) {
            informacoes += "\nLivros em posse:\n";

            for (Livros livro : livrosPossuidos) {
                informacoes += "- " + livro.nome + "\n";
            }
        } else {
            informacoes += "\nNenhum livro alugado.";
        }
        return informacoes;
    }
}