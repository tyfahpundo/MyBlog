package zw.co.afrosoft.myblog.exception;

import org.springframework.http.HttpStatus;

public class UserNameAlreadyInUseException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public UserNameAlreadyInUseException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public UserNameAlreadyInUseException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
