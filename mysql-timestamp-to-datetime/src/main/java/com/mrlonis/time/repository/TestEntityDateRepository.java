package com.mrlonis.time.repository;

import com.mrlonis.time.entity.TestEntityDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEntityDateRepository extends JpaRepository<TestEntityDate, Long> {}
