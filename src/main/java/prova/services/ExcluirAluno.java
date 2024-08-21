package prova.services;

import jakarta.persistence.EntityManager;
import prova.DAO.AlunoDAO;
import prova.entities.Aluno;

public class ExcluirAluno {
    private final EntityManager em;
    private final AlunoDAO dao;

    private final BuscarAluno buscarAluno;

    public ExcluirAluno(EntityManager em, AlunoDAO dao, BuscarAluno buscarAluno) {
        this.em = em;
        this.dao = dao;
        this.buscarAluno = buscarAluno;
    }

    public void excluir(String nome){
        Aluno aluno = buscarAluno.buscarPorNome(nome);
        em.getTransaction().begin();
        dao.excluir(aluno);
        em.getTransaction().commit();
    }
}
