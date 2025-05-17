package com.mrlonis.time.repository;

import com.mrlonis.time.entity.TestEntityOffsetDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEntityOffsetDateTimeRepository extends JpaRepository<TestEntityOffsetDateTime, Long> {}
