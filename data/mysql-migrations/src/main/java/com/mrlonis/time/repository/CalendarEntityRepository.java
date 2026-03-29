package com.mrlonis.time.repository;

import com.mrlonis.time.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarEntityRepository extends JpaRepository<CalendarEntity, Long> {}
