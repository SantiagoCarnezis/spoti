package com.scarnezis.spoti.controller;

import com.scarnezis.spoti.persistance.entity.Song;
import com.scarnezis.spoti.service.SearchEntity;
import com.scarnezis.spoti.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class SongController {

  private final SongService service;

  @GetMapping
  public List<Song> getAll(@RequestParam("name") Optional<String> optionalSongName,
                           @RequestParam("artist") Optional<String> optionalArtistName){
    Boolean hasSong = optionalSongName.isPresent();
    Boolean hasArtist = optionalArtistName.isPresent();
    List<Song> songs;
    if(hasSong && hasArtist)
       songs = this.service.findAllByNameAndArtist(optionalSongName.get(), optionalArtistName.get());
    else if(hasSong)
      songs = this.service.findAllByName(optionalSongName.get());
    else if(hasArtist)
      songs = this.service.findAllByArtist(optionalArtistName.get());
    else
      songs = this.service.findAll();
    System.out.println("songs: " + songs);
    return songs;
  }

  @PatchMapping("/{song}/artist/{artist}/like")
  public void like(@PathVariable("artist") String artistName,
                   @PathVariable("song") String songName){

    this.service.likeSong(songName, artistName);
    //else
      //TODO throw error
  }

  @PatchMapping("/{song}/artist/{artist}/dislike")
  public void dislike(@PathVariable("artist") String artistName,
                      @PathVariable("song") String songName){

    this.service.quitLikeSong(songName, artistName);
    //else
      //TODO throw error
  }
}
