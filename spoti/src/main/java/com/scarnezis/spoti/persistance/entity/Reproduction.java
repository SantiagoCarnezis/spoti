package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

@Data
public class Reproduction {

  private Song song;
  private Integer seconds;
  //TODO its necessary a boolean state? like playing or stopped

  public void run(){
    //TODO start running the seconds
  }

  public void stop(){
    //TODO stop running the seconds
  }

  public void setSong(Song song) {
    this.song = song;
    this.seconds = 0;
  }

  public void finish(){
    song = null;
  }
}
