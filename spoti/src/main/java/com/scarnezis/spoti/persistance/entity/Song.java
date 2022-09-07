package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table
@Embeddable
public class Song{

  @Id
  @GeneratedValue
  private int id;

  @Column(nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name = "artist_id", nullable = false)
  private Artist artist;

  @Enumerated
  private Gender gender;

  @Column(nullable = false)
  private Float duration;

  @Column(nullable = false)
  private LocalDate releaseDate;

  @Column(nullable = false)
  private Integer numberOfLikes;
}
