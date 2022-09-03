package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table
public class Playlist{

  @ElementCollection
  private List<PlaylistSong> playlistSongs;
  private String description;

  public Gender gender(){
    //TODO
    return Gender.POP;
  }

  public void addplaylistSongs(PlaylistSong playlistSong){
    //TODO check if it is already and if he want add likewise, se hace en el controller
    playlistSongs.add(playlistSong);
  }

}
