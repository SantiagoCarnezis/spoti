package com.scarnezis.spoti.domain;

import com.scarnezis.spoti.domain.id.SongId;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;

@Data
@Entity
@Table
@IdClass( value = SongId.class)
@AllArgsConstructor
@NoArgsConstructor
public class Song{

  @Id
  private String name;
  @Id
  @Column(name = "artist_name")
  private String artist_name;
  @ManyToOne
  @MapsId("artist_name")
  private Artist artist;
  @Enumerated
  private Gender gender;
  @Column(nullable = false)
  private Duration duration;
  @Column(nullable = false)
  private LocalDate releaseDate;
  @Column(nullable = false)
  private Integer likesCounter;

  public SongId getSong_id(){
    return new SongId(this.name, this.getArtist().getName());
  }
}
