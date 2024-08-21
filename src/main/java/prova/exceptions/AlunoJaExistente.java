package prova.exceptions;

public class AlunoJaExistente extends RuntimeException{
    public AlunoJaExistente(String message) {
        super(message);
    }
}
