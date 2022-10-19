package com.scarnezis.spoti.persistance.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Reproducer {

  private ExternalReproducer externalReproducer;

  //TODO Async method
  public void run(Device device) {
    Song song = device.getPlayingSong();

    while(song != null){
      externalReproducer.play(
          song.getName(),
          song.getArtist_name(),
          device.getUser().getName(),
          device.getPlayingSongSeconds(),
          device.getPlayingSongSeconds());

      device.setNextSong();
      song = device.getPlayingSong();
    }
  }

  public Integer stop(Device device){
    Song song = device.getPlayingSong();
    return externalReproducer.stop(
        song.getName(),
        song.getArtist_name(),
        device.getUser().getName());
  }
}
