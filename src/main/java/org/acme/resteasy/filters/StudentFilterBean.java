package org.acme.resteasy.filters;

import javax.ws.rs.QueryParam;

public class StudentFilterBean {
    private @QueryParam("year") int year;
    private @QueryParam("start") int start;
    private @QueryParam("size") int size;
    private @QueryParam("startYear") int startYear;
    private @QueryParam("endYear") int endYear;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

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

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }
}
