package com.scarnezis.spoti.persistance.dto;

import com.scarnezis.spoti.persistance.entity.Artist;
import com.scarnezis.spoti.persistance.entity.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Setter
@Getter
public class SongInDTO {

  private String name;
  private Artist artist;
  private Gender gender;
  private Integer duration;
}
