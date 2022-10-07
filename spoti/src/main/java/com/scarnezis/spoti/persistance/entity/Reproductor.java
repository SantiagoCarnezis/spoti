package com.scarnezis.spoti.persistance.entity;

import org.springframework.stereotype.Component;

@Component
public class Reproductor {

  private ExternalReproductor externalReproductor;

  //TODO Async method
  public Boolean run(Device device) throws Exception {
    Song song = device.getPlayingSong();
    if(song.getDuration().compareTo(device.getPlayingSongSeconds()) < 0)
      throw new Exception();
    return externalReproductor.play(
        song.getName(), song.getArtist().getName(), device.getUser().getName(), device.getPlayingSongSeconds());
  }

  public Integer stop(Device device){
    Song song = device.getPlayingSong();
    Integer playingSongSeconds = externalReproductor.stop(
        song.getName(), song.getArtist().getName(), device.getUser().getName());
    return playingSongSeconds;
  }

}
