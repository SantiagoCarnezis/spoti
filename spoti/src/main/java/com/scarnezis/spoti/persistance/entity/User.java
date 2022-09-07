package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table
public class User {

  @Id
  @GeneratedValue
  private int id;

  @Transient
  private Set<Playlist> ownPlaylists;

  @Transient
  private Set<Playlist> suscribePlaylists;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private LocalDate birthdate;

  @Column(nullable = false)
  private LocalDate registerDate;

  //TODO recomendateSongs
  /*
  public Collection<Song> recomendateSongs(){
    return
  }
   */
}
