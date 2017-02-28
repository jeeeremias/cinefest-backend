package com.cinefest.pojo;

import java.util.List;

public class QueryParams {
    int offset;
    int size;
    List<String> sortParams;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getSortParams() {
        return sortParams;
    }

    public void setSortParams(List<String> sortParams) {
        this.sortParams = sortParams;
    }
}
