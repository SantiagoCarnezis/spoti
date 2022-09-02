package com.scarnezis.spoti.persistance.entity;

import lombok.Data;
import java.util.List;

@Data
public class Playlist implements Playable{

  private List<Song> songs;
  private PlayMode playMode;
  private String description;

  @Override
  public void play() {
    PlayQueue queue = playMode.createQueue(songs);
    queue.play();
  }

  public Gender gender(){
    //TODO
    return Gender.POP;
  }

  public void addSong(Song song){
    //TODO check if it is already and if he want add likewise
    songs.add(song);
  }

}
