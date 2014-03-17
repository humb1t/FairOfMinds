package com.nipu.ui.model;

import com.nipu.domain.Answer;
import com.nipu.domain.Criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by humb1t on 15.02.14.
 */
public class InterviewModel
{

    private List<CriteriaModel> criterias;

    public InterviewModel()
    {
        this.criterias = new ArrayList<CriteriaModel>();
    }

    public InterviewModel(List<Criteria> criteriasList)
    {
        this.criterias = new ArrayList<CriteriaModel>();
        for (Criteria criteria : criteriasList)
        {
            this.criterias.add(new CriteriaModel(criteria));
        }
    }

    public List<CriteriaModel> getCriterias()
    {
        return criterias;
    }

    public void setCriterias(List<CriteriaModel> criterias)
    {
        this.criterias = criterias;
    }

    @Override
    public String toString()
    {
        return "InterviewModel{" +
                "criterias=" + criterias +
                '}';
    }
}
