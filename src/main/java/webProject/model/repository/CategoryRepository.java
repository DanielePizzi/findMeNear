package webProject.model.repository;

import webProject.framework.data.BaseJPARepository;
import webProject.model.entity.Category;

import java.util.List;

public interface CategoryRepository extends BaseJPARepository<Category, Long> {
    /**
     * 
     * Trova una categoria dal suo nome
     *
     * @param categoryName
     * @return
     */
    public Category findByCategoryName(String categoryName);

    /**
     * Trova una categoria dalla sua priorità
     *
     * @param categoryPriority
     * @return
     */
    public Category findByCategoryPriority(Integer categoryPriority);

    /**
     * Trova una cateoria dalla sua cateoria padre
     *
     * @param parentCategory
     * @return
     */
    public List<Category> findSubCategories(Category parentCategory);
}
