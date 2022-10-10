package com.scarnezis.spoti.controller;

import com.scarnezis.spoti.persistance.dto.PlaylistInDTO;
import com.scarnezis.spoti.persistance.entity.Playlist;
import com.scarnezis.spoti.persistance.entity.id.PlaylistId;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import com.scarnezis.spoti.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class PlaylistController {

  private final PlaylistService playlistService;

  @PostMapping("/user/{owner_id}")
  public Playlist createPlaylist(@RequestBody PlaylistInDTO playlistInDTO,
                                 @PathVariable("owner_id") Long ownerId){
    return this.playlistService.createPlaylist(playlistInDTO, ownerId);
  }

  @DeleteMapping("/{playlist_id}")
  public void deletePlaylist(@PathVariable("playlist_id") PlaylistId playlistId){
    this.playlistService.deletePlaylist(playlistId);
  }

  @PutMapping("/{playlist_id}/song/{song_id}/add")
  public void addSong(@PathVariable("playlist_id") PlaylistId playlistId,
                      @PathVariable("song_id")SongId songId){
    this.playlistService.addSong(songId, playlistId);
  }

  @PutMapping("/{playlist_id}/song/{song_id}/remove")
  public void removeSong(@PathVariable("playlist_id") PlaylistId playlistId,
                      @PathVariable("song_id")SongId songId){
    this.playlistService.removeSong(songId, playlistId);
  }

  @GetMapping
  public List<Playlist> getAll(
      @RequestParam("playlist_name") Optional<String> optionalPlaylistName,
      @RequestParam("owner_id") Optional<Long> optionalPlaylistOwner){
    List<Playlist> playlists = null;
    if(optionalPlaylistName.isPresent())
      playlists = playlistService.findAllByName(optionalPlaylistName.get());
    else if(optionalPlaylistName.isPresent())
      playlists = playlistService.findAllByUser(optionalPlaylistOwner.get());
    return playlists;
  }
}