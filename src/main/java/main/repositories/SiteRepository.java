package main.repositories;

import main.model.Site;
import main.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site, Long> {
    boolean existsByUrl(String url);
    boolean existsByStatus(Status status);
    Site findByUrl(String url);
}
