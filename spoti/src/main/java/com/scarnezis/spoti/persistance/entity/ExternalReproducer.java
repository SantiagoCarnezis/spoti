package com.scarnezis.spoti.persistance.entity;

import com.scarnezis.spoti.exceptions.TerminateSongReproductionException;

public interface ExternalReproducer {

  // return the true when the song terminates
  public void play(
      String songName,
      String artistName,
      String userName,
      Integer startingSecond,
      Integer songDuration)
      throws TerminateSongReproductionException;

  // returns the minute where song stop
  public Integer stop(
      String songName,
      String artistName,
      String userName);

}
