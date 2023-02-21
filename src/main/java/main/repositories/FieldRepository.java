package main.repositories;

import main.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Long> {
    boolean existsByName(String name);
}
