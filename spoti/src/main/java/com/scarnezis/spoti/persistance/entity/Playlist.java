package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table
public class Playlist{

  @Id
  @GeneratedValue
  private Long id;
  @Column(nullable = false)
  private String name;
  @ElementCollection
  private Collection<Track> tracks;
  private String description;

  public Gender gender(){
    //TODO
    return Gender.POP;
  }

  public void addplaylistSongs(Track track){
    //TODO check if it is already and if he want add likewise, se hace en el controller
    tracks.add(track);
  }

}
