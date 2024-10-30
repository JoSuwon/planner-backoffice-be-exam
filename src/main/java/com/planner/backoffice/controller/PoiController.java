package com.planner.backoffice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.backoffice.annotation.RequireAdminToken;
import com.planner.backoffice.dto.SearchConditionRequest;
import com.planner.backoffice.model.Poi;
import com.planner.backoffice.repository.PoiRepository;

@RestController
@RequestMapping("/api/poi")
public class PoiController {

  private final PoiRepository poiRepository;

  public PoiController(PoiRepository poiRepository) {
    this.poiRepository = poiRepository;
  }

  @PostMapping("/search")
  @RequireAdminToken
  public Page<Poi> searchPois(@RequestBody SearchConditionRequest searchConditionRequest, Pageable pageable) {
    System.out.println(searchConditionRequest);
    PageRequest pageRequest = PageRequest.of(1, 10);
    Page<Poi> pois = this.poiRepository.findAll(pageRequest);
    return pois;
  }
}
