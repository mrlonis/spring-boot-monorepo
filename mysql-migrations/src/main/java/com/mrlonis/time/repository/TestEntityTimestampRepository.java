package com.mrlonis.time.repository;

import com.mrlonis.time.entity.TestEntityTimestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEntityTimestampRepository extends JpaRepository<TestEntityTimestamp, Long> {}
