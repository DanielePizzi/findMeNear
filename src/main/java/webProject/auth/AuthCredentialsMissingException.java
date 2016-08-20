package webProject.auth;

import javax.servlet.ServletException;

/**
 * Questa eccezzione indica che mancano le credenziali per l'auth.
 *
 * 
 */
public class AuthCredentialsMissingException extends ServletException {
    private static final long serialVersionUID = -8799659324455306881L;

    public AuthCredentialsMissingException(String message) {
        super(message);
    }
}
