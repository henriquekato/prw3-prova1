package prova.services;

import jakarta.persistence.EntityManager;
import prova.DAO.AlunoDAO;
import prova.entities.Aluno;
import prova.exceptions.AlunoJaExistente;

import java.math.BigDecimal;

public class CadastrarAluno {
    private final EntityManager em;
    private final AlunoDAO dao;

    public CadastrarAluno(EntityManager em, AlunoDAO dao) {
        this.em = em;
        this.dao = dao;
    }

    public void cadastrar(String nome, String ra, String email, BigDecimal nota1, BigDecimal nota2, BigDecimal nota3) {
        Aluno aluno = new Aluno(null, nome, ra, email, nota1, nota2, nota3);
        if (dao.existe(nome)) throw new AlunoJaExistente("nome já cadastrado");
        em.getTransaction().begin();
        dao.cadastrar(aluno);
        em.getTransaction().commit();
    }
}
