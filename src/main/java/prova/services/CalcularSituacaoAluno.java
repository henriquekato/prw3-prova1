package prova.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalcularSituacaoAluno {
    public BigDecimal calcularMedia(BigDecimal nota1, BigDecimal nota2, BigDecimal nota3){
        BigDecimal media = BigDecimal.ZERO;
        media = media.add(nota1);
        media = media.add(nota2);
        media = media.add(nota3);
        media = media.divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);
        return media;
    }

    public String calcularSituacao(BigDecimal media){
        String situacao;
        if (media.compareTo(BigDecimal.valueOf(6)) >= 0) situacao = "APROVADO";
        else if (media.compareTo(BigDecimal.valueOf(4)) >= 0) situacao = "RECUPERACAO";
        else situacao = "REPROVADO";
        return situacao;
    }
}
