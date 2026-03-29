package com.mrlonis.time.repository;

import com.mrlonis.time.entity.TimestampEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimestampEntityRepository extends JpaRepository<TimestampEntity, Long> {}
