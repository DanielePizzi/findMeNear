package webProject.framework.data;

import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.Collection;

/**
 * Repository generica di JPA per operazioni Crud
 *
 */
public interface BaseJPARepository<T extends Entity, ID extends Serializable> {
    /**
     * metodo per il setup del tipo di classe per l'entità per 
     * le funzioanlità del DAO
     * @param clazz
     */
    public void setupEntityClass(Class clazz);

    /**
     * Metodo per inserire una nuova riga nel config.database table
     * @param object
     *         l'oggetto dell entità da inserire
     */
    public T insert(T object);

    /**
     * Metodo per aggiornare una nuova riga nel config.database table
     *
     * @param object
     *         l'oggetto dell entità da aggiornare
     */
    public T update(T object);

    /**
     * Metodo per inserire o aggiornare una nuova 
     * riga se già esiste
     *
     * @param object
     *         L'ogetto dell'entità da aggiornare
     */
    public T insertOrUpdate(T object);

    /**
     * Metodo per eliminare una nuova riga nel config.database table
     *
     * @param object
     *         L'ogetto dell'entità da eliminare
     */
    public void delete(T object);

    /**
     * metoto per trovare gli oggetti del db tramite id
     *
     * @param id
     *         
     */
    public T findById(ID id);

    /**
     * Metodo per trovare la collezzione dell'entita di una pagina da database
     *
     * @param pageNum
     * @param countPerPage
     * @param order
     *
     * @return
     *
     * @throws Exception
     */
    public Collection<T> findAllByPage(int pageNum, int countPerPage, Order order);
}
