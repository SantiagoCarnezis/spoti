package com.scarnezis.spoti.persistance.entity;

import com.scarnezis.spoti.persistance.TableNames;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = TableNames.USER)
public class User {

  @Id
  @GeneratedValue
  private Long id;
  @Column(nullable = false)
  private String name;
  @OneToMany
  @JoinColumn(name = "user", nullable = false)
  private Set<Playlist> playlists;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private LocalDate birthdate;
  @Column(nullable = false)
  private LocalDate registerDate;

  public void addPlaylist(Playlist playlist){
    playlists.add(playlist);
  }

  public void removePlaylist(Playlist playlist){
    playlists.remove(playlist);
  }
}
