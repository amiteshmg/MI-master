package com.example.aadyam.mi.model;

import android.support.annotation.NonNull;

public class QuestionList
{

    private String Active;

    private String Description;

    private String CategoryId;

    private String FieldType;

    private String IsRemark;

    private String FieldData;

    private String QuestionId;

    private String DescriptionMarathi;

    private String DescriptionHindi;

    public String getActive ()
    {
        return Active;
    }

    public void setActive (String Active)
    {
        this.Active = Active;
    }

    public String getDescription ()
    {
        return Description;
    }

    public void setDescription (String Description)
    {
        this.Description = Description;
    }

    public String getCategoryId ()
    {
        return CategoryId;
    }

    public void setCategoryId (String CategoryId)
    {
        this.CategoryId = CategoryId;
    }

    public String getFieldType ()
    {
        return FieldType;
    }

    public void setFieldType (String FieldType)
    {
        this.FieldType = FieldType;
    }

    public String getIsRemark ()
    {
        return IsRemark;
    }

    public void setIsRemark (String IsRemark)
    {
        this.IsRemark = IsRemark;
    }

    public String getFieldData ()
    {
        return FieldData;
    }

    public void setFieldData (String FieldData)
    {
        this.FieldData = FieldData;
    }

    public String getQuestionId ()
    {
        return QuestionId;
    }

    public void setQuestionId (String QuestionId)
    {
        this.QuestionId = QuestionId;
    }

    public String getDescriptionMarathi ()
    {
        return DescriptionMarathi;
    }

    public void setDescriptionMarathi (String DescriptionMarathi)
    {
        this.DescriptionMarathi = DescriptionMarathi;
    }

    public String getDescriptionHindi ()
    {
        return DescriptionHindi;
    }

    public void setDescriptionHindi (String DescriptionHindi)
    {
        this.DescriptionHindi = DescriptionHindi;
    }

    @NonNull
    @Override
    public String toString()
    {
        return "{ Active = "+Active+", Description = "+Description+", CategoryId = "+CategoryId+", FieldType = "+FieldType+", IsRemark = "+IsRemark+", FieldData = "+FieldData+", QuestionId = "+  QuestionId+", DescriptionMarathi = "+DescriptionMarathi+", DescriptionHindi = "+DescriptionHindi+" }";
    }


}
