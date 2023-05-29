import src.*;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class App {
    static Scanner scanner = new Scanner(System.in);
    public static Cliente clientAutenticado;
    public static PlataformaStreaming plataforma = new PlataformaStreaming();

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.println("Enter para continuar.");
        scanner.nextLine();
    }

    public static int menu() {
        System.out.println("Bem-vindo ao StreamingApp!");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Ver catálogo de filmes");
        System.out.println("2 - Ver catálogo de séries");
        System.out.println("3 - Buscar conteúdo:");
        System.out.println("4 - Ver perfil");
        System.out.println("5 - Adicionar Filme");
        System.out.println("6 - Adicionar Serie");
        System.out.println("7 - Assistir");
        System.out.println("8 - Filtrar midia por genero");
        System.out.println("9 - Filtrar midia por Idioma");
        System.out.println("10 - Cadastrar um novo usuário");
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(scanner.nextLine());
        limparTela();
        return opcao;
    }

    public static void buscarSeries() {
        System.out.println("Buscar Séries:");
        System.out.println("Digite o nome do titulo:");
        String audiovisual = scanner.next();
        System.out.println(plataforma.buscarAudiovisual(audiovisual));
    }

    public static void adicionarFilme() {
        System.out.println("Adicionar Filme...");
        System.out.println("Digite o ID do filme");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o nome do filme:");
        String nome = scanner.nextLine();
        System.out.println("Digite a data de lançamento:");
        String anoLancamento = scanner.nextLine();
        System.out.println("Digite a duração do filme:");
        int duracao = scanner.nextInt();
        scanner.nextLine();
        Filme filme = new Filme(id, nome, anoLancamento, duracao);
        plataforma.adicionarFilme(filme);
        plataforma.salvarFilme();
    }

    public static void adicionarSerie() {
        System.out.println("Adicionar src.Serie...");
        System.out.println("Digite o ID da serie");
        int idSerie = scanner.nextInt();
        System.out.println("Digite o nome da serie:");
        String nomeSerie = scanner.next();
        System.out.println("Digite a data de lançamento:");
        String anoLancamentoSerie = scanner.next();
        Serie serie = new Serie(idSerie, nomeSerie, anoLancamentoSerie);
        plataforma.adicionarSerie(serie);
        plataforma.salvarSerie();
    }

    public static void assistir() {
        // System.out.println("Oque deseja ver ? 1 -> Filme 2 -> serie");
        // int opcAssistir = scanner.nextInt();
        // switch (opcAssistir) {
        // case 1:
        // plataforma.getFilmes().values().forEach(x ->
        // System.out.println(x.toString()));
        // break;
        // case 2:
        // plataforma.getSeries().values().forEach(x ->
        // System.out.println(x.toString()));
        // break;
        // default:
        // plataforma.getFilmes().values().forEach(x ->
        // System.out.println(x.toString()));
        // break;
        // }
        // plataforma.getHashMapAudioVisual().values().forEach(x ->
        // System.out.println(x.toString())); C
        System.out.println("Digite o id que deseja assistir.");
        Audiovisual ver = plataforma.buscarAudiovisual(scanner.nextInt());
        System.out.println("Deseja avaliar? 0 -> Não | 1 -> Sim.");
        int opc = scanner.nextInt();
        if (opc == 1) {

            String comentario = "";
            System.out.println("Digite uma nota de 1 a 5.");
            Double nota = scanner.nextDouble();
            scanner.nextLine();
            System.out.println(
                    "Digite um comentario (apenas para clientes especialistas), caso não queira apenas aperte enter.");
            comentario = scanner.nextLine();

            try {
                clientAutenticado.adicionarAvaliacao(ver, nota, comentario);
                System.out.println("Avaliação cadastrada");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        System.out.println("Obrigado.");
        pausa();
    }

    public static void filtraMidiaPorGenero() {
        System.out.println("Digite o gênero:");
        String genero = scanner.nextLine();
        Filtro<Audiovisual> f = new Filtro<>();

        Predicate<FiltroPersonalizado<Audiovisual>> filtrador = filtro -> filtro.getElemento().getGenero()
                .equals(filtro.getBusca());

        List<Audiovisual> palavrasFiltradas = f.filtrar(plataforma.getListaAudioVisual(), filtrador, genero);

        palavrasFiltradas.forEach(audiovisual -> System.out.println(audiovisual.getNome()));
    }

    public static void filtraMidiaPorIdioma() {
        System.out.println("Digite o idioma:");
        String idioma = scanner.nextLine();
        Filtro<Audiovisual> f = new Filtro<>();

        Predicate<FiltroPersonalizado<Audiovisual>> filtrador = filtro -> filtro.getElemento().getIdioma()
                .equals(filtro.getBusca());

        List<Audiovisual> palavrasFiltradas = f.filtrar(plataforma.getListaAudioVisual(), filtrador, idioma);

        palavrasFiltradas.forEach(audiovisual -> System.out.println(audiovisual.getNome()));
    }

    public static void verPerfil() {

        if (clientAutenticado != null) {
            System.out.println("Bem vindo " + clientAutenticado.getNomeUsuario() + "! Seu login é "
                    + clientAutenticado.getLogin() + " e sua senha é " + clientAutenticado.getSenha() + ".");
        } else {
            System.out.println("Você não está logado.");
            System.out.println("Digite seu login:");
            String login = scanner.nextLine();
            System.out.println("Digite sua senha:");
            String senha = scanner.nextLine();
            clientAutenticado = plataforma.login(login, senha);

            if (clientAutenticado != null) {
                System.out.println("Bem vindo " + clientAutenticado.getNomeUsuario() + "! Seu login é "
                        + clientAutenticado.getLogin() + " e sua senha é " + clientAutenticado.getSenha() + ".");
            } else {
                System.out.println("Login ou senha incorretos. Tente novamente");
            }
        }
    }

    public static void cadastrarNovoUsuario() {
        System.out.println("Digite seu nome de exibição:");
        String nomeUsuarioCadastro = scanner.nextLine();
        System.out.println("Digite seu login:");
        String loginCadastro = scanner.nextLine();
        System.out.println("Digite sua senha:");
        String senhaCadastro = scanner.nextLine();

        plataforma.cadastro(nomeUsuarioCadastro, loginCadastro, senhaCadastro);

        System.out.println("você deseja logar como este cliente agora? 1 para Sim | 0 para Não");
        int opc = scanner.nextInt();

        if (opc == 1) {
            clientAutenticado = plataforma.login(loginCadastro, senhaCadastro);
            if (clientAutenticado != null)
                System.out.println("Usuário " + nomeUsuarioCadastro
                        + " cadastrado com sucesso! Você está logado como este usuário");
        } else {
            System.out.println("Usuário " + nomeUsuarioCadastro + " cadastrado com sucesso!");
        }

        pausa();

    }

    public static void main(String[] args) {
        int opcao = -1;
        plataforma.carregarDados();
        do {
            opcao = menu();

            switch (opcao) {
                case 0:
                    System.out.println("Saindo...");
                    break;
                case 1:

                    System.out.println("Catálogo de filmes:");
                    plataforma.getFilmes().values().forEach(x -> System.out.println(x.toString()));
                    break;
                case 2:
                    System.out.println("Catálogo de séries:");
                    plataforma.getSeries().values().forEach(x -> System.out.println(x.toString()));
                    break;
                case 3:
                    System.out.println("Buscar conteúdo:");
                    System.out.print("Digite o nome do título: ");
                    String audiovisual = scanner.nextLine();
                    Audiovisual audiovisualEncontrado = plataforma.buscarAudiovisual(audiovisual);
                    if (audiovisualEncontrado != null) {
                        System.out.println(audiovisualEncontrado.toString());
                    } else {
                        System.out.println("Nenhum audiovisual encontrado com o nome fornecido.");
                    }
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("Perfil:");
                    verPerfil();
                    break;
                case 5:
                    adicionarFilme();
                    break;

                case 6:
                    adicionarSerie();
                    break;
                case 7:
                    assistir();
                    break;
                case 8:
                    filtraMidiaPorGenero();
                    break;
                case 9:
                    filtraMidiaPorIdioma();
                    break;
                case 10:
                    System.out.println("Criando um novo usuário:");
                    cadastrarNovoUsuario();
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }

            System.out.println();
            pausa();
        } while (opcao != 0);

        scanner.close();
    }

}
