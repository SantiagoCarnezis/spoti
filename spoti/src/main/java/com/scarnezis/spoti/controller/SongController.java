package com.scarnezis.spoti.controller;

import com.scarnezis.spoti.domain.id.SongId;
import com.scarnezis.spoti.exceptions.ResourceNotFoundException;
import com.scarnezis.spoti.domain.Song;
import com.scarnezis.spoti.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class SongController {

  private final SongService service;

  @GetMapping
  public Page<Song> getAll(@RequestParam(value = "name", required = false) String songName,
                           @RequestParam(value = "artist", required = false) String artistName,
                           @RequestParam(value = "offset") Integer offset,
                           @RequestParam(value = "page_size") Integer pageSize){

    Page<Song> songs;
    if(songName != null && artistName != null)
       songs = this.service.findAllByNameAndArtist(songName, artistName, offset, pageSize);
    else if(songName != null)
      songs = this.service.findAllByName(songName, offset, pageSize);
    else if(artistName != null)
      songs = this.service.findAllByArtist(artistName, offset, pageSize);
    else
      songs = this.service.findAll(offset, pageSize);
    return songs;
  }

  @PatchMapping("/{song}/artist/{artist}/like")
  public ResponseEntity<Song> like(@PathVariable("artist") String artistName,
                                  @PathVariable("song") String songName)
      throws ResourceNotFoundException {

    return ResponseEntity.ok(this.service.likeSong(songName, artistName));
  }

  @PatchMapping("/{song}/artist/{artist}/dislike")
  public ResponseEntity<Song> dislike(@PathVariable("artist") String artistName,
                      @PathVariable("song") String songName){

    return ResponseEntity.ok(this.service.quitLikeSong(songName, artistName));
  }
}
