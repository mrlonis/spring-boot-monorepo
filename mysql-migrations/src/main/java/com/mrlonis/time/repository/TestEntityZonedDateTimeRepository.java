package com.mrlonis.time.repository;

import com.mrlonis.time.entity.TestEntityZonedDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEntityZonedDateTimeRepository extends JpaRepository<TestEntityZonedDateTime, Long> {}
