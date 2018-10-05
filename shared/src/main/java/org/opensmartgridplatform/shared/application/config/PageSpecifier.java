package org.opensmartgridplatform.shared.application.config;

public class PageSpecifier {
    private final Integer pageSize;
    private final Integer pageNumber;

    public PageSpecifier(Integer pageSize, Integer pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }
}
