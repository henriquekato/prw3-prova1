package prova;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import prova.DAO.AlunoDAO;
import prova.entities.Aluno;
import prova.exceptions.AlunoJaExistente;
import prova.services.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        Scanner scanner = new Scanner(System.in);
        EntityManager em = JPAUtil.getEntityManager();
        AlunoDAO alunoDAO = new AlunoDAO(em);
        BuscarAluno buscarAluno = new BuscarAluno(alunoDAO);
        CadastrarAluno cadastrarAluno = new CadastrarAluno(em, alunoDAO);
        EditarAluno editarAluno = new EditarAluno(em, alunoDAO);
        ExcluirAluno excluirAluno = new ExcluirAluno(em, alunoDAO, buscarAluno);
        CalcularSituacaoAluno calcularSituacaoAluno = new CalcularSituacaoAluno();

        int option;
        do {
            printMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            switch (option) {
                case 1 -> {
                    System.out.println("CADASTRO ALUNO");
                    System.out.print("nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("RA: ");
                    String ra = scanner.nextLine();
                    System.out.print("email: ");
                    String email = scanner.nextLine();
                    System.out.print("nota 1: ");
                    BigDecimal nota1 = scanner.nextBigDecimal();
                    System.out.print("nota 2: ");
                    BigDecimal nota2 = scanner.nextBigDecimal();
                    System.out.print("nota 3: ");
                    BigDecimal nota3 = scanner.nextBigDecimal();

                    try {
                        cadastrarAluno.cadastrar(nome, ra, email, nota1, nota2, nota3);
                    } catch (AlunoJaExistente e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("EXCLUIR ALUNO");
                    System.out.print("nome: ");
                    String nome = scanner.nextLine();

                    try {
                        excluirAluno.excluir(nome);
                        System.out.println("aluno removido");
                    } catch (NoResultException e) {
                        System.out.println("aluno não encontrado");
                    }
                }
                case 3 -> {
                    System.out.println("ALTERAR ALUNO");
                    System.out.print("nome: ");
                    String nome = scanner.nextLine();

                    Aluno aluno;
                    try {
                        aluno = buscarAluno.buscarPorNome(nome);
                    } catch (NoResultException e) {
                        System.out.println("aluno não encontrado");
                        break;
                    }

                    System.out.println("DADOS DO ALUNO:");
                    System.out.println(STR."RA: \{aluno.getRa()}");
                    System.out.println(STR."email: \{aluno.getEmail()}");
                    System.out.println(STR."notas: \{aluno.getNota1()} - \{aluno.getNota2()} - \{aluno.getNota3()}");

                    System.out.println();
                    System.out.println("NOVOS DADOS");
                    System.out.print("nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("RA: ");
                    String novoRa = scanner.nextLine();
                    System.out.print("email: ");
                    String novoEmail = scanner.nextLine();
                    System.out.print("nota 1: ");
                    BigDecimal novaNota1 = scanner.nextBigDecimal();
                    System.out.print("nota 2: ");
                    BigDecimal novaNota2 = scanner.nextBigDecimal();
                    System.out.print("nota 3: ");
                    BigDecimal novaNota3 = scanner.nextBigDecimal();

                    try {
                        editarAluno.editar(aluno, novoNome, novoRa, novoEmail, novaNota1, novaNota2, novaNota3);
                    } catch (AlunoJaExistente e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.println("CONSULTAR ALUNO");
                    System.out.print("nome: ");
                    String nome = scanner.nextLine();

                    Aluno aluno;
                    try {
                        aluno = buscarAluno.buscarPorNome(nome);
                    } catch (NoResultException e) {
                        System.out.println("aluno não encontrado");
                        break;
                    }

                    System.out.println("DADOS DO ALUNO:");
                    System.out.println(STR."RA: \{aluno.getRa()}");
                    System.out.println(STR."email: \{aluno.getEmail()}");
                    System.out.println(STR."notas: \{aluno.getNota1()} - \{aluno.getNota2()} - \{aluno.getNota3()}");
                }
                case 5 -> {
                    System.out.println("EXIBINDO TODOS OS ALUNOS");

                    List<Aluno> alunos = buscarAluno.buscarTodos();
                    alunos.forEach(a -> {
                        BigDecimal media = calcularSituacaoAluno.calcularMedia(a.getNota1(), a.getNota2(), a.getNota3());
                        String situacao = calcularSituacaoAluno.calcularSituacao(media);

                        System.out.println(STR."nome: \{a.getNome()}");
                        System.out.println(STR."RA: \{a.getRa()}");
                        System.out.println(STR."email: \{a.getEmail()}");
                        System.out.println(STR."notas: \{a.getNota1()} - \{a.getNota2()} - \{a.getNota3()}");
                        System.out.println(STR."media: \{media}");
                        System.out.println(STR."situação: \{situacao}");
                        System.out.println();
                    });
                }
            }
        } while (option != 6);
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("CADASTRO DE ALUNOS");
        System.out.println("1 - cadastrar aluno");
        System.out.println("2 - excluir aluno");
        System.out.println("3 - alterar aluno");
        System.out.println("4 - buscar aluno pelo nome");
        System.out.println("5 - listar alunos (com status de aprovação)");
        System.out.println("6 - FIM");
        System.out.print("Opção: ");
    }
}
