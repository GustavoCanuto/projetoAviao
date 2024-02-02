package br.com.magnasistemas.apipassagem.infra;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class TratadorDeErro {


	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> tratarErro404() {

		return ResponseEntity.notFound().build();

	}
	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<String> tratarErro404NaoEncontrado(NoHandlerFoundException ex) {
	    return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity<String> tratarErroRegraDeNegocio(ValidacaoException ex) {

		return ResponseEntity.badRequest().body(ex.getMessage());

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> tratarErro500(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + ex.getLocalizedMessage());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> tratarErroForeignKeyConstraint(DataIntegrityViolationException ex) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("Não é possível excluir devido existir outra entidade vinculada a essa.");

	}
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    
    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
