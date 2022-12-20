package com.scarnezis.spoti.persistance.entity;

import com.scarnezis.spoti.persistance.TableNames;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = TableNames.SONG)
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
  private Integer duration;
  @Column(nullable = false)
  private LocalDate releaseDate;
  @Column(nullable = false)
  private Integer numberOfLikes;

  public SongId getSong_id(){
    return new SongId(this.name, this.getArtist().getName());
  }
}
