package com.mrlonis.time.repository;

import com.mrlonis.time.entity.TestEntityCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEntityCalendarRepository extends JpaRepository<TestEntityCalendar, Long> {}
