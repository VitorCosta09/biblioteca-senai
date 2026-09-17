import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Usuarios {
    private static ImageIcon logo = new ImageIcon(Biblioteca.class.getResource("/Imagens/livro.png"));
    private ArrayList<Livros> livrosPossuidos = new ArrayList<>();
    private String nome;
    private String CPF;
    private int livrosAlugados;

    public String toString() {
        String informacoes = "\n" +
                "Nome: " + nome + "\n" +
                "Livros alugados: " + livrosAlugados + "\n";
        if (!livrosPossuidos.isEmpty()) {
            informacoes += "\nLivros em posse:\n";

            for (Livros livro : livrosPossuidos) {
                informacoes += "- " + livro.getTitulo() + "\n";
            }
        } else {
            informacoes += "\nNenhum livro alugado.";
        }
        return informacoes;
    }

    public static void listarUsuarios(ArrayList<Usuarios> usuario) {
        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum usuário registrado!");
            return;
        }
        int pagina = 1;

        for (int i = 0; i < usuario.size(); i += 3) {
            int fim = Math.min(i + 3, usuario.size());
            StringBuilder lista = new StringBuilder();
            for (Usuarios l : usuario.subList(i, fim)) {
                lista.append(l).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, "Página " + pagina + "\n\n" + lista, "Lista de usuários", JOptionPane.INFORMATION_MESSAGE);
            pagina++;
        }

    }

    public Usuarios(String nome, String CPF) {
        this.nome = nome;
        this.CPF = CPF;
    }

    public String getCPF() {
        return CPF;
    }

    public int getLivrosAlugados() {
        return livrosAlugados;
    }

    public void setALugado(Livros livro) {
        this.livrosPossuidos.add(livro);
        this.livrosAlugados++;
    }

    public static void devolverLivro(){
        int numeroUsuario = -1;
        String cpf = (String) JOptionPane.showInputDialog(null, "Informe o CPF do usuário que irá devolver o livro:", "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo, null, null);
        if(cpf.length() >=11){
            cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
        }
        if (cpf == null) {
            return;
        }
        ArrayList<Usuarios> usuario = Biblioteca.getUsuario();
        for (int i = 0; i < usuario.size(); i++) {
            if (usuario.get(i).getCPF().equals(cpf)) {
                numeroUsuario = i;
                break;
            }
        }
        if (numeroUsuario == -1) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado.", "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo);
            return;
        }
        if (usuario.get(numeroUsuario).livrosPossuidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Esse usuário não possui livros alugados.", "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo);
            return;
        }
        ArrayList<Livros> livrosPossuidos =
                usuario.get(numeroUsuario).livrosPossuidos;
        String[] opcoesLivros = new String[livrosPossuidos.size()];

        for (int i = 0; i < livrosPossuidos.size(); i++) {
            opcoesLivros[i] = livrosPossuidos.get(i).getTitulo();
        }
        int opcaoLivro = JOptionPane.showOptionDialog(null, "Escolha o livro que deseja devolver:", "Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, logo, opcoesLivros, opcoesLivros[0]);
        if (opcaoLivro == -1) {
            return;
        }
        Livros livroDevolvido = livrosPossuidos.get(opcaoLivro);

        JOptionPane.showMessageDialog(null, "Livro selecionado:\n" + livroDevolvido.getTitulo(), "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo);
        if (LocalDate.now().isAfter(livroDevolvido.getDataFinal())) {
            long diasAtrasados = ChronoUnit.DAYS.between(livroDevolvido.getDataFinal(), LocalDate.now());

            double multa = diasAtrasados * 2.0;
            JOptionPane.showMessageDialog(null, "Livro devolvido com atraso!\n" + "Dias atrasados: " + diasAtrasados + "\n" + "Multa: R$ " + multa, "Blibioteca", JOptionPane.QUESTION_MESSAGE, logo);
            livroDevolvido.setDevolver();
            usuario.get(numeroUsuario).setDevolvido(livroDevolvido);
        } else {
            JOptionPane.showMessageDialog(null, "Livro devolvido com sucesso.", "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo);
            livroDevolvido.setDevolver();
            usuario.get(numeroUsuario).setDevolvido(livroDevolvido);
        }
    }
    public void setDevolvido(Livros livro) {
        this.livrosPossuidos.remove(livro);
        this.livrosAlugados--;
    }
}
