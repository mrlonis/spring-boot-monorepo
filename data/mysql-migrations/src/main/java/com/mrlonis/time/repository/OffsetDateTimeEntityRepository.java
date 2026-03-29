package com.mrlonis.time.repository;

import com.mrlonis.time.entity.OffsetDateTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffsetDateTimeEntityRepository extends JpaRepository<OffsetDateTimeEntity, Long> {}
