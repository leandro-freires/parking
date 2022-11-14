package br.com.teste.parking.core.models.dtos;

import br.com.teste.parking.core.utils.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorDto {

    private Integer code;

    private String message;

    private LocalDateTime timestamp;

    private String detail;

    private List<?> errors;

    public static class Builder {

        private Integer code;

        private String message;

        private LocalDateTime timestamp;

        private String detail;

        private List<ValidationErrorDto> errors;

        public Builder(
              HttpStatus httpStatus
            , Throwable throwable
            , String message
            , Object... args
        ) {
            this.code = httpStatus.value();
            this.timestamp = LocalDateTime.now();
            this.message = this.getMessageBy(message, args);
            this.detail = this.getMessageDetailBy(throwable);
        }

        private String getMessageBy(String message, Object[] args) {
            return MessageUtil.parseMessage(StringUtils.hasText(message) ? message : "msg.erro.Default.message", args);
        }

        private String getMessageDetailBy(Throwable throwable) {
            return hasMessageDetail(throwable) ?
                    throwable.getMessage() :
                    MessageUtil.parseMessage("msg.erro.Default.message");
        }

        private boolean hasMessageDetail(Throwable throwable) {
            return !ObjectUtils.isEmpty(throwable) && StringUtils.hasText(throwable.getMessage());
        }

        public Builder fields(List<FieldError> fieldErrors) {
            this.errors = new ArrayList<>();
            fieldErrors.forEach(
                fieldError -> this.errors.add(
                    new ValidationErrorDto(
                          fieldError.getObjectName()
                        , fieldError.getField()
                        , fieldError.getRejectedValue()
                        , fieldError.getDefaultMessage()
                    )
                )
            );
            return this;
        }

        public ErrorDto build() {
            return new ErrorDto(
                  this.code
                , this.message
                , this.timestamp
                , this.detail
                , this.errors
            );
        }
    }

}
