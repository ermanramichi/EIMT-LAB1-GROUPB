package com.example.emtlabgroupb.model.projection;

import com.example.emtlabgroupb.model.domain.Category;

public interface AccommodationShortProjection {
    Long getId();
    String getName();
    Category getCategory();
    Integer getNumRooms();
}
