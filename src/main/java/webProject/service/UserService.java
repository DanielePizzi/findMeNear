package webProject.service;

import webProject.framework.data.BaseService;
import webProject.framework.exception.EmailNotFoundException;
import webProject.model.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * Service class per le funzionalità di business dell'entità User
 */
public interface UserService extends BaseService<User, Long> {

    /**
     * Registra un nuovo User nel sistema
     *
     * @param user
     * @param request
     *
     * @return
     */
    public User registerUser(User user, HttpServletRequest request);


    /**
     * Login di un nuovo User nel sistema
     *
     * @param user
     * @param request
     *
     * @return
     */
    public User loginUser(User user, HttpServletRequest request);


    /**
     * Controlla se una password è già 
     * presente nel sistema
     *
     * @param user
     * @param pass
     *
     * @return
     */
    public boolean isValidPass(User user, String pass);


    /**
     * Controlla se una mail è 
     * già presente nel sistema
     *
     * @param email
     *
     * @return
     */
    public boolean isEmailExists(String email);


    /**
     * trova un utente a partire dalla sua mail
     *
     * @param email
     * @return
     */
    public User findByEmail(String email) throws EmailNotFoundException;
}
