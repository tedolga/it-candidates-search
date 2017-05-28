package com.roi.poc.dto;

import com.roi.poc.dto.domain.Candidate;

import java.util.List;

public class SearchResult {
    private Long total;
    private Integer pageNumber;
    private Integer pageSize;
    private List<Candidate> result;

    public SearchResult(Long total, Integer pageNumber, Integer pageSize, List<Candidate> result) {
        this.total = total;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.result = result;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Candidate> getResult() {
        return result;
    }

    public void setResult(List<Candidate> result) {
        this.result = result;
    }
}
