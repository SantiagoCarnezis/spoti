package com.scarnezis.spoti.service;

import com.scarnezis.spoti.exceptions.NoSuchElementInTableException;
import com.scarnezis.spoti.persistance.entity.*;
import com.scarnezis.spoti.persistance.entity.id.PlaylistId;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import com.scarnezis.spoti.persistance.mappers.DeviceMapper;
import com.scarnezis.spoti.persistance.mappers.SongMapper;
import com.scarnezis.spoti.persistance.repository.DeviceRepository;
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
  private final SearchEntity searcherEntity;
  private final Reproducer reproducer;

  @Transactional
  public void playSong(Long deviceId, SongId songId)  {
    Device device = searcherEntity.getDevice(deviceId);
    Song song = searcherEntity.getSong(songId);
    device.setPlayingSong(song);
    _addSongToQueue(device.getPlayQueue(), song);
    reproducer.run(device);
    repository.save(device);
  }

  @Transactional
  public void play(Long deviceId)  {
    Device device = searcherEntity.getDevice(deviceId);
    reproducer.run(device);
    repository.save(device);
  }

  @Transactional
  public void pause(Long deviceId)  {
    Device device = searcherEntity.getDevice(deviceId);
    int pauseSecond = reproducer.stop(device);
    device.setPlayingSongSeconds(pauseSecond);
    repository.save(device);
  }

  public Device createDevice(){
    Device device = this.mapper.deviceInDTOToDevice(1);
    return this.repository.save(device);
  }

  @Transactional
  public void shuffleQueue(Long deviceId) throws NoSuchElementInTableException {
    Device device = searcherEntity.getDevice(deviceId);
    device.getPlayQueue().shuffle();
    repository.save(device);
  }

  @Transactional
  public void addSongToQueue(Long deviceId, SongId songId) throws NoSuchElementInTableException {
    Device device = searcherEntity.getDevice(deviceId);
    Song song = searcherEntity.getSong(songId);
    this._addSongToQueue(device.getPlayQueue(), song);
    repository.save(device);
  }

  @Transactional
  public void addPlaylistToQueue(Long deviceId, PlaylistId playlistId) throws NoSuchElementInTableException {
    Device device = searcherEntity.getDevice(deviceId);
    Playlist playlist = searcherEntity.getPlaylist(playlistId);
    playlist.
        getSongsForQueue().
        stream().
        map(song -> songMapper.songToEnqueuedSong(song, device.getPlayQueue())).
        forEach(enqueuedSong -> device.getPlayQueue().addSong(enqueuedSong));
    repository.save(device);
  }

  private void _addSongToQueue(PlayQueue playQueue, Song song) {
    EnqueuedSong enqueuedSong = this.songMapper.songToEnqueuedSong(song, playQueue);
    playQueue.addSong(enqueuedSong);
  }
}
