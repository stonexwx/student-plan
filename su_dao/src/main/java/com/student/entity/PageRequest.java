package com.student.entity;

public class PageRequest {
    private int page;
    private int limit;

    public PageRequest(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    public PageRequest() {
    }

    public int getPage() {
        return  (page-1)*limit;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
