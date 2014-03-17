package com.nipu.ui.model;

import com.nipu.domain.Answer;
import com.nipu.domain.Criteria;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by humb1t on 16.02.14.
 */
public class CriteriaModel
{

    private String id;
    private String title;
    private List<AnswerModel> answers;
    private double weight;

    public CriteriaModel(String title, double weight)
    {
        this.title = title;
        this.weight = weight;
    }

    public CriteriaModel(String title, List<AnswerModel> answers, double weight)
    {
        this.title = title;
        this.answers = answers;
        this.weight = weight;
    }

    public CriteriaModel(Criteria criteria)
    {
        this.id = String.valueOf(criteria.getId());
        this.title = criteria.getTitle();
        this.weight = criteria.getWeight();
        this.answers = new ArrayList<AnswerModel>();
        List<Answer> answers = criteria.getAnswers();
        for (Answer answer : answers)
        {
            this.answers.add(new AnswerModel(answer));
        }
    }

    public String getId()
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

    public List<AnswerModel> getAnswers()
    {
        return answers;
    }

    public void setAnswers(List<AnswerModel> answers)
    {
        this.answers = answers;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    @Override
    public String toString()
    {
        return "CriteriaModel{" +
                "title='" + title + '\'' +
                ", answers=" + answers +
                ", weight=" + weight +
                '}';
    }
}
