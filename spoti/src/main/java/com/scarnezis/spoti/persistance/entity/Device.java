package com.scarnezis.spoti.persistance.entity;

import com.scarnezis.spoti.persistance.TableNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = TableNames.DEVICE)
@NoArgsConstructor
@AllArgsConstructor
public class Device {

  @Id
  @GeneratedValue
  private Long id;
  @OneToOne
  private User user;
  @Embedded
  private PlayQueue playQueue;
  @ManyToOne
  @JoinColumn(name = "song_name", referencedColumnName = "name")
  @JoinColumn(name = "song_artist", referencedColumnName = "artist_name")
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

  public void logOut() {
    setUser(null);
    setPlayQueue(null);
    setPlayingSong(null);
    setPlayingSongSeconds(null);
  }

  public void setNextSong() {
    Song song = this.getPlayQueue().nextSong();
    this.setPlayingSong(song);
  }
}
