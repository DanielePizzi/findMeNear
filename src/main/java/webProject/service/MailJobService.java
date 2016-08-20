package webProject.service;

import webProject.framework.data.BaseService;
import webProject.model.entity.Job;
import webProject.model.entity.User;


public interface MailJobService extends BaseService<Job, Long> {

    /**
     *  Manda una mail di validazione all'utente
     *
     * @param user
     */
    public void sendConfirmationMail(User user);
}
