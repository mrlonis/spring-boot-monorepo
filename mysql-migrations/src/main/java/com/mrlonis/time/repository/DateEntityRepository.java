package com.mrlonis.time.repository;

import com.mrlonis.time.entity.DateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateEntityRepository extends JpaRepository<DateEntity, Long> {}
