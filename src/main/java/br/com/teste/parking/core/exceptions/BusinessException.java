package br.com.teste.parking.core.exceptions;

import br.com.teste.parking.core.utils.MessageUtil;

public class BusinessException extends RuntimeException {

    public BusinessException(
          String message
        , Object... args
    ) {
        super(message(message, args));
    }

    public BusinessException(
          Throwable cause
        , String message
        , Object... args
    ) {
        super(message(message, args), cause);
    }

    private static String message(String message, Object[] args) {
        return MessageUtil.parseMessage(message, args);
    }
}
