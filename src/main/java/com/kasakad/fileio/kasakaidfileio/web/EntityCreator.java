package com.kasakad.fileio.kasakaidfileio.web;

import com.kasakad.fileio.kasakaidfileio.domain.EntityObject;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public abstract class EntityCreator<T extends MappedDTO, Y extends EntityObject> {

    public abstract Y createEntity(T mapped);

    public abstract List<InvalidInformation> validate(T mapped);

    @Getter
    protected List<InvalidInformation> invalidInformation = new LinkedList<>();

    public void addInvalidInformation(InvalidInformation invalidInformation) {
        this.invalidInformation.add(invalidInformation);
    }
}
