package me.dio.service.exception;

public class BadRequestException extends BusinessException {

    private static final long serialVersionUID = -831341073886895557L;

    public BadRequestException() {
    super("The request body is empty or malformed.");
        }
}
