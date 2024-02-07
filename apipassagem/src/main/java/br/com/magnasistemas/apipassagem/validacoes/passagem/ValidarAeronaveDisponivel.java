package br.com.magnasistemas.apipassagem.validacoes.passagem;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoCadastro;
import br.com.magnasistemas.apipassagem.repository.PassagemRepository;
import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;

@Component
public class ValidarAeronaveDisponivel implements ValidarCadastroPassagem {
    
    @Autowired
    private PassagemRepository passagemRepository;
    
    String mensagemDeValidacao = "A aeronave já tem uma viagem dentro do intervalo de tempo especificado";

    public void validar(PassagemDtoCadastro dados) {
        LocalDateTime inicioIntervalo = dados.timestampPartida().minusHours(3); 
        LocalDateTime fimIntervalo = dados.timestampChegada().plusHours(3);

        boolean aeronaveEmUso = passagemRepository.existsByAeronaveIdAndTimestampInterval(dados.idAeronave(), inicioIntervalo, fimIntervalo);
        
        if (aeronaveEmUso) {
            throw new ValidacaoException(mensagemDeValidacao);
       }
    }
}
