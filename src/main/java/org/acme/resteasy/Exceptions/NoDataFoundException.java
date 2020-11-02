package org.acme.resteasy.Exceptions;
public class NoDataFoundException extends RuntimeException{
    private static final long serialVersionUID = -123456789L;

    public NoDataFoundException(String message)
    {
        super(message);
    }
}
