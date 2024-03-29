package com.scarnezis.spoti.controller;

import com.scarnezis.spoti.domain.Device;
import com.scarnezis.spoti.domain.id.PlaylistId;
import com.scarnezis.spoti.domain.id.SongId;
import com.scarnezis.spoti.service.DeviceService;
import com.scarnezis.spoti.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class DeviceController {

  private final DeviceService deviceService;

  @PostMapping
  public ResponseEntity<Device> createDevice(){
    return ResponseEntity.ok(this.deviceService.createDevice());
  }

  @PostMapping("/{device_id}/user/{user_id}/login")
  public void logIn(@PathVariable("device_id") Long deviceId,
                    @PathVariable("user_id") Long userId){
    this.deviceService.logIn(deviceId, userId);
  }

  @PostMapping("/{device_id}/logout")
  public void logOut(@PathVariable("device_id") Long deviceId){
    this.deviceService.logOut(deviceId);
  }

  @PostMapping("/{device_id}/play")
  public void play(@PathVariable("device_id") Long deviceId,
                       @RequestBody(required = false) SongId songId) throws InterruptedException {
    if(songId == null)
      this.deviceService.play(deviceId);
    else
      this.deviceService.playSong(deviceId, songId);
  }

  @PostMapping("/{device_id}/pause")
  public void pause(@PathVariable("device_id") Long deviceId){
    this.deviceService.pause(deviceId);
  }

  @PostMapping("/{device_id}/queue/shuffle")
  public void shuffle(@PathVariable("device_id") Long deviceId){
    this.deviceService.shuffleQueue(deviceId);
  }

  @PatchMapping("/{device_id}/queue/add/playlist")
  public void addPlaylistToQueue(
      @PathVariable("device_id") Long deviceId,
      @RequestBody PlaylistId playlistId){
    this.deviceService.addPlaylistToQueue(deviceId, playlistId);
  }

  @GetMapping("/{device_id}")
  public ResponseEntity<Device> getDevice(@PathVariable("device_id")Long deviceId){
    return ResponseEntity.ok(deviceService.get(deviceId));
  }

  @PatchMapping("/{device_id}/queue/add/song")
  public void addSongToQueue(
      @PathVariable("device_id") Long deviceId,
      @RequestBody SongId songId){
    this.deviceService.addSongToQueue(deviceId, songId);
  }
}
