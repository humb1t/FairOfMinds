package com.nipu.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: humb1t
 * Date: 24.11.13
 * Time: 2:49
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Criteria
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Answer> answers;
    private double weight;

    public Criteria()
    {
        this.answers = new ArrayList<Answer>();
    }

    public Criteria(String title, List<Answer> answers, double weight)
    {
        this.title = title;
        this.answers = answers;
        this.weight = weight;
    }

    public long getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public List<Answer> getAnswers()
    {
        return answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public double getWeight()
    {
        return weight;
    }
}
