package com.scarnezis.spoti.service;

import com.scarnezis.spoti.exceptions.ResourceAlreadyExistsException;
import com.scarnezis.spoti.exceptions.ResourceNotFoundException;
import com.scarnezis.spoti.domain.*;
import com.scarnezis.spoti.domain.id.PlaylistId;
import com.scarnezis.spoti.domain.id.SongId;
import com.scarnezis.spoti.mappers.DeviceMapper;
import com.scarnezis.spoti.mappers.SongMapper;
import com.scarnezis.spoti.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Service
public class DeviceService {

  private final DeviceMapper mapper;
  private final SongMapper songMapper;
  private final DeviceRepository repository;
  private final Reproducer reproducer;
  private final SongService songService;
  private final PlaylistService playlistService;
  private final UserService userService;


  @Transactional
  public void playSong(Long deviceId, SongId songId) throws InterruptedException {
    Device device = this.get(deviceId);
    Song song = songService.get(songId);
    device.setPlayingSong(song);
    _addSongToQueue(device.getPlayQueue(), song);
    reproducer.run(device);
    repository.save(device);
  }

  public Device get(Long deviceId) {
    return repository
        .findById(deviceId)
        .orElseThrow(() ->
            new ResourceNotFoundException("Device " + deviceId));
  }

  public void validateExists(Long deviceId) {
    if (repository.existsById(deviceId))
      throw new ResourceAlreadyExistsException(deviceId.toString());
  }

  @Transactional
  public void play(Long deviceId) throws InterruptedException {
    Device device = this.get(deviceId);
    reproducer.run(device);
    repository.save(device);
  }

  @Transactional
  public void pause(Long deviceId) {
    Device device = this.get(deviceId);
    int pauseSecond = reproducer.stop(device);
    device.setPlayingSongSeconds(pauseSecond);
    repository.save(device);
  }

  public Device createDevice() {
    Device device = this.mapper.deviceInDTOToDevice(1);
    return this.repository.save(device);
  }

  @Transactional
  public void logIn(Long deviceId, Long userId) throws ResourceNotFoundException {
    User user = userService.get(userId);
    this.repository.logOut(userId);
    Device device = this.get(deviceId);
    device.setUser(user);
    this.repository.save(device);
  }

  @Transactional
  public void logOut(Long deviceId) throws ResourceNotFoundException {
    Device device = this.get(deviceId);
    device.logOut();
    this.repository.save(device);
  }

  @Transactional
  public void shuffleQueue(Long deviceId) throws ResourceNotFoundException {
    Device device = this.get(deviceId);
    device.getPlayQueue().shuffle();
    repository.save(device);
  }

  @Transactional
  public void addSongToQueue(Long deviceId, SongId songId) throws ResourceNotFoundException {
    Device device = this.get(deviceId);
    Song song = songService.get(songId);
    this._addSongToQueue(device.getPlayQueue(), song);
    repository.save(device);
  }

  @Transactional
  public void addPlaylistToQueue(Long deviceId, PlaylistId playlistId) throws ResourceNotFoundException {
    Device device = this.get(deviceId);
    Playlist playlist = playlistService.get(playlistId);
    playlist.
        getTracks().
        stream().
        sorted(Comparator.comparing(Track::getAddedAt)).
        map(Track::getSong).
        map(song -> songMapper.songToEnqueuedSong(song, device.getPlayQueue())).
        forEach(enqueuedSong -> device.getPlayQueue().addSong(enqueuedSong));
    repository.save(device);
  }

  private void _addSongToQueue(PlayQueue playQueue, Song song) {
    EnqueuedSong enqueuedSong = this.songMapper.songToEnqueuedSong(song, playQueue);
    playQueue.addSong(enqueuedSong);
  }
}
