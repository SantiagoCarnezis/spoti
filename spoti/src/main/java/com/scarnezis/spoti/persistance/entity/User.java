package com.scarnezis.spoti.persistance.entity;

import java.time.LocalDate;
import java.util.Collection;

public class User {

  private Collection<Playlist> ownPlaylists;
  private Collection<Playlist> suscribePlaylists;
  private String email;
  private LocalDate birthdate;
  private LocalDate registerDate;

  //TODO recomendateSongs
  /*
  public Collection<Song> recomendateSongs(){
    return
  }
   */
}
