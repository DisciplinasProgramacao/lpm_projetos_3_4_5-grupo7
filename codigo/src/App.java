import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlataformaStreaming ps = new PlataformaStreaming();
        DadosService meusDados = new DadosService(ps);
        boolean sair = false;

        while (!sair) {
            System.out.println("Bem-vindo ao StreamingApp!");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Ver catálogo de filmes");
            System.out.println("2 - Ver catálogo de séries");
            System.out.println("3 - Buscar Séries:");
            System.out.println("4 - Ver perfil");
            System.out.println("5 - Adicionar Filme");
            System.out.println("6 - Adicionar Serie");
            System.out.println("7 - Assistir");
            System.out.println("8 - Sair");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Catálogo de filmes:");
                    meusDados.carregarFilmes();
                    break;
                case 2:
                    System.out.println("Catálogo de séries:");
                    meusDados.carregarSeries();
                    break;
                case 3:
                    System.out.println("Buscar Séries:");
                    System.out.println("Digite o nome do titulo:");
                    String audiovisual = scanner.next();
                    System.out.println(ps.buscarAudiovisual(audiovisual));
                    break;
                case 4:
                    System.out.println("Perfil:");
                    meusDados.carregarClientes();
                    break;
                case 5:
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
                    ps.adicionarFilme(filme);
                    ps.salvarFilme();
                    break;

                case 6:
                    System.out.println("Adicionar Serie...");
                    System.out.println("Digite o ID da serie");
                    int idSerie = scanner.nextInt();
                    System.out.println("Digite o nome da serie:");
                    String nomeSerie = scanner.next();
                    System.out.println("Digite a data de lançamento:");
                    String anoLancamentoSerie = scanner.next();
                    Serie serie = new Serie(idSerie, nomeSerie, anoLancamentoSerie);
                    ps.adicionarSerie(serie);
                    ps.salvarSerie();
                    break;
                case 7:
                    System.out.println("Oque deseja ver ? 1 -> Filme 2 -> serie");
                    int opcAssistir = scanner.nextInt();
                    switch (opcAssistir) {
                        case 1:
                            meusDados.carregarFilmes();
                            break;
                        case 2:
                            meusDados.carregarSeries();
                            break;
                        default:
                            meusDados.carregarFilmes();
                            break;
                    }

                    System.out.println("Digite o id que deseja assistir.");
                    Audiovisual ver = ps.buscarAudiovisual(scanner.nextInt());
                    System.out.println("Deseja avaliar? 0 -> Não | 1 -> Sim.");
                    int opc = scanner.nextInt();
                    if (opc == 1) {
                        System.out.println("Digite uma nota de 1 a 5.");
                        ver.getAvaliacao().cadastrarAvaliacao(scanner.nextDouble());
                        System.out.println("Avaliação cadastrada");
                    }
                    System.out.println("Obrigado.");
                    break;
                case 8:
                    System.out.println("Saindo...");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }

}
