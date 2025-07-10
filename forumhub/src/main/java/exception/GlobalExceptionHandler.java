package exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        String mensagem = ex.getMessage();
        if ("Tópico não encontrado".equals(mensagem)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
        if ("Tópico duplicado".equals(mensagem)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem);
        }
        // Outras mensagens podem ser tratadas aqui conforme necessário
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem);
    }

    // Outros tratamentos globais podem ser adicionados aqui, ex:
    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<String> handleAll(Exception ex) {
    //    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno");
    // }
}
