package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table
public class Song{

  @Id
  @GeneratedValue
  private int id;
  private String name;
  @ManyToOne
  @JoinColumn(name = "artist_id")
  private Artist artist;
  @Enumerated
  private Gender gender;
  private Float duration;
  private LocalDate releaseDate;
  private Integer numberOfLikes;
}
