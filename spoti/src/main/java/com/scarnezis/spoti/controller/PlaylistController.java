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

  @DeleteMapping("/{playlist_name}/user/{owner_id}")
  public void deletePlaylist(@PathVariable("playlist_name") String playlistName,
                             @PathVariable("owner_id") Long ownerId){
    PlaylistId playlistId = new PlaylistId(playlistName, ownerId);
    this.playlistService.deletePlaylist(playlistId);
  }

  @PatchMapping("/{playlist_name}/user/{owner_id}/add")
  public void addSong(@PathVariable("playlist_name") String playlistName,
                      @PathVariable("owner_id") Long ownerId,
                      @RequestBody SongId songId){
    PlaylistId playlistId = new PlaylistId(playlistName, ownerId);
    this.playlistService.addSong(songId, playlistId);
  }

  @PatchMapping("/{playlist_name}/user/{owner_id}/remove")
  public void removeSong(@PathVariable("playlist_name") String playlistName,
                         @PathVariable("owner_id") Long ownerId,
                         @RequestBody SongId songId){
    PlaylistId playlistId = new PlaylistId(playlistName, ownerId);
    this.playlistService.removeSong(songId, playlistId);
  }

  @GetMapping
  public List<Playlist> getAll(
      @RequestParam("playlist_name") Optional<String> optionalPlaylistName,
      @RequestParam("owner_id") Optional<Long> optionalPlaylistOwner){
    List<Playlist> playlists = null;
    if(optionalPlaylistName.isPresent())
      playlists = playlistService.findAllByName(optionalPlaylistName.get());
    else if(optionalPlaylistOwner.isPresent())
      playlists = playlistService.findAllByUser(optionalPlaylistOwner.get());
    return playlists;
  }
}