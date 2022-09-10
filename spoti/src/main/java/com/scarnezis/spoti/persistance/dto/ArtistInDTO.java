package com.scarnezis.spoti.persistance.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ArtistInDTO {

  private String name;
  private LocalDate birthday;
}
