package webProject.model.repository;

import webProject.framework.data.BaseJPARepository;
import webProject.model.entity.User;


public interface UserRepository extends BaseJPARepository<User, Long> {
    /**
     * Trova un'utente dalla sua e-mail
     *
     * @param email
     * @return
     */
    public User findByEmail(String email);
}
