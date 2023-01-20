package com.scarnezis.spoti.controller;

import com.scarnezis.spoti.domain.Track;
import com.scarnezis.spoti.dto.PlaylistInDTO;
import com.scarnezis.spoti.domain.Playlist;
import com.scarnezis.spoti.domain.id.PlaylistId;
import com.scarnezis.spoti.domain.id.SongId;
import com.scarnezis.spoti.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class PlaylistController {

  private final PlaylistService playlistService;

  @PostMapping("/user/{owner_id}")
  public ResponseEntity<Playlist> createPlaylist(@RequestBody PlaylistInDTO playlistInDTO,
                                                 @PathVariable("owner_id") Long ownerId){
    return ResponseEntity.ok(this.playlistService.createPlaylist(playlistInDTO, ownerId));
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
  public Page<Playlist> getAll(
      @RequestParam(value = "playlist_name", required = false) String playlistName,
      @RequestParam(value = "offser", required = false) Integer offset,
      @RequestParam(value = "page_size") Integer pageSize){

    Page<Playlist> playlists = null;

    if(playlistName != null)
      playlists = playlistService.findAllByName(playlistName, offset, pageSize);
    else
      playlists = playlistService.findAll(offset, pageSize);
    return playlists;
  }

  @GetMapping("/{playlist_name}/user/{owner_id}/songs")
  public  ResponseEntity<List<Track>> getTracks(@PathVariable("playlist_name") String playlistName,
                                                @PathVariable("owner_id") Long ownerId){
    PlaylistId playlistId = new PlaylistId(playlistName, ownerId);
    return ResponseEntity.ok(this.playlistService.getTracks(playlistId));
  }

}