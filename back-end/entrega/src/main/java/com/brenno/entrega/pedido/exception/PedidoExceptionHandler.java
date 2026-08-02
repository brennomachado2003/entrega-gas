package com.brenno.entrega.pedido.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(
        basePackages = "com.brenno.entrega.pedido.controller"
)
public class PedidoExceptionHandler {

    @ExceptionHandler(PedidoJaAceitoException.class)
    public ResponseEntity<String> handle(PedidoJaAceitoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
}
