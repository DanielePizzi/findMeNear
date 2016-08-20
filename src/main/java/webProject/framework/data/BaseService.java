package webProject.framework.data;

import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.Collection;

/**
 * Interfaccia elenco dei servizi più basilari che devono essere presenti in qualsiasi
 * Servizio costruito su un oggetto entità di dominio che è persistente.
 *
 */
public interface BaseService<T extends Entity, ID extends Serializable> {
    /**
     * Metodo per settare il servizio di base.
     * viene invocato prima dell'inizializzazione di spring
     */
    public void setupService();

    /**
     * Servizio per inserire un uovo oggetto
     *
     * @param object
     *         Il nuovo oggetto
     */
    public T insert(T object) throws Exception;

    /**
     * Servizio per aggiornare un oggetto giè esistente
     *
     * @param object
     *         L'oggetto esistente
     */
    public T update(T object) throws Exception;

    /**
     * Servizio per eliminare un oggetto giè esistente
     *
     * @param object
     *          L'oggetto esistente
     */
    public void delete(T object) throws Exception;

    /**
     *  Servizio per trovare un oggetto esistente dall'id e dal nome     
     * @param id
     *         Id della risorsa
     */
    public T findById(ID id) throws Exception;


    /**
     * Servizio per trovare la collezione di entità da una pagina
     *
     * @param pageNum
     * @param countPerPage
     * @param order
     *
     * @return
     *
     * @throws Exception
     */
    public Collection<T> findAllByPage(int pageNum, int countPerPage, Order order) throws Exception;
}
