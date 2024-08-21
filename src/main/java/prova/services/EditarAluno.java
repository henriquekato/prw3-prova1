package prova.services;

import jakarta.persistence.EntityManager;
import prova.DAO.AlunoDAO;
import prova.entities.Aluno;
import prova.exceptions.AlunoJaExistente;

import java.math.BigDecimal;

public class EditarAluno {
    private final EntityManager em;
    private final AlunoDAO dao;

    public EditarAluno(EntityManager em, AlunoDAO dao) {
        this.em = em;
        this.dao = dao;
    }

    public void editar(Aluno alunoGerenciado, String novoNome, String novoRa, String novoEmail, BigDecimal novaNota1, BigDecimal novaNota2, BigDecimal novaNota3) {
        Aluno alunoNovo = new Aluno(null, novoNome, novoRa, novoEmail, novaNota1, novaNota2, novaNota3);
        if (dao.existe(novoNome)) throw new AlunoJaExistente("nome já cadastrado");
        em.getTransaction().begin();
        dao.editar(alunoGerenciado, alunoNovo);
        em.getTransaction().commit();
    }
}
