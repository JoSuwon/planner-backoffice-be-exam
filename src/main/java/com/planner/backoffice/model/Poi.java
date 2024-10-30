package com.planner.backoffice.model;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pois")
public class Poi {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "address", nullable = true)
  private String address;

  @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
  private Instant createdAt;

  @Column(name = "detail", nullable = true, columnDefinition = "text")
  private String detail;

  @Column(name = "email", nullable = true)
  private String email;

  @Column(name = "google_place_id", nullable = true)
  private String googlePlaceId;

  @Column(name = "google_url", nullable = true, columnDefinition = "text")
  private String googleUrl;

  @Column(name = "gpt_description", nullable = true, columnDefinition = "text")
  private String gptDescription;

  @Column(name = "grade", nullable = true, columnDefinition = "int4")
  private Integer grade;

  @Column(name = "is_landmark", nullable = true, columnDefinition = "boolean default false")
  private Boolean isLandmark;

  @Column(name = "kakao_url", nullable = true, columnDefinition = "text")
  private String kakaoUrl;

  @Column(name = "latitude", nullable = true, precision = 10, scale = 7)
  private BigDecimal latitude;

  @Column(name = "longitude", nullable = true, precision = 10, scale = 7)
  private BigDecimal longitude;

  @Column(name = "main_image_url", nullable = true)
  private String mainImageUrl;

  @Column(name = "name", nullable = true)
  private String name;

  @Column(name = "naver_url", nullable = true, columnDefinition = "text")
  private String naverUrl;

  @Column(name = "phone", nullable = true)
  private String phone;

  @Column(name = "ranking", nullable = true)
  private String ranking;

  @Column(name = "reservation_url", nullable = true, columnDefinition = "text")
  private String reservationUrl;

  @Column(name = "source", nullable = true)
  private String source;

  @Column(name = "timezone", nullable = true)
  private String timezone;

  @Column(name = "tripadvisor_location_id", nullable = true)
  private String tripadvisorLocationId;

  @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP")
  private Instant updatedAt;

  @Column(name = "website_url", nullable = true, columnDefinition = "text")
  private String websiteUrl;

  @Column(name = "city_id", nullable = true, columnDefinition = "int8")
  private Integer cityId;

  @Column(name = "poi_type_id", nullable = true, columnDefinition = "int8")
  private Integer poiTypeId;
}
