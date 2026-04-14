package com.example.emtlabgroupb.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MaterializedViewRefreshScheduler {

    private static final Logger log = LoggerFactory.getLogger(MaterializedViewRefreshScheduler.class);

    @PersistenceContext
    private EntityManager entityManager;

    // Refreshes every 10 minutes (configurable via application property)
    @Scheduled(fixedRateString = "${app.materialized-view.refresh-interval-ms:600000}")
    @Transactional
    public void refreshCategoryStats() {
        log.info("[SCHEDULER] Refreshing materialized view: category_stats");
        entityManager.createNativeQuery("REFRESH MATERIALIZED VIEW CONCURRENTLY category_stats").executeUpdate();
        log.info("[SCHEDULER] Materialized view category_stats refreshed successfully.");
    }
}
