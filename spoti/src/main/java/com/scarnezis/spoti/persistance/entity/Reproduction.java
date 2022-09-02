package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

@Data
public class Reproduction {

  private Song song;
  private Integer seconds;
  //TODO its necessary a boolean state? like playing or stopped

  public Reproduction(Song song) {
    this.song = song;
    this.seconds = 0;
  }

  public void run(){
    //TODO start running the seconds
  }

  public void stop(){
    //TODO stop running the seconds
  }
}
