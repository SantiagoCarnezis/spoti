package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Reproduction {

  @ManyToOne
  @JoinColumn(name = "song_in_reproduction_id")
  private Song song;

  @Column(name = "reproduction_time_lapsed")
  private Integer timeLapsed;

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
