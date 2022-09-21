package com.group50.repository;

import com.group50.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository  extends JpaRepository<Visit, Integer> {
}
