package com.mrlonis.time.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.mrlonis.time.entity.base.BaseEntity;
import java.util.List;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.repository.JpaRepository;

@UtilityClass
public class TestUtils {
    public static <D, T extends BaseEntity<D>, R extends JpaRepository<T, Long>> void assertInitialRepositoryConditions(
            R repository) {
        List<T> all = repository.findAll();
        assertFalse(all.isEmpty());
        assertEquals(1, all.size());
    }

    public static <D, T extends BaseEntity<D>, R extends JpaRepository<T, Long>> void assertEntityCreation(
            T entity, R repository) {
        assertNull(entity.getId());
        assertNull(entity.getCreatedDatetime());
        assertNull(entity.getUpdatedDatetime());

        entity = repository.saveAndFlush(entity);
        assertNotNull(entity.getId());
        assertNotNull(entity.getCreatedDatetime());
        assertNotNull(entity.getUpdatedDatetime());
    }
}
