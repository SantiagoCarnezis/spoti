package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Device {

  @Id
  @GeneratedValue
  private Long id;
  @Transient
  private Reproductor reproductor;
  @OneToOne
  private User user;
  @Embedded()
  private PlayQueue playQueue;
  @ManyToOne
  @JoinColumn(name = "song_name", referencedColumnName = "name")
  @JoinColumn(name = "song_artist", referencedColumnName = "artist_id")
  private Song playingSong;
  @Column
  private Integer playingSongSeconds;

  /*
  public void run(){
   Boolean isFinished = externalReproductor.play(
       getPlayingSong().getName(), getPlayingSong().getArtist().getName(), getUser().getName());
   if(isFinished)
     setPlayingSong(getPlayQueue().nextSong());
  }
  */
}
