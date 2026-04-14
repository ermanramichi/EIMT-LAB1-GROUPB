package com.example.emtlabgroupb.model.projection;

import com.example.emtlabgroupb.model.domain.Category;

public interface AccommodationExtendedProjection {
    Long getId();
    String getName();
    Category getCategory();
    Integer getNumRooms();
    String getHostFirstName();
    String getHostLastName();
    String getHostCountry();
}
