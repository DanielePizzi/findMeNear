package webProject.controller;

import webProject.framework.api.APIResponse;
import webProject.framework.controller.BaseController;
import webProject.model.dto.CategoryDTO;
import webProject.model.entity.Category;
import webProject.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Creazione delle categorie e cattura delle Api
 *
 * 
 */
@Controller
@RequestMapping("category")
public class CategoryController extends BaseController {
    private static Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    /**
     * Method to handle creation of the category by extracting the categoryInfo json from
     * POST body expected in the format - {"name":"cat1", "priority":2, "parent":"pCat"}
     *
     * @param categoryDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, headers = {JSON_API_CONTENT_HEADER})
    public @ResponseBody
    APIResponse createCategory(@RequestBody CategoryDTO categoryDTO) throws Exception {
        if(StringUtils.isEmpty(categoryDTO.getName())
                && (categoryDTO.getPriority()==null || StringUtils.isEmpty(categoryDTO.getParent()))) {
            return APIResponse.toErrorResponse("required params missing. format - {\"name\":\"cat1\", \"priority\":2, \"parent\":\"pCat\"}");
        }

        if(categoryService.isCategoryPresent(categoryDTO.getName())) {
            LOG.info("Category taken: "+categoryDTO.getName());
            return APIResponse.toErrorResponse("Category taken");
        }

        Category parentCategory = null;
        if(!StringUtils.isEmpty(categoryDTO.getParent())) {
            parentCategory = categoryService.findByCategoryName(categoryDTO.getParent());
        }

        if(parentCategory != null && categoryDTO.getPriority() != null) {
            if(parentCategory.getPriority() != categoryDTO.getPriority()) {
                return APIResponse.toErrorResponse("Sub-Category has to take same priority as parent");
            }
        }

        if(categoryService.isPriorityPresent(categoryDTO.getPriority())) {
            LOG.info("Category Priority taken: "+categoryDTO.getPriority());
            return APIResponse.toErrorResponse("Priority taken");
        }

        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setPriority(categoryDTO.getPriority());
        if(parentCategory != null) {
            category.setPriority(parentCategory.getPriority());
            category.setParentCategory(parentCategory);
        }

        categoryService.insert(category);
        return APIResponse.toOkResponse(category);
    }

    /**
     * Metodo per catturare le categorie tramite ID
     * GET
     *
     * @param catId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getById/{catId}", method = RequestMethod.GET)
    public @ResponseBody
    APIResponse getCategoryById(@PathVariable Long catId) throws Exception {
        Category category = categoryService.findById(catId);
        return APIResponse.toOkResponse(category);
    }

    /**
     * metodo per catturare le categorie tramite nome
     * GET
     *
     * @param catName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getByName/{catName}", method = RequestMethod.GET)
    public @ResponseBody
    APIResponse getCategoryByName(@PathVariable String catName) throws Exception {
        Category category = categoryService.findByCategoryName(catName);
        return APIResponse.toOkResponse(category);
    }


    /**
     * Metodo per catturare le sottocategorie tramite nome
     * GET
     *
     * @param parentCatName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getSubCategoriesByName/{parentCatName}", method = RequestMethod.GET)
    public @ResponseBody
    APIResponse getSubCategoriesByName(@PathVariable String parentCatName) throws Exception {
        Category category = categoryService.findByCategoryName(parentCatName);

        List<Category> subCategories = null;
        if(category != null) {
            subCategories = categoryService.findSubCategories(category);
        }

        return APIResponse.toOkResponse(subCategories);
    }


    /**
     * Metodo per catturare le sottocategorie tramite nome
     * GET
     *
     * @param parentCatId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getSubCategoriesById/{parentCatId}", method = RequestMethod.GET)
    public @ResponseBody
    APIResponse getSubCategoriesById(@PathVariable Long parentCatId) throws Exception {
        Category category = categoryService.findById(parentCatId);

        List<Category> subCategories = null;
        if(category != null) {
            subCategories = categoryService.findSubCategories(category);
        }

        return APIResponse.toOkResponse(subCategories);
    }
}