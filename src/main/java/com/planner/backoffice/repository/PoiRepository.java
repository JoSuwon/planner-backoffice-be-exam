package com.planner.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.backoffice.model.Poi;

public interface PoiRepository extends JpaRepository<Poi, Long> {

}
