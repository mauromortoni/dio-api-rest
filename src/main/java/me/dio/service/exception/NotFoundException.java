package me.dio.service.exception;

public class NotFoundException extends BusinessException {

    private static final long serialVersionUID = -2310326040996335669L;

    public NotFoundException() {
        super("Resource not found.");
    }
}
