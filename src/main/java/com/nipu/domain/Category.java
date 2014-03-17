package com.nipu.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by humb1t on 10.03.14.
 */
@Entity
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String title;
    private String description;
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Category> childrens;

    public Category()
    {
        this.childrens = new LinkedList<Category>();
    }

    public Category(String title, String description)
    {
        this.title = title;
        this.description = description;
    }

    public Category(String title, String description, List<Category> childrens)
    {
        this.title = title;
        this.description = description;
        this.childrens = childrens;
    }

    public long getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void addChild(Category child)
    {
        this.childrens.add(child);
    }

    public List<Category> getChildrens()
    {
        return childrens;
    }

    public void setChildrens(List<Category> childrens)
    {
        this.childrens = childrens;
    }
}
