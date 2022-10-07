package com.scarnezis.spoti.controller;

import com.scarnezis.spoti.persistance.dto.PlaylistInDTO;
import com.scarnezis.spoti.persistance.dto.SongInDTO;
import com.scarnezis.spoti.persistance.entity.Playlist;
import com.scarnezis.spoti.persistance.entity.Song;
import com.scarnezis.spoti.persistance.entity.id.PlaylistId;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import com.scarnezis.spoti.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class PlaylistController {

  private final PlaylistService playlistService;

  @PostMapping("/user/{user_id}")
  public Playlist createPlaylist(@RequestBody PlaylistInDTO playlistInDTO,
                                 @PathVariable("user_id") Long userId){
    return this.playlistService.createPlaylist(playlistInDTO, userId);
  }

  @PostMapping("/{playlist_id}/song/{song_id}")
  public void addSong(@PathVariable("playlist_id") PlaylistId playlistId,
                      @PathVariable("song_id")SongId songId){
    this.playlistService.addSong(songId, playlistId);
  }


}
