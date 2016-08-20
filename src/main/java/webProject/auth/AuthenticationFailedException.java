package webProject.auth;

import javax.servlet.ServletException;

/**
 * Questa eccezzione indica che l'autenticazione è fallita
 *
 * 
 */
public class AuthenticationFailedException extends ServletException {
    private static final long serialVersionUID = -8799659324455306881L;

    public AuthenticationFailedException(String message) {
        super(message);
    }
}
