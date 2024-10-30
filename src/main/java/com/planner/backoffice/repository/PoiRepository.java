package com.planner.backoffice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.planner.backoffice.model.Poi;

public interface PoiRepository extends JpaRepository<Poi, Long>, JpaSpecificationExecutor<Poi> {
  @EntityGraph(attributePaths = "city")
  Page<Poi> findAll(Specification<Poi> spec, Pageable pageable);

  @Override
  @EntityGraph(attributePaths = "city")
  Page<Poi> findAll(Pageable pageable);
}
