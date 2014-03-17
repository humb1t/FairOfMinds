package com.nipu.ui.model;

import com.nipu.domain.Category;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by humb1t on 10.03.14.
 */
public class CategoryModel
{

    private String id;
    private String title;
    private String description;
    private List<CategoryModel> childrens;

    public CategoryModel(String id, String title, String description, List<CategoryModel> childrens)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.childrens = childrens;
    }

    public CategoryModel(String id, String title, String description)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.childrens = new LinkedList<CategoryModel>();
    }

    public CategoryModel(Category category)
    {
        this.id = String.valueOf(category.getId());
        this.title = category.getTitle();
        this.description = category.getDescription();
        this.childrens = new LinkedList<CategoryModel>();
        if (category.getChildrens() != null)
        {
            for (Category category1 : category.getChildrens())
            {
                this.childrens.add(new CategoryModel(category1));
            }
        }
    }
}
