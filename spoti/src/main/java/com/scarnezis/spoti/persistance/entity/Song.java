package com.scarnezis.spoti.persistance.entity;

import com.scarnezis.spoti.persistance.TableNames;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = TableNames.SONG)
@IdClass( value = SongId.class)
public class Song{

  @Id
  private String name;
  @Id
  @ManyToOne
  @JoinColumn(name = "artist_id")
  private Artist artist;
  @Enumerated
  private Gender gender;
  @Column(nullable = false)
  private Integer duration;
  @Column(nullable = false)
  private LocalDate releaseDate;
  @Column(nullable = false)
  private Integer numberOfLikes;

}
