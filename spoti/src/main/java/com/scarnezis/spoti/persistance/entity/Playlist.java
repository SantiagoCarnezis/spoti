package com.scarnezis.spoti.persistance.entity;

import com.scarnezis.spoti.persistance.TableNames;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Data
@Entity
@Table(name = TableNames.PLAYLIST)
public class  Playlist{

  @Id
  @GeneratedValue
  private Long id;
  @Column(nullable = false)
  private String name;
  @ElementCollection
  private Collection<Track> tracks;
  private String description;
  @Column(nullable = false)
  private LocalDate createDate;

  public void addSong(Track track){
    tracks.add(track);
  }

  public void removeSong(Track track) {
    tracks.remove(track);
  }
}
