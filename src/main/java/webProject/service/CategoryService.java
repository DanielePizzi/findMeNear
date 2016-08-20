package webProject.service;

import webProject.framework.data.BaseService;
import webProject.framework.exception.NotFoundException;
import webProject.model.entity.Category;

import java.util.List;

/**
 * Brings in the basic CRUD service ops from BaseService. Insert additional ops here.
 *
 * Created by Y.Kamesh on 8/2/2015.
 */
public interface CategoryService extends BaseService<Category, Long> {
    /**
     * Validates whether the given category already
     * exists in the system.
     *
     * @param categoryName
     *
     * @return
     */
    public boolean isCategoryPresent(String categoryName);

    /**
     *
     * controlla se la validazione della priorità della categoria
     * non è già presente nel sistema
     *
     * @param priorityId
     *
     * @return
     */
    public boolean isPriorityPresent(Integer priorityId);

    /**
     * Trova una cateoria dal nome
     *
     * @param categoryName
     * @return
     */
    public Category findByCategoryName(String categoryName) throws NotFoundException;

    /**
     * Trova una sottocategoria dalla sua categoria padre
     *
     * @param parentCategory
     * @return
     */
    public List<Category> findSubCategories(Category parentCategory) throws NotFoundException;
}
