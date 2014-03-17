package com.nipu.ui.model;

import com.nipu.domain.Answer;

/**
 * Created by humb1t on 16.02.14.
 */
public class AnswerModel
{

    private String id;
    private String text;

    public AnswerModel(String text)
    {
        this.text = text;
    }

    public AnswerModel(Answer answer)
    {
        this.id = String.valueOf(answer.getId());
        this.text = answer.getText();
    }

    public String getId()
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

    @Override
    public String toString()
    {
        return "AnswerModel{" +
                "text='" + text + '\'' +
                '}';
    }
}
