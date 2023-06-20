import src.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

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
        System.out.println("10 - Adicionar na Lista para ver mais tarde");
        System.out.println("11 - Mostrar Lista: ver mais tarde");
        System.out.println("12 - Mostrar Lista: assistidos");
        System.out.println("13 - Avaliação avulsa");
        System.out.println("14 - Relatórios");
        System.out.println("15 - Logoff");
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

    private static void criarAudioVisual(boolean tipoAudio) {
        int duracao = 0;
        System.out.println("Adicionar " + (tipoAudio ? "Filme" : "Serie"));
        System.out.println("Digite o ID");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o nome:");
        String nome = scanner.nextLine();
        System.out.println("Digite a data de lançamento:");
        String anoLancamento = scanner.nextLine();
        if (tipoAudio) {
            System.out.println("Digite a duração:");
            duracao = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.println("Pre-lancamento? (0 - não, 1 - sim)");
        int tipo = scanner.nextInt();

        if (tipo != 0 && tipo != 1) {
            System.out.println("Opção inválida. A mídia foi definida como regular");
            tipo = 0;
        }
        
        if (tipoAudio) {
            Filme filme = new Filme(id, nome, anoLancamento, duracao, Tipo.values()[tipo]);
            plataforma.adicionarFilme(filme);
        } else {
            Serie serie = new Serie(id, nome, anoLancamento, Tipo.values()[tipo]);
            plataforma.adicionarSerie(serie);
        }

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
        for (Idiomas g : Idiomas.values()) {
            System.out.println((g.ordinal() + 1) + " - " + g.toString());
        }

        resultado = Idiomas.values()[(Integer.parseInt(scanner.nextLine())) - 1].toString();

        return resultado;
    }

    private static String menuGenero() {
        String resultado;
        System.out.println("Generos disponiveis");

        for (Generos g : Generos.values()) {
            System.out.println((g.ordinal() + 1) + " - " + g.toString());
        }
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

    public static void verListaAssistidos() {
        if (clientAutenticado.getAssistidas().size() == 0) {
            System.out.println("Não há mídias assistidas!");
            return;
        }

        System.out.println("======= MÍDIAS ASSISTIDAS =======");
        clientAutenticado.getAssistidas().values().forEach(x -> System.out.println(x.toString()));
        System.out.println("=================================");
    }

    private static void avaliacaoAvulsa() {
        Audiovisual midia = null;

        verListaAssistidos();

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

    public static void main(String[] args) throws Exception {
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
                    criarAudioVisual(true);
                    break;

                case 6:
                    criarAudioVisual(false);
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
                    System.out.println("Lista de assistidos:");
                    verListaAssistidos();
                    break;

                case 13:
                    System.out.println("Avaliação Avulsa:");
                    avaliacaoAvulsa();
                    break;
                case 14:
                    System.out.println("Relatório");
                    relatorio(plataforma);
                    break;
                case 15:
                    System.out.println("Logoff");
                    logoff();
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

    private static void relatorio(PlataformaStreaming plataforma) throws Exception {
        Comparator<Audiovisual> comparatorAudiovisual;
        Comparator<Cliente> comparatorCliente;
        Predicate<Audiovisual> filtroAudiovisual;
        Predicate<Cliente> filtroCliente;
        Collector<Audiovisual, ?, String> mappingAudiovisual;
        Collector<Audiovisual, ?, Map<String, String>> groupingByAudiovisual;

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

        Relatorio relatorio = new Relatorio(plataforma.getClientes(), plataforma.getHashMapAudioVisual());

        switch (tipoRelatorio) {
            case 1:
                comparatorCliente = Comparator.comparingInt(c -> c.getAssistidas().size());
                System.out.println(relatorio.gerarRelatorioDeMidia(comparatorCliente));
                break;
            case 2:
                comparatorCliente = Comparator.comparingInt(c -> c.getAvaliacoes().size());
                System.out.println(relatorio.gerarRelatorioAvaliacao(comparatorCliente));
                break;
            case 3:
                filtroCliente = c -> c.getTotalAvaliacoes() >= 15;
                System.out.println(relatorio.gerarRelatorioClientes15Avaliacoes(filtroCliente));
                break;
            case 4:
                filtroAudiovisual = a -> a.getAvaliacoes().size() >= 100;
                comparatorAudiovisual = Comparator.comparingDouble(Audiovisual::gerarMediaAvaliacoes).reversed();
                System.out.println(relatorio.gerarRelatorioDezMelhores(filtroAudiovisual, comparatorAudiovisual));
                break;
            case 5:
                comparatorAudiovisual = Comparator.comparingInt(Audiovisual::getAudiencia).reversed();

                System.out.println(relatorio.gerarRelatorio10MaisVistas(comparatorAudiovisual));
                break;
            case 6:
                filtroAudiovisual = a -> a.getAvaliacoes().size() >= 100;
                comparatorAudiovisual = Comparator.comparingDouble(Audiovisual::gerarMediaAvaliacoes).reversed();

                mappingAudiovisual = Collectors.mapping(Audiovisual::toString, Collectors.joining(", "));
                groupingByAudiovisual = Collectors.groupingBy(Audiovisual::getGenero, mappingAudiovisual);

                System.out.println(relatorio.gerarRelatorioDezMelhoresGenero(
                        filtroAudiovisual,
                        comparatorAudiovisual,
                        groupingByAudiovisual));
                break;
            case 7:
                comparatorAudiovisual = Comparator.comparingInt(Audiovisual::getAudiencia).reversed();

                mappingAudiovisual = Collectors.mapping(Audiovisual::toString, joining(", "));
                groupingByAudiovisual = Collectors.groupingBy(Audiovisual::getGenero, mappingAudiovisual);

                System.out.println(
                        relatorio.gerarRelatorioMaisVistasGenero(comparatorAudiovisual, groupingByAudiovisual));
                break;
            default:
                System.out.println("A opção digitada não existe");
                break;
        }

        scanner.nextLine();
    }

}
