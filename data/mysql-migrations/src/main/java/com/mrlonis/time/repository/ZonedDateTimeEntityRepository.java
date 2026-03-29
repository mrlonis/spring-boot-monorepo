package com.mrlonis.time.repository;

import com.mrlonis.time.entity.ZonedDateTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonedDateTimeEntityRepository extends JpaRepository<ZonedDateTimeEntity, Long> {}
