package com.student.dto;

import com.student.entity.Information;
import com.student.entity.Type;
import com.student.entity.TypeMapper;

public class InformationDTO {
    private Information information;
    private Type typeMapper;

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public Type getTypeMapper() {
        return typeMapper;
    }

    public void setTypeMapper(Type typeMapper) {
        this.typeMapper = typeMapper;
    }
}
