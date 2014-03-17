package com.nipu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: humb1t
 * Date: 24.11.13
 * Time: 3:54
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Answer
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String text;

    public Answer()
    {
    }

    public Answer(String text)
    {
        this.text = text;
    }

    public long getId()
    {
        return id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
