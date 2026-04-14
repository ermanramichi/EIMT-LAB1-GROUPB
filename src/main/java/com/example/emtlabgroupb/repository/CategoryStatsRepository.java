package com.example.emtlabgroupb.repository;

import com.example.emtlabgroupb.model.domain.Category;
import com.example.emtlabgroupb.model.views.CategoryStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryStatsRepository extends JpaRepository<CategoryStatsEntity, Category> {
}
