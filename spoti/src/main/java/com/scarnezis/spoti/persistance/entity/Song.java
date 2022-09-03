package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table
public class Song{

  private String name;
  @OneToMany
  private Artist artist;
  @Enumerated
  private Gender gender;
  private Float seconds;
  private LocalDate releaseDate;
  private Integer numberOfLikes;
}
