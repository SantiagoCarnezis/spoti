package com.scarnezis.spoti.persistance.entity;

import com.scarnezis.spoti.persistance.TableNames;
import com.scarnezis.spoti.persistance.entity.id.PlaylistId;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = TableNames.PLAYLIST)
@IdClass( value = PlaylistId.class)
public class  Playlist implements Queueable{

  @Id
  private String name;
  @Id
  private Long user_id;
  @ElementCollection
  private Collection<Track> tracks;
  private String description;
  @Column(nullable = false)
  private LocalDate createDate;
  @ManyToOne
  @MapsId("user_id")
  private User user;

  public void addSong(Track track){
    tracks.add(track);
  }

  public void removeSong(Track track) {
    tracks.remove(track);
  }


  public PlaylistId getPlaylistId(){
    return new PlaylistId(this.name, this.user_id);
  }

  @Override
  public Collection<Song> getSongsForQueue() {
    return getTracks().
        stream().
        sorted(Comparator.comparing(Track::getAddedAt)).
        map(Track::getSong).collect(Collectors.toList());
  }
}
