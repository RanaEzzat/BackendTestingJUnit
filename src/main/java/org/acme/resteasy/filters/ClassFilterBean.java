package org.acme.resteasy.filters;

import javax.ws.rs.QueryParam;

public class ClassFilterBean {
    private @QueryParam("start") int start;
    private @QueryParam("size") int size;
    private @QueryParam("firstLetter") char firstLetter;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public char getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(char firstLetter) {
        this.firstLetter = firstLetter;
    }
}
