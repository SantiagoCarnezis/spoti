package com.scarnezis.spoti.controller;

import com.scarnezis.spoti.dto.ArtistInDTO;
import com.scarnezis.spoti.dto.SongInDTO;
import com.scarnezis.spoti.domain.Artist;
import com.scarnezis.spoti.domain.Song;
import com.scarnezis.spoti.service.ArtistService;
import com.scarnezis.spoti.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
  public Page<Artist> getAll(@RequestParam(value = "artist", required = false) String artistName,
                             @RequestParam(value = "offset", required = false) Integer offset,
                             @RequestParam(value = "page_size") Integer pageSize){

    Page<Artist> artists = null;
    if(artistName != null)
      artists = artistService.findAllByName(artistName, offset, pageSize);
    else
      artists = artistService.findAll(offset, pageSize);
    return artists;
  }

  @GetMapping("/{artist_name}")
  public ResponseEntity<Artist> getArtist(@PathVariable("artist_name") String artistName){
    return ResponseEntity.ok(artistService.get(artistName));
  }
}
