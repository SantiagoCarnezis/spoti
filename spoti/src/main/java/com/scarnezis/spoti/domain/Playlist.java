package com.scarnezis.spoti.domain;

import com.scarnezis.spoti.domain.id.PlaylistId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Data
@Entity
@Table
@IdClass( value = PlaylistId.class)
@NoArgsConstructor
@AllArgsConstructor
public class  Playlist{

  @Id
  private String name;
  @Id
  private Long user_id;
  @ElementCollection(fetch = FetchType.LAZY)
  private Collection<Track> tracks;
  private String description;
  @Column(nullable = false)
  private LocalDate createDate;
  @ManyToOne
  @MapsId("user_id")
  private User user;

  public Playlist(String name, String description, LocalDate createDate, User user) {
    this.name = name;
    this.description = description;
    this.createDate = createDate;
    this.user = user;
  }

  public void addSong(Track track){
    tracks.add(track);
  }

  public void removeSong(Track track) {
    tracks.remove(track);
  }

  public PlaylistId getPlaylistId(){
    return new PlaylistId(this.name, this.user_id);
  }

  public void setUser(User user) {
    this.user = user;
    this.user_id = user.getId();
  }
}
