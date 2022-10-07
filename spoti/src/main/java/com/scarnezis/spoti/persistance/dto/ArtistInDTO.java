package com.scarnezis.spoti.persistance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ArtistInDTO {

  private String name;
  //@JsonFormat(pattern = "hh:mm:ss a")
  private LocalDate birthday;
}
