package com.scarnezis.spoti.service;

import com.scarnezis.spoti.exceptions.NoSuchElementInTableException;
import com.scarnezis.spoti.persistance.dto.DeviceInDTO;
import com.scarnezis.spoti.persistance.entity.*;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import com.scarnezis.spoti.persistance.mappers.DeviceMapper;
import com.scarnezis.spoti.persistance.mappers.SongMapper;
import com.scarnezis.spoti.persistance.repository.DeviceRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;


public class DeviceService {

  private DeviceMapper mapper;
  private SongMapper songMapper;
  private DeviceRepository repository;
  private SearchEntity searcherEntity;
  private Reproductor reproductor;

  @Transactional
  public void playSong(Long deviceId, SongId songId)  {
    Device device = searcherEntity.getDevice(deviceId);
    Song song = searcherEntity.getSong(songId);
    device.setPlayingSong(song);
    _addSongToQueue(device.getPlayQueue(), song);
    device.setReproductor(reproductor);
    //device.run();
  }

  public Device createDevice(DeviceInDTO deviceInDTO){
    Device device = this.mapper.deviceInDTOToDevice(deviceInDTO);
    return this.repository.save(device);
  }

  public void shuffleQueue(Long deviceId) throws NoSuchElementInTableException {
    Device device = searcherEntity.getDevice(deviceId);
    device.getPlayQueue().shuffle();
  }

  @Transactional
  public void addSongToQueue(Long deviceId, SongId songId) throws NoSuchElementInTableException {
    Device device = searcherEntity.getDevice(deviceId);
    Song song = searcherEntity.getSong(songId);
    this._addSongToQueue(device.getPlayQueue(), song);
  }

  @Transactional
  public void addPlaylistToQueue(Long deviceId, Long playlistId) throws NoSuchElementInTableException {
    Device device = searcherEntity.getDevice(deviceId);
    Playlist playlist = searcherEntity.getPlaylist(playlistId);
    playlist.
        getTracks().
        stream().
        sorted(Comparator.comparing(Track::getAddedAt)).
        map(Track::getSong).
        map(song -> songMapper.songToEnqueuedSong(song, device.getPlayQueue())).
        forEach(enqueuedSong -> device.getPlayQueue().addSong(enqueuedSong));
  }

  private void _addSongToQueue(PlayQueue playQueue, Song song) {
    EnqueuedSong enqueuedSong = this.songMapper.songToEnqueuedSong(song, playQueue);
    playQueue.addSong(enqueuedSong);
  }
}
