import src.*;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class App {
    static Scanner scanner = new Scanner(System.in);
    public static Cliente clientAutenticado;
    public static PlataformaStreaming plataforma = new PlataformaStreaming();

    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pausa() {
        System.out.println("Enter para continuar.");
        scanner.nextLine();
    }

    private static int menu() {
        System.out.println("Bem-vindo ao StreamingApp!");
        System.out.println("Escolha uma opção:");
        System.out.println("1  - Ver catálogo de filmes");
        System.out.println("2  - Ver catálogo de séries");
        System.out.println("3  - Buscar conteúdo:");
        System.out.println("4  - Ver perfil");
        System.out.println("5  - Adicionar Filme");
        System.out.println("6  - Adicionar Serie");
        System.out.println("7  - Assistir");
        System.out.println("8  - Filtrar");
        System.out.println("9 - Cadastrar um novo usuário");
        System.out.println("10 - Adicionar para ver mais tarde");
        System.out.println("11 - Mostrar para ver mais tarde");
        System.out.println("12 - Avaliação avulsa");
        System.out.println("13 - Relatórios");
        System.out.println("14 - Logoff");
        System.out.println("0  - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(scanner.nextLine());
        limparTela();
        return opcao;
    }

    private static int menuFiltros() {
        System.out.println("Escolha o que quer filtrar!");
        System.out.println("1 - Filtrar mídia por Gênero");
        System.out.println("2 - Filtrar mídia por Idioma");
        System.out.println("3 - Filtrar série por episódio");

        int opcao = Integer.parseInt(scanner.nextLine());
        limparTela();
        return opcao;
    }

    private static int menuInicialLogin() {
        System.out.println("Bem-vindo ao StreamingApp!");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Criar um novo perfil");
        System.out.println("2 - Fazer login");
        int opcao = Integer.parseInt(scanner.nextLine());
        limparTela();
        return opcao;
    }

    private static int menuTipoCadastro() {
        System.out.println("Escolha o tipo de cliente:");
        System.out.println("1 - Cliente");
        System.out.println("2 - Cliente Profissional");
        int opcao = Integer.parseInt(scanner.nextLine());
        limparTela();
        return opcao;
    }

    private static void adicionarFilme() {
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

        System.out.println("O filme e de pre-lancamento? (0 - não, 1 - sim)");
        int tipo = scanner.nextInt();

        if (tipo != 0 && tipo != 1) {
            System.out.println("Opção inválida. A mídia foi definida como regular");
            tipo = 0;
        }

        Filme filme = new Filme(id, nome, anoLancamento, duracao, Tipo.values()[tipo]);
        plataforma.adicionarFilme(filme);
    }

    private static void adicionarSerie() {
        System.out.println("Adicionar src.Serie...");
        System.out.println("Digite o ID da serie");
        int idSerie = scanner.nextInt();
        System.out.println("Digite o nome da serie:");
        String nomeSerie = scanner.next();
        System.out.println("Digite a data de lançamento:");
        String anoLancamentoSerie = scanner.next();
        System.out.println("A serie e de pre-lancamento? (0 - não, 1 - sim)");
        int tipo = scanner.nextInt();

        if (tipo != 0 && tipo != 1) {
            System.out.println("Opção inválida. A mídia foi definida como regular");
            tipo = 0;
        }

        Serie serie = new Serie(idSerie, nomeSerie, anoLancamentoSerie, Tipo.values()[tipo]);
        plataforma.adicionarSerie(serie);
        plataforma.salvarSerie();
    }

    private static void avaliar(Audiovisual ver) {
        if (ver.getAvaliacoes().get(clientAutenticado.getLogin()) == null) {
            String comentario;
            System.out.println("Digite uma nota de 0 a 5.");
            double nota = scanner.nextDouble();
            scanner.nextLine();
            System.out.println(
                    "Digite um comentario (apenas para clientes autorizados), caso não queira apenas aperte enter.");
            comentario = scanner.nextLine();

            try {
                clientAutenticado.adicionarAvaliacao(ver, nota, comentario);
                System.out.println("Avaliação cadastrada");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Obrigado.");
        } else
            System.out.println("Avaliação já cadastrada anteriormente.");

    }

    private static void assistir() {
        System.out.println("Digite o id que deseja assistir.");
        Audiovisual aud = plataforma.buscarAudiovisual(scanner.nextInt());

        if (aud == null) {
            System.out.println("Não foi encontrado nenhum audiovisual com esse id. Tente novamente");
        } else {
            try {
                clientAutenticado.adicionarNaListaJaVistas(aud);
            } catch (Exception e) {
                e.printStackTrace();
            }
            plataforma.salvarListasCliente(clientAutenticado);
            aud.registrarAudiencia();

            if (aud.getAvaliacoes().get(clientAutenticado.getLogin()) == null) {

                System.out.println("Deseja avaliar o audiovisual? 0 -> Não   1 -> Sim");
                int opcao = scanner.nextInt();
                if (opcao == 1)
                    avaliar(aud);
                else
                    System.out.println("Obrigado!");
            } else
                System.out.println("Avaliação já cadastrada anteriormente.");

        }
        scanner.nextLine();

    }

    private static void filtraMidiaPorGenero() {
        System.out.println("Digite o gênero:");
        String genero = menuGenero();
        Filtro<Audiovisual> f = new Filtro<>();

        Predicate<FiltroPersonalizado<Audiovisual>> filtrador = filtro -> filtro.getElemento().getGenero()
                .equals(filtro.getBusca());

        List<Audiovisual> palavrasFiltradas = f.filtrar(plataforma.getListaAudioVisual(), filtrador, genero);

        palavrasFiltradas.forEach(audiovisual -> System.out.println(audiovisual.toString()));
    }

    private static void filtraMidiaPorIdioma() {

        String idioma = menuLinguagem();
        Filtro<Audiovisual> f = new Filtro<>();

        Predicate<FiltroPersonalizado<Audiovisual>> filtrador = filtro -> filtro.getElemento().getIdioma()
                .equals(filtro.getBusca());

        List<Audiovisual> palavrasFiltradas = f.filtrar(plataforma.getListaAudioVisual(), filtrador, idioma);

        palavrasFiltradas.forEach(audiovisual -> System.out.println(audiovisual.toString()));
    }

    private static void filtraSeriePorQuantidadeEpisodios() {
        System.out.println("Digite a quantidade de episódios:");
        String qtdEpisodios = scanner.nextLine();
        Filtro<Serie> f = new Filtro<>();

        Predicate<FiltroPersonalizado<Serie>> filtrador = filtro -> Integer
                .toString(filtro.getElemento().getQuantidadeEpisodios())
                .equals(filtro.getBusca());

        List<Serie> palavrasFiltradas = f.filtrar(plataforma.getListaSerie(), filtrador, qtdEpisodios);
        if (palavrasFiltradas.size() > 0)
            palavrasFiltradas.forEach(serie -> System.out.println(serie.toString()));
        else
            System.out.println("Não foi encontrado nenhuma serie com esse numero de episodios.");

    }

    private static void identificaCliente() {
        System.out.println(clientAutenticado.toString());
    }

    private static void verPerfil() {
        if (clientAutenticado != null) {
            identificaCliente();
        } else {
            System.out.println("Você não está logado.");
            System.out.println("Digite seu login:");
            String login = scanner.nextLine();
            System.out.println("Digite sua senha:");
            String senha = scanner.nextLine();
            clientAutenticado = plataforma.login(login, senha);

            if (clientAutenticado != null) {
                identificaCliente();
            } else {
                System.out.println("Login ou senha incorretos. Tente novamente");
            }
        }
    }

    private static void cadastrarNovoUsuario() {
        int opcaoInicial;

        System.out.println("Digite seu nome de exibição:");
        String nomeUsuarioCadastro = scanner.nextLine();
        System.out.println("Digite seu login:");
        String loginCadastro = scanner.nextLine();
        System.out.println("Digite sua senha:");
        String senhaCadastro = scanner.nextLine();

        opcaoInicial = menuTipoCadastro();

        switch (opcaoInicial) {
            case 1:
                plataforma.cadastro(nomeUsuarioCadastro, loginCadastro, senhaCadastro, "Comum");
                break;
            case 2:
                plataforma.cadastro(nomeUsuarioCadastro, loginCadastro, senhaCadastro, "Profissional");
                break;
            default:
                plataforma.cadastro(nomeUsuarioCadastro, loginCadastro, senhaCadastro, "Comum");
                break;
        }

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

    private static String menuLinguagem() {
        String resultado;
        System.out.println("Linguagens disponiveis");
        System.out.println("1 - Português");
        System.out.println("2 - Italiano");
        System.out.println("3 - Francês");
        System.out.println("4 - Inglês");
        System.out.println("5 - Espanhol");

        resultado = Idiomas.values()[(Integer.parseInt(scanner.nextLine())) - 1].toString();

        return resultado;
    }

    private static String menuGenero() {
        String resultado;
        System.out.println("Generos disponiveis");
        System.out.println("1 - Terror");
        System.out.println("2 - Comédia");
        System.out.println("3 - Romance");
        System.out.println("4 - Suspense");
        System.out.println("5 - Ação");
        System.out.println("6 - Ficção Científica");
        System.out.println("7 - Drama");

        resultado = Generos.values()[(Integer.parseInt(scanner.nextLine())) - 1].toString();

        return resultado;
    }

    private static void assistirMaisTarde() {
        System.out.println("Digite o id que deseja adicionar na lista para assistir.");
        Audiovisual ver = plataforma.buscarAudiovisual(scanner.nextInt());

        if (ver == null) {
            System.out.println("Não foi encontrado nenhum audiovisual com esse id. Tente novamente");
        } else {
            clientAutenticado.adicionarNaLista(ver);
            System.out.println("Obrigado, foi adicionado na sua lista para assistir mais tarde.");
            plataforma.salvarListasCliente(clientAutenticado);
        }
        scanner.nextLine();
    }

    private static void verListaAssistirMaisTarde() {
        if (clientAutenticado.getParaVer().size() > 0) {
            clientAutenticado.getParaVer().values().forEach(x -> System.out.println(x.toString()));
            System.out.println("Deseja remover algum item dessa lista? Id - Sim ou 0 - Não");
            int opc = scanner.nextInt();
            if (opc != 0)
                clientAutenticado.retirarDaLista(opc);
        } else {
            System.out.println("Nenhum item encontrado");
        }
    }

    private static void avaliacaoAvulsa() {
        Audiovisual midia = null;

        if (clientAutenticado.getAssistidas().size() == 0) {
            System.out.println("Não há mídias assistidas!");
            return;
        }

        System.out.println("======= MÍDIAS ASSISTIDAS =======");
        clientAutenticado.getAssistidas().values().forEach(x -> System.out.println(x.toString()));
        System.out.println("=================================");

        System.out.println("Digite o Id da mídia:");
        int idMidia = scanner.nextInt();

        midia = clientAutenticado.getAssistidas().get(idMidia);

        if (midia == null) {
            System.out.println("A midia referente ao Id informado não existe");
            return;
        }

        avaliar(midia);

        scanner.nextLine();
    }

    public static void logoff() {
        clientAutenticado = null;
        logar();
    }

    public static void logar() {
        int opcaoInicial;
        do {

            opcaoInicial = menuInicialLogin();
            switch (opcaoInicial) {
                case 1:
                    cadastrarNovoUsuario();
                    break;
                case 2:
                    verPerfil();
                    break;
            }
        } while (clientAutenticado == null);
    }

    public static void main(String[] args) {
        int opcfiltro;
        int opcao;

        new Thread(() -> plataforma.carregarDados(), "segundoPlano").start();

        logar();

        do {
            limparTela();
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
                    opcfiltro = menuFiltros();
                    switch (opcfiltro) {
                        case 1:
                            filtraMidiaPorGenero();
                            break;
                        case 2:
                            filtraMidiaPorIdioma();
                            ;
                            break;
                        case 3:
                            filtraSeriePorQuantidadeEpisodios();
                            break;
                    }
                    break;
                case 9:
                    System.out.println("Criando um novo usuário:");
                    cadastrarNovoUsuario();
                    break;
                case 10:
                    System.out.println("Lista de assistir mais tarde:");
                    assistirMaisTarde();
                    break;
                case 11:
                    System.out.println("Lista de assistir mais tarde:");
                    verListaAssistirMaisTarde();
                    break;
                case 12:
                    System.out.println("Avaliação Avulsa:");
                    avaliacaoAvulsa();
                    break;
                case 13:
                    System.out.println("Relatório");
                    relatorio(plataforma);
                case 14:
                    System.out.println("Logoff");
                    logoff();
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

            System.out.println();
            pausa();
        } while (opcao != 0);

        scanner.close();
    }

    private static void relatorio(PlataformaStreaming plataforma) {
        String[] tiposRelatorio = new String[] {
                "Qual cliente assistiu mais mídias, e quantas mídias",
                "Qual cliente tem mais avaliações, e quantas avaliações",
                "Qual a porcentagem dos clientes com pelo menos 15 avaliações",
                "10 mídias de melhor avaliação, com pelo menos 100 avaliações, em ordem decrescente",
                "Quais são as 10 mídias com mais visualizações, em ordem decrescente;",
                "10 mídias de melhor avaliação, com pelo menos 100 avaliações, em ordem decrescente - SEPARADO POR GENERO",
                "Quais são as 10 mídias com mais visualizações, em ordem decrescente - SEPARADO POR GENERO"
        };

        for (int i = 0; i < tiposRelatorio.length; i++) {
            System.out.println((i + 1) + " - " + tiposRelatorio[i]);
        }

        System.out.println("Você deseja ver qual tipo de relatório? ");
        int tipoRelatorio = scanner.nextInt();

        Relatorio relatorio = new Relatorio(plataforma);

        switch (tipoRelatorio) {
            case 1:
                System.out.println(relatorio.gerarRelatorioDeMidia());
                break;
            case 2:
                System.out.println(relatorio.gerarRelatorioAvaliacao());
                break;
            case 3:
                System.out.println(relatorio.gerarRelatorioClientes15Avaliacoes());
                break;
            case 4:
                System.out.println(relatorio.gerarRelatorioDezMelhores());
                break;
            case 5:
                System.out.println(relatorio.gerarRelatorioMaisVistas());
                break;
            case 6:
                System.out.println(relatorio.gerarRelatorioDezMelhoresGenero());
                break;
            case 7:
                System.out.println(relatorio.gerarRelatorioMaisVistasGenero());
                break;
            default:
                System.out.println("A opção digitada não existe");
                break;
        }

        scanner.nextLine();
        scanner.nextLine();
    }

}
