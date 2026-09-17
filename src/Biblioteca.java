import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;

public class Biblioteca {
    private static ImageIcon logo = new ImageIcon(Biblioteca.class.getResource("/Imagens/livro.png"));
    private static String[] confirmar = {"Sim", "Não"};
    private static ArrayList<Livros> livro = new ArrayList<>();
    private static ArrayList<Usuarios> usuario = new ArrayList<>();

    public static void cadastrarLivro() {
        boolean cancelou = false;
        String titulo;
        int opcaoCategoria;
        String categoria = "";
        int numeroPaginas = 0;
        String entrada;
        String[] categorias = {"Romance", "Aventura", "Fantasia", "Ficção científica", "Terror", "Mistério", "Biografia", "Tecnologia"};
        String[] classificacaoIndicativa = {"L", "10+", "12+", "14+", "16+", "18+", "Cancelar"};
        int opcaoClassificacao;
        String classificacao;
        do {
            titulo = (String) JOptionPane.showInputDialog(null, "Digite o título do livro: ", "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo, null, null);
            if (titulo == null) {
                cancelou = true;
                break;
            }
        } while (titulo.isEmpty());
        if (cancelou) {
            return;
        }
        do {
            entrada = (String) JOptionPane.showInputDialog(null, "Digite o número correspondente à categoria do livro:\n" + "1 - Romance\n" + "2 - Aventura\n" + "3 - Fantasia\n" + "4 - Ficção científica\n" + "5 - Terror\n" + "6 - Mistério\n" + "7 - Biografia\n" + "8 - Tecnologia", "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo, null, null);
            if (entrada == null) {
                cancelou = true;
                break;
            }
            try {
                opcaoCategoria = Integer.parseInt(entrada);
                if (opcaoCategoria >= 1 && opcaoCategoria <= 8) {
                    categoria = categorias[opcaoCategoria - 1];
                }
            } catch (NumberFormatException e) {
                opcaoCategoria = -1;
            }
        } while (opcaoCategoria < 1 || opcaoCategoria > 8);
        if (cancelou) {
            return;
        }
        opcaoClassificacao = JOptionPane.showOptionDialog(null, "Escolha a classificação indicativa do livro.", "Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, logo, classificacaoIndicativa, classificacaoIndicativa[0]);
        if (opcaoClassificacao == 6 || opcaoClassificacao == -1) {
            return;
        }
        classificacao = classificacaoIndicativa[opcaoClassificacao];
        do {
            entrada = (String) JOptionPane.showInputDialog(null, "Digite o número de páginas: ", "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo, null, null);
            if (entrada == null) {
                cancelou = true;
                break;
            }
            try {
                numeroPaginas = Integer.parseInt(entrada);
                if (numeroPaginas >= 1) {
                    break;
                }
            } catch (NumberFormatException e) {
                numeroPaginas = -1;
            }
        } while (numeroPaginas < 1);
        if (cancelou) {
            return;
        }
        Livros novoLivro = new Livros(titulo, categoria, classificacao, numeroPaginas);
        int confirmarCadastro = JOptionPane.showOptionDialog(null, "Informações do livro:\n" + novoLivro.toString() + "\nDeseja completar o cadastro?", "Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, logo, confirmar, confirmar[0]);
        if (confirmarCadastro == 0) {
            JOptionPane.showMessageDialog(null, "Cadastro concluído com sucesso!");
            livro.add(novoLivro);
            return;
        }
        JOptionPane.showMessageDialog(null, "Cadastro não concluído!");
    }

    public static Boolean cadastrarUsuario() {
        String nome = (String) JOptionPane.showInputDialog(null, "Digite o nome do usuário: ", "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo, null, null);
        if (nome == null) {
            return false;
        }
        String cpf = (String) JOptionPane.showInputDialog(null, "Digite o CPF do usuário. (Apenas números): ", "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo, null, null);
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() == 11) {
            cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
            Usuarios novoUsuario = new Usuarios(nome, cpf);
            usuario.add(novoUsuario);
            return true;
        }
        return false;
    }

    public static ArrayList<Livros> getLivro() {
        return livro;
    }

    public static ArrayList<Usuarios> getUsuario() {
        return usuario;
    }

    public static void registrarAluguel() {
        int numeroUsuario = -1;
        int numeroLivro = -1;
        boolean usuarioEncontrado = false;
        boolean livroEncontrado = false;
        int confirmarLivro;
        String procurarLivro = (String) JOptionPane.showInputDialog(null, "Digite o nome do livro: ", "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo, null, null);
        if (procurarLivro == null) {
            return;
        }
        for (int i = 0; i < livro.size(); i++) {
            procurarLivro = procurarLivro.toLowerCase(Locale.ROOT).replaceAll(" ", "");
            String titulo = livro.get(i).getTitulo().toLowerCase(Locale.ROOT).replaceAll(" ", "");
            if (titulo.equals(procurarLivro)) {
                livroEncontrado = true;
                confirmarLivro = JOptionPane.showOptionDialog(null, "Informações do livro:\n" + livro.get(i).toString() + "\nEsse é o livro desejado?", "Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, logo, confirmar, confirmar[0]);
                if (confirmarLivro == 0) {
                    if (livro.get(i).isDisponivel() == false) {
                        JOptionPane.showMessageDialog(null, "O livro está alugado no momento. Livro disponível novamente: " + livro.get(i).getDataFinal() + ".", "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo);
                        return;
                    }
                    do {
                        String cpf = (String) JOptionPane.showInputDialog(null, "Informe o CPF do usuário que irá alugar este livro. (Apenas números): ", "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo, null, null);
                        if (cpf == null) {
                            return;
                        }
                        if (cpf.length() != 11) {
                            JOptionPane.showMessageDialog(null, "O CPF deve possuir 11 números.", "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo);
                            continue;
                        }
                        try {
                            Long.parseLong(cpf);
                            cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
                            for (int j = 0; j < usuario.size(); j++) {
                                if (usuario.get(j).getCPF().equals(cpf)) {
                                    usuarioEncontrado = true;
                                    numeroUsuario = j;
                                    numeroLivro = i;
                                    break;
                                }
                            }
                            if (usuarioEncontrado) {
                                if (usuario.get(numeroUsuario).getLivrosAlugados() >= 3) {
                                    JOptionPane.showMessageDialog(null, "O usuário já possuí a quantidade máxima de livros alugados.", "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo);
                                    return;
                                }
                            } else {
                                int confirmarUsuario = JOptionPane.showOptionDialog(null, "Usuário não encontrado, deseja criar um novo?", "Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, logo, confirmar, confirmar[0]);
                                if (confirmarUsuario == 0) {
                                    if (cadastrarUsuario()) {
                                        JOptionPane.showMessageDialog(null, "Usuário cadastrado! Agora informe o CPF novamente.", "Biblioteca", JOptionPane.INFORMATION_MESSAGE, logo);
                                        continue;
                                    }

                                    break;
                                }
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "O CPF deve conter apenas números.", "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo);
                            continue;
                        }
                    } while (!usuarioEncontrado);
                    if (usuarioEncontrado) {
                        JOptionPane.showMessageDialog(null, "Livro alugado com sucesso.", "Biblioteca", JOptionPane.QUESTION_MESSAGE, logo);
                        livro.get(numeroLivro).setAlugar();
                        usuario.get(numeroUsuario).setALugado(livro.get(numeroLivro));
                    }
                }
            }
        }
        if (!livroEncontrado) {
            JOptionPane.showMessageDialog(null, "Livro não encontrado.");
        }

    }

}