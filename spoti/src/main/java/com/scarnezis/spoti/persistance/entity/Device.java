package com.scarnezis.spoti.persistance.entity;

import com.scarnezis.spoti.persistance.TableNames;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
@Entity
@Table(name = TableNames.DEVICE)
public class Device {

  @Id
  @GeneratedValue
  private Long id;
  @Transient
  private Reproductor reproductor;
  @OneToOne
  private User user;
  @Embedded
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

  //@Autowired
  public void setReproductor(Reproductor reproductor){
    this.reproductor = reproductor;
  }

  public void logOut() {
    setReproductor(null);
    setUser(null);
    setPlayQueue(null);
    setPlayingSong(null);
    setPlayingSongSeconds(null);
  }
}
