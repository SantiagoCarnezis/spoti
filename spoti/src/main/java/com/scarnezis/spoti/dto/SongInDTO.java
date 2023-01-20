package com.scarnezis.spoti.dto;

import com.scarnezis.spoti.domain.Artist;
import com.scarnezis.spoti.domain.Gender;
import lombok.*;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongInDTO {

  private String name;
  private Artist artist;
  private Gender gender;
  private Duration duration;

  public SongInDTO(String name, Gender gender, Duration duration) {
    this.name = name;
    this.gender = gender;
    this.duration = duration;
  }
}
