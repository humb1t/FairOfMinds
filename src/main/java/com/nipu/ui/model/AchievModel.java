package com.nipu.ui.model;

import com.nipu.domain.Achievement;

/**
 * Created by humb1t on 15.02.14.
 */
public class AchievModel
{

    private String id;
    private CategoryModel category;
    private String title;
    private String text;
    private double rating;

    public AchievModel(CategoryModel category, String title, double rating)
    {
        this.category = category;
        this.title = title;
        this.rating = rating;
    }

    public AchievModel(CategoryModel category, String title, String text, double rating)
    {
        this.category = category;
        this.title = title;
        this.text = text;
        this.rating = rating;
    }

    public AchievModel(Achievement achievement)
    {
        this.id = String.valueOf(achievement.getId());
        this.category = new CategoryModel(achievement.getCategory());
        this.title = achievement.getTitle();
        this.text = achievement.getText();
        this.rating = achievement.getRating();
    }

    public String getId()
    {
        return id;
    }

    public CategoryModel getCategory()
    {
        return category;
    }

    public void setCategory(CategoryModel category)
    {
        this.category = category;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public double getRating()
    {
        return rating;
    }

    public void setRating(double rating)
    {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AchievModel that = (AchievModel) o;

        if (Double.compare(that.rating, rating) != 0) return false;
        if (!category.equals(that.category)) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (!title.equals(that.title)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        result = category.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString()
    {
        return "AchievModel{" +
                "category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                '}';
    }
}
