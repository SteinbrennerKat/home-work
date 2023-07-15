package com.homework.dto;

public class UserPageRequestDto {
    private String createdDate;
    private Integer page = 0;
    private Integer size = 0;
    private String direction = "DESC";
    private String sortOrder = "createdDate";

    public UserPageRequestDto(
            String createdDate,
            Integer page,
            Integer size,
            String direction,
            String sortOrder
    ) {
        this.createdDate = createdDate;
        this.page = page;
        this.size = size;
        this.direction = direction;
        this.sortOrder = sortOrder;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public String getDirection() {
        return direction;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setCreatedDate(String createdDate) {
        if (createdDate != null) {
            this.createdDate = createdDate;
        }
    }

    public void setPage(Integer page) {
        if (page != null) {
            this.page = page;
        }
    }

    public void setSize(Integer size) {
        if (size != null) {
            this.size = size;
        }
    }

    public void setDirection(String direction) {
        if (direction != null) {
            this.direction = direction;
        }
    }

    public void setSortOrder(String sortOrder) {
        if (sortOrder != null) {
            this.sortOrder = sortOrder;
        }
    }
}
