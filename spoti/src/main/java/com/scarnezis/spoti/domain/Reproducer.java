package com.scarnezis.spoti.domain;

import org.springframework.stereotype.Component;

@Component
public class Reproducer {

  public void run(Device device) throws InterruptedException {
    Song song = device.getPlayingSong();

    while(song != null){
      System.out.println("Playing " + song.getName() + "from " + song.getArtist_name());
      Thread.sleep(1000);
      device.setNextSong();
      song = device.getPlayingSong();
    }
  }

  public Integer stop(Device device){
    Song song = device.getPlayingSong();
    return 1;
  }
}
