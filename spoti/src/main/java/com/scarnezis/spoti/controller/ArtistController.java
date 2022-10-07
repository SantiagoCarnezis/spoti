package com.scarnezis.spoti.controller;

import com.scarnezis.spoti.persistance.dto.ArtistInDTO;
import com.scarnezis.spoti.persistance.dto.SongInDTO;
import com.scarnezis.spoti.persistance.entity.Artist;
import com.scarnezis.spoti.persistance.entity.Song;
import com.scarnezis.spoti.service.ArtistService;
import com.scarnezis.spoti.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ArtistController {

  private final SongService songService;
  private final ArtistService artistService;

  @PostMapping("/{artist_name}/song")
  public Song createSong(@RequestBody SongInDTO songInDTO,
                         @PathVariable("artist_name") String artistName){
    Song song = this.songService.createSong(songInDTO, artistName);
    return song;
  }

  @PostMapping
  public Artist createArtist(@RequestBody ArtistInDTO artistInDTO){
    return this.artistService.createArtist(artistInDTO);
  }

  @GetMapping
  public String getAll(){
    return "Hola";
  }
}
