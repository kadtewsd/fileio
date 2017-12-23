package com.kasakad.fileio.kasakaidfileio;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public abstract class AbstractMatchingFileSourceVerification<T> {

    protected abstract String folderName();

    protected abstract String fileName();

    protected abstract int entityListSize();

    public void verifyEntity(List<T> entityList) {
        assertThat(verify(entityList), is(entityListSize()));
    }
    protected abstract int verify(List<T> entityList);
}
