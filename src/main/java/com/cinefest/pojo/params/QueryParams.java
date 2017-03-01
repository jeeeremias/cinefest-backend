package com.cinefest.pojo.params;

import java.util.List;
import java.util.Map;

public class QueryParams {
    int page;
    int size;
    List<String> sort;
    Map<String, String> genericParams;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getSort() {
        return sort;
    }

    public void setSort(List<String> sort) {
        this.sort = sort;
    }

    public Map<String, String> getGenericParams() {
        return genericParams;
    }

    public void setGenericParams(Map<String, String> genericParams) {
        this.genericParams = genericParams;
    }
}
