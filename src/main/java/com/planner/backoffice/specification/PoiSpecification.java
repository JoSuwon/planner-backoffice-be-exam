package com.planner.backoffice.specification;

import org.springframework.data.jpa.domain.Specification;

import com.planner.backoffice.dto.SearchConditionRequest;
import com.planner.backoffice.model.City;
import com.planner.backoffice.model.Poi;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PoiSpecification {

  public static Specification<Poi> searchCondition(SearchConditionRequest condition) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      // name 조건 처리
      if (condition.getName() != null && condition.getName().getInputs() != null) {
        predicates.add(getNamePredicate(condition.getName(), root, criteriaBuilder));
      }

      // googlePlaceId 조건 처리
      if (condition.getGooglePlaceId() != null && condition.getGooglePlaceId().getInputs() != null) {
        predicates.add(getGooglePlaceIdPredicate(condition.getGooglePlaceId(), root, criteriaBuilder));
      }

      // cityName 조건 처리
      if (condition.getCityName() != null && condition.getCityName().getInputs() != null) {
        predicates.add(getCityNamePredicate(condition.getCityName(), root, criteriaBuilder));
      }

      // // zoneName 조건 처리
      // if (condition.getZoneName() != null && condition.getZoneName().getInputs() !=
      // null) {
      // predicates.add(getZoneNamePredicate(condition.getZoneName(), root,
      // criteriaBuilder));
      // }

      return predicates.isEmpty() ? null : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }

  private static Predicate getNamePredicate(SearchConditionRequest.NameSearch nameSearch,
      Root<Poi> root,
      CriteriaBuilder cb) {
    switch (nameSearch.getCondition().toLowerCase()) {
      case "in":
        return root.get("name").in(nameSearch.getInputs());
      case "like":
        List<Predicate> likePredicates = nameSearch.getInputs().stream()
            .map(input -> cb.like(root.get("name"), "%" + input + "%"))
            .collect(Collectors.toList());
        return cb.or(likePredicates.toArray(new Predicate[0]));
      case "=":
        return cb.equal(root.get("name"), nameSearch.getInputs().get(0));
      default:
        return null;
    }
  }

  private static Predicate getGooglePlaceIdPredicate(SearchConditionRequest.GooglePlaceIdSearch search,
      Root<Poi> root,
      CriteriaBuilder cb) {
    switch (search.getCondition().toLowerCase()) {
      case "in":
        return root.get("googlePlaceId").in(search.getInputs());
      case "like":
        List<Predicate> likePredicates = search.getInputs().stream()
            .map(input -> cb.like(root.get("googlePlaceId"), "%" + input + "%"))
            .collect(Collectors.toList());
        return cb.or(likePredicates.toArray(new Predicate[0]));
      case "=":
        return cb.equal(root.get("googlePlaceId"), search.getInputs().get(0));
      default:
        return null;
    }
  }

  private static Predicate getCityNamePredicate(SearchConditionRequest.CityNameSearch search,
      Root<Poi> root,
      CriteriaBuilder cb) {
    switch (search.getCondition().toLowerCase()) {
      case "in":
        return root.get("city").get("name").in(search.getInputs());
      case "like":
        List<Predicate> likePredicates = search.getInputs().stream()
            .map(input -> cb.like(root.get("city").get("name"), "%" + input + "%"))
            .collect(Collectors.toList());
        return cb.or(likePredicates.toArray(new Predicate[0]));
      case "=":
        return cb.equal(root.get("city").get("name"), search.getInputs().get(0));
      default:
        return null;
    }
  }

  private static Predicate getZoneNamePredicate(SearchConditionRequest.ZoneNameSearch search,
      Root<Poi> root,
      CriteriaBuilder cb) {
    switch (search.getCondition().toLowerCase()) {
      case "in":
        return root.get("zoneName").in(search.getInputs());
      case "like":
        List<Predicate> likePredicates = search.getInputs().stream()
            .map(input -> cb.like(root.get("zoneName"), "%" + input + "%"))
            .collect(Collectors.toList());
        return cb.or(likePredicates.toArray(new Predicate[0]));
      case "=":
        return cb.equal(root.get("zoneName"), search.getInputs().get(0));
      default:
        return null;
    }
  }
}
