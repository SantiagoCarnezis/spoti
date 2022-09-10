package com.scarnezis.spoti.persistance.entity;

public interface ExternalReproductor {

  // return the true when the song terminates
  public Boolean play(String songName, String artistName, String userName, Integer startingSecond);

  // returns the minute where song stop
  public Integer stop(String songName, String artistName, String userName);

}
