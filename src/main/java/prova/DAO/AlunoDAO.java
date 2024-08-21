package prova.DAO;

import jakarta.persistence.EntityManager;
import prova.entities.Aluno;

import java.util.List;

public class AlunoDAO {
    private final EntityManager em;

    public AlunoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Aluno aluno) {
        em.persist(aluno);
    }

    public List<Aluno> buscarTodos() {
        String jpql = "SELECT a FROM Aluno a";
        return em.createQuery(jpql, Aluno.class).getResultList();
    }

    public Aluno buscarPorNome(String nome) {
        String jpql = "SELECT a FROM Aluno a WHERE a.nome = :n";
        return em.createQuery(jpql, Aluno.class)
                .setParameter("n", nome)
                .getSingleResult();
    }

    public boolean existe(String nome){
        String jpql = "SELECT a FROM Aluno a WHERE a.nome = :n";
        return !em.createQuery(jpql, Aluno.class)
                .setParameter("n", nome)
                .getResultList().isEmpty();
    }

    public void editar(Aluno alunoGerenciado, Aluno alunoNovosDados) {
        alunoGerenciado.setNome(alunoNovosDados.getNome());
        alunoGerenciado.setRa(alunoNovosDados.getRa());
        alunoGerenciado.setEmail(alunoNovosDados.getEmail());
        alunoGerenciado.setNota1(alunoNovosDados.getNota1());
        alunoGerenciado.setNota2(alunoNovosDados.getNota2());
        alunoGerenciado.setNota3(alunoNovosDados.getNota3());
    }

    public void excluir(Aluno aluno) {
        em.remove(aluno);
    }
}
