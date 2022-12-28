package com.scarnezis.spoti.controller;

import com.scarnezis.spoti.persistance.dto.ArtistInDTO;
import com.scarnezis.spoti.persistance.dto.SongInDTO;
import com.scarnezis.spoti.persistance.entity.Artist;
import com.scarnezis.spoti.persistance.entity.Song;
import com.scarnezis.spoti.service.ArtistService;
import com.scarnezis.spoti.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ArtistController {

  private final SongService songService;
  private final ArtistService artistService;

  @PostMapping("/{artist_name}/song")
  public ResponseEntity<Song> createSong(@RequestBody SongInDTO songInDTO,
                                        @PathVariable("artist_name") String artistName){
    return ResponseEntity.ok(this.songService.createSong(songInDTO, artistName));
  }

  @PostMapping
  public ResponseEntity<Artist> createArtist(@RequestBody ArtistInDTO artistInDTO){
    return ResponseEntity.ok(this.artistService.createArtist(artistInDTO));
  }

  @GetMapping
  public ResponseEntity<List<Artist>> getAll(@RequestParam("artist") Optional<String> optionalArtistName){
    List<Artist> artists = null;
    if(optionalArtistName.isPresent())
      artists = artistService.findAllByName(optionalArtistName.get());
    else
      artists = artistService.findAll();
    if (artists.isEmpty())
      return ResponseEntity.noContent().build();
    return ResponseEntity.ok(artists);
  }
}
