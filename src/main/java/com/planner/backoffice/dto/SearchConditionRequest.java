package com.planner.backoffice.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchConditionRequest {
  private NameSearch name;
  private GooglePlaceIdSearch googlePlaceId;
  private CityNameSearch cityName;
  private ZoneNameSearch zoneName;

  @Getter
  @Setter
  public static class NameSearch {
    private String condition; // "in", "like", "="
    private List<String> inputs;
  }

  @Getter
  @Setter
  public static class GooglePlaceIdSearch {
    private String condition; // "in", "like", "="
    private List<String> inputs;
  }

  @Getter
  @Setter
  public static class CityNameSearch {
    private String condition; // "in", "like", "="
    private List<String> inputs;
  }

  @Getter
  @Setter
  public static class ZoneNameSearch {
    private String condition; // "in", "like", "="
    private List<String> inputs;
  }
}
