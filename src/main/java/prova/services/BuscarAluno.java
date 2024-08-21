package prova.services;

import prova.DAO.AlunoDAO;
import prova.entities.Aluno;

import java.util.List;

public class BuscarAluno {
    private final AlunoDAO dao;

    public BuscarAluno(AlunoDAO dao) {
        this.dao = dao;
    }

    public List<Aluno> buscarTodos() {
        return dao.buscarTodos();
    }

    public Aluno buscarPorNome(String nome) {
        return dao.buscarPorNome(nome);
    }
}
