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
        limparTela();
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
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(scanner.nextLine());

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
        System.out.println("Digite o nome do filme:");
        String nome = scanner.next();
        System.out.println("Digite a data de lançamento:");
        String anoLancamento = scanner.next();
        System.out.println("Digite a duração do filme:");
        int duracao = scanner.nextInt();
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
        System.out.println("Oque deseja ver ? 1 -> Filme 2 -> serie");
        int opcAssistir = scanner.nextInt();
        switch (opcAssistir) {
            case 1:
                plataforma.getFilmes().forEach(x -> System.out.println(x.toString()));
                break;
            case 2:
                plataforma.getSeries().forEach(x -> System.out.println(x.toString()));
                break;
            default:
                plataforma.getFilmes().forEach(x -> System.out.println(x.toString()));
                break;
        }

        System.out.println("Digite o id que deseja assistir.");
        Audiovisual ver = plataforma.buscarAudiovisual(scanner.nextInt());
        System.out.println("Deseja avaliar? 0 -> Não | 1 -> Sim.");
        int opc = scanner.nextInt();
        if (opc == 1) {
            String comentario = "";
            System.out.println("Digite uma nota de 1 a 5.");
            Double nota = scanner.nextDouble();
            if (clientAutenticado.getEspecialista()) {
                System.out.println("Digite um comentario, caso não queira apenas aperte enter.");
                comentario = scanner.next();
                clientAutenticado.adicionarAvaliacao(ver, nota, comentario);
            } else {
                clientAutenticado.adicionarAvaliacao(ver, nota);
            }

            System.out.println("Avaliação cadastrada");
        }
        System.out.println("Obrigado.");
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
        System.out.println("Digite o gênero:");
        String genero = scanner.nextLine();
        Filtro<Audiovisual> f = new Filtro<>();

        Predicate<FiltroPersonalizado<Audiovisual>> filtrador = filtro -> filtro.getElemento().getIdioma()
                .equals(filtro.getBusca());

        List<Audiovisual> palavrasFiltradas = f.filtrar(plataforma.getListaAudioVisual(), filtrador, genero);

        palavrasFiltradas.forEach(audiovisual -> System.out.println(audiovisual.getNome()));
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
                    limparTela();
                    System.out.println("Catálogo de filmes:");
                    plataforma.getFilmes().forEach(x -> System.out.println(x.toString()));
                    break;
                case 2:
                    limparTela();
                    System.out.println("Catálogo de séries:");
                    plataforma.getSeries().forEach(x -> System.out.println(x.toString()));
                    break;
                case 3:
                    limparTela();
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
                    limparTela();
                    System.out.println("Perfil:");
                    break;
                case 5:
                    limparTela();
                    adicionarFilme();
                    break;

                case 6:
                    limparTela();
                    adicionarSerie();
                    break;
                case 7:
                    limparTela();
                    assistir();
                    break;
                case 8:
                    limparTela();
                    filtraMidiaPorGenero();
                    break;
                case 9:
                    limparTela();
                    filtraMidiaPorIdioma();
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
