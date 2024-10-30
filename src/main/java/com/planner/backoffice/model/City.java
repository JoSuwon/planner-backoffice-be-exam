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
@Table(name = "cities")
public class City {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "admin1code", nullable = true)
  private String admin1code;

  @Column(name = "admin2code", nullable = true)
  private String admin2code;

  @Column(name = "admin3code", nullable = true)
  private String admin3code;

  @Column(name = "admin4code", nullable = true)
  private String admin4code;

  @Column(name = "alternatenames", nullable = true, columnDefinition = "text")
  private String alternatenames;

  @Column(name = "asciiname", nullable = true)
  private String asciiname;

  @Column(name = "cc2", nullable = true)
  private String cc2;

  @Column(name = "country_code", nullable = true)
  private String countryCode;

  @Column(name = "created_at", nullable = false, columnDefinition = "timestamp")
  private Instant createdAt;

  @Column(name = "dem", nullable = true, columnDefinition = "int4")
  private Integer dem;

  @Column(name = "elevation", nullable = true, columnDefinition = "int4")
  private Integer elevation;

  @Column(name = "feature_class", nullable = true)
  private String featureClass;

  @Column(name = "feature_code", nullable = true)
  private String featureCode;

  @Column(name = "geonameid", nullable = true, columnDefinition = "int4")
  private Integer geonameid;

  @Column(name = "is_active", nullable = false, columnDefinition = "boolean default false")
  private Boolean isActive = false;

  @Column(name = "latitude", nullable = true, precision = 10, scale = 7)
  private BigDecimal latitude;

  @Column(name = "longitude", nullable = true, precision = 10, scale = 7)
  private BigDecimal longitude;

  @Column(name = "main_image_url", nullable = true)
  private String mainImageUrl;

  @Column(name = "name", nullable = true)
  private String name;

  @Column(name = "name_original", nullable = true)
  private String nameOriginal;

  @Column(name = "population", nullable = true, columnDefinition = "int8")
  private Long population;

  @Column(name = "timezone", nullable = true)
  private String timezone;

  @Column(name = "updated_at", nullable = false, columnDefinition = "timestamp")
  private Instant updatedAt;

  @Column(name = "country_id", nullable = true, columnDefinition = "int8")
  private Long countryId;
}
