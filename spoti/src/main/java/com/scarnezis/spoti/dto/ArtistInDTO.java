package com.scarnezis.spoti.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistInDTO {

  private String name;
  private LocalDate birthday;
}
