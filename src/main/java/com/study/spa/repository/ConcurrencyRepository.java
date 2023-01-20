package com.study.spa.repository;

import com.study.spa.entity.Concurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

public interface ConcurrencyRepository extends JpaRepository<Concurrency, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Concurrency> findById(Long id);
}
