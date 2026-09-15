import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;

public class Biblioteca {
    static String[] confirmar = {"Sim", "Não"};
    static ArrayList<Livros> livro = new ArrayList<>();
    static ArrayList<Usuarios> usuario = new ArrayList<>();

    public static void listarLivros() {
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

    public static void cadastrarLivro() {
        int opcaoClassificacao;
        int opcaoCategoria;
        int numerosPaginas;
        int confirmarCadastro;
        Livros novoLivro = new Livros();
        String[] categorias = {"Romance", "Aventura", "Fantasia", "Ficção científica", "Terror", "Mistério", "Biografia", "Tecnologia"};
        String[] classificacaoIndicativa = {"L", "10+", "12+", "14+", "16+", "18+", "Cancelar"};
        boolean cancelou = false;
        do {
            novoLivro.nome = JOptionPane.showInputDialog(null, "Digite o título do livro: ");
            if (novoLivro.nome == null) {
                cancelou = true;
                break;
            }
        } while (novoLivro.nome.isEmpty());
        if (cancelou) {
            return;
        }
        do {
            String entrada = JOptionPane.showInputDialog(null, "Digite o número correspondente à categoria do livro:\n" + "1 - Romance\n" + "2 - Aventura\n" + "3 - Fantasia\n" + "4 - Ficção científica\n" + "5 - Terror\n" + "6 - Mistério\n" + "7 - Biografia\n" + "8 - Tecnologia");
            if (entrada == null) {
                cancelou = true;
                break;
            }
            try {
                opcaoCategoria = Integer.parseInt(entrada);
                if (opcaoCategoria >= 1 && opcaoCategoria <= 8) {
                    novoLivro.categoria = categorias[opcaoCategoria - 1];
                }
            } catch (NumberFormatException e) {
                opcaoCategoria = -1;
            }
        } while (opcaoCategoria < 1 || opcaoCategoria > 8);
        if (cancelou) {
            return;
        }
        opcaoClassificacao = JOptionPane.showOptionDialog(null, "Escolha a classificação indicativa do livro.", "Sistema da Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, classificacaoIndicativa, classificacaoIndicativa[0]);
        if (opcaoClassificacao == 6 || opcaoClassificacao == -1) {
            return;
        }
        novoLivro.classificacaoIndicativa = classificacaoIndicativa[opcaoClassificacao];
        do {
            String entrada = JOptionPane.showInputDialog(null, "Digite o número de páginas: ");
            if (entrada == null) {
                cancelou = true;
                break;
            }
            try {
                numerosPaginas = Integer.parseInt(entrada);
                if (numerosPaginas >= 1) {
                    novoLivro.numeroPaginas = numerosPaginas;
                }
            } catch (NumberFormatException e) {
                numerosPaginas = -1;
            }
        } while (numerosPaginas < 1);
        if (cancelou) {
            return;
        }
        String informacoes = novoLivro.toString();
        confirmarCadastro = JOptionPane.showOptionDialog(null, "Informações do livro:\n" + informacoes + "\nDeseja completar o cadastro?", "Sistema de Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, confirmar, confirmar[0]);
        if (confirmarCadastro == 0) {
            JOptionPane.showMessageDialog(null, "Cadastro concluído com sucesso!");
            livro.add(novoLivro);
        } else {
            JOptionPane.showMessageDialog(null, "Cadastro não concluído!");
        }
    }

    public static Boolean cadastrarUsuario() {
        Usuarios novoUsuario = new Usuarios();
        novoUsuario.nome = JOptionPane.showInputDialog(null, "Digite o nome do usuário: ");
        if (novoUsuario == null) {
            return false;
        }
        novoUsuario.CPF = JOptionPane.showInputDialog(null, "Digite o CPF do usuário. (Apenas números): ");
        if(novoUsuario.CPF == null){
            return false;
        }
         else if (novoUsuario.CPF.length() == 11) {
            try {
                Long.parseLong(novoUsuario.CPF);
                usuario.add(novoUsuario);
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                return true;
            } catch (NumberFormatException e) {
            }
        }
         return false;
    }

    public static void registrarAluguel() {
        Boolean cancelou = true;
        int numeroUsuario = -1;
        int numeroLivro = -1;
        boolean usuarioEncontrado = false;
        boolean livroEncontrado = false;
        int confirmarLivro;
        String procurarLivro = JOptionPane.showInputDialog(null, "Digite o nome do livro: ");
        if (procurarLivro == null) {
            return;
        }
        for (int i = 0; i < livro.size(); i++) {
            if (livro.get(i).nome.toLowerCase(Locale.ROOT).equals(procurarLivro.toLowerCase())) {
                livroEncontrado = true;
                confirmarLivro = JOptionPane.showOptionDialog(null, "Informações do livro:\n" + livro.get(i).toString() + "\nEsse é o livro desejado?", "Sistema de Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, confirmar, confirmar[0]);
                if (confirmarLivro == 0) {
                    if (livro.get(i).disponivel == false) {
                        JOptionPane.showMessageDialog(null, "O livro está alugado no momento. Livro disponível novamente: " + livro.get(i).dataFinal + ".");
                        return;
                    }
                    do {
                        if(!cancelou){
                            break;
                        }
                        String cpf = JOptionPane.showInputDialog(null, "Informe o CPF do usuário que irá alugar este livro. (Apenas números): ");
                        if (cpf == null) {
                            return;
                        }
                        if (cpf.length() != 11) {
                            JOptionPane.showMessageDialog(null, "O CPF deve possuir 11 números.");
                            continue;
                        }
                        try {
                            Long.parseLong(cpf);
                            for (int j = 0; j < usuario.size(); j++) {
                                if (usuario.get(j).CPF.equals(cpf)) {
                                    usuarioEncontrado = true;
                                    numeroUsuario = j;
                                    numeroLivro = i;
                                    break;
                                }
                            }
                            if (usuarioEncontrado) {
                                if (usuario.get(numeroUsuario).livrosAlugados >= 3) {
                                    JOptionPane.showMessageDialog(null, "O usuário já possuí a quantidade máxima de livros alugados.");
                                    return;
                                }
                            } else {
                                int confirmarUsuario = JOptionPane.showOptionDialog(null, "Usuário não encontrado, deseja cadastrar um novo?", "Sistema de Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, confirmar, confirmar[0]);
                                if (confirmarUsuario == 0) {
                                    cancelou = cadastrarUsuario();
                                    if(!cancelou){
                                        break;
                                    }
                                } else {
                                    return;
                                }
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "O CPF deve conter apenas números.");
                            continue;
                        }
                    } while (!usuarioEncontrado);
                    if(cancelou) {
                        JOptionPane.showMessageDialog(null, "Livro alugado com sucesso.");
                        livro.get(numeroLivro).disponivel = false;
                        livro.get(numeroLivro).dataInicial = LocalDate.now();
                        livro.get(numeroLivro).dataFinal = livro.get(numeroLivro).dataInicial.plusDays(15);
                        usuario.get(numeroUsuario).livrosAlugados++;
                        usuario.get(numeroUsuario).livrosPossuidos.add(livro.get(numeroLivro));
                    }
                }
            }
        }
        if (!livroEncontrado) {
            JOptionPane.showMessageDialog(null, "Livro não encontrado.");
        }
    }

    public static void listarUsuarios() {
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

    public static void devolverLivro() {
        int numeroUsuario = -1;
        String cpf = JOptionPane.showInputDialog(null, "Informe o CPF do usuário que irá devolver o livro:");
        if (cpf == null) {
            return;
        }
        for (int i = 0; i < usuario.size(); i++) {
            if (usuario.get(i).CPF.equals(cpf)) {
                numeroUsuario = i;
                break;
            }
        }
        if (numeroUsuario == -1) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
            return;
        }
        if (usuario.get(numeroUsuario).livrosPossuidos.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Esse usuário não possui livros alugados."
            );
            return;
        }
        ArrayList<Livros> livrosPossuidos =
                usuario.get(numeroUsuario).livrosPossuidos;
        String[] opcoesLivros = new String[livrosPossuidos.size()];

        for (int i = 0; i < livrosPossuidos.size(); i++) {
            opcoesLivros[i] = livrosPossuidos.get(i).nome;
        }
        int opcaoLivro = JOptionPane.showOptionDialog(null, "Escolha o livro que deseja devolver:", "Devolução de livro", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesLivros, opcoesLivros[0]);
        if (opcaoLivro == -1) {
            return;
        }
        Livros livroDevolvido = livrosPossuidos.get(opcaoLivro);

        JOptionPane.showMessageDialog(null, "Livro selecionado:\n" + livroDevolvido.nome);
        if (LocalDate.now().isAfter(livroDevolvido.dataFinal)) {
            long diasAtrasados = ChronoUnit.DAYS.between(livroDevolvido.dataFinal, LocalDate.now());

            double multa = diasAtrasados * 2.0;
            JOptionPane.showMessageDialog(null, "Livro devolvido com atraso!\n" + "Dias atrasados: " + diasAtrasados + "\n" + "Multa: R$ " + multa);
            livroDevolvido.disponivel = true;
            livroDevolvido.dataInicial = null;
            livroDevolvido.dataFinal = null;

            livrosPossuidos.remove(opcaoLivro);
            usuario.get(numeroUsuario).livrosAlugados--;
        } else {
            JOptionPane.showMessageDialog(null, "Livro devolvido com sucesso.");
            livroDevolvido.disponivel = true;
            livroDevolvido.dataInicial = null;
            livroDevolvido.dataFinal = null;

            livrosPossuidos.remove(opcaoLivro);
            usuario.get(numeroUsuario).livrosAlugados--;
        }
    }
}