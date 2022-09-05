package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Reproduction {

  @ManyToOne
  @JoinColumn(name = "reproduction_song_id")
  private Song song;
  @Column(name = "reproduction_song_time_lapsed")
  private Integer timeLapsed;
  private Boolean isPlaying;

  public void run(){
    //TODO start running the seconds
  }

  public void stop(){
    //TODO stop running the seconds
  }

  public void setSong(Song song) {
    this.song = song;
    this.timeLapsed = 0;
  }

  public void finish(){
    song = null;
  }

  public boolean haveSong(){
    return song != null;
  }
}
