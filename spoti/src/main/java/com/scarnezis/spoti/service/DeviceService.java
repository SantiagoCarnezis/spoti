package com.scarnezis.spoti.service;

import com.scarnezis.spoti.persistance.dto.DeviceInDTO;
import com.scarnezis.spoti.persistance.entity.*;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import com.scarnezis.spoti.persistance.mappers.DeviceMapper;
import com.scarnezis.spoti.persistance.mappers.SongMapper;
import com.scarnezis.spoti.persistance.repository.DeviceRepository;
import com.scarnezis.spoti.persistance.repository.PlaylistRepository;
import com.scarnezis.spoti.persistance.repository.SongRepository;
import com.scarnezis.spoti.persistance.repository.UserRepository;

import java.util.Comparator;


public class DeviceService {

  private DeviceMapper mapper;
  private SongMapper songMapper;
  private DeviceRepository repository;
  private SongRepository songRepository;
  private UserRepository userRepository;
  private PlaylistRepository playlistRepository;
  private SearchEntity searcherEntity;
  private Reproductor reproductor;

  public void playSong(Long deviceId, SongId songId) throws Exception {
    Device device = searcherEntity.get(deviceId, repository);
    Song song = searcherEntity.get(songId, songRepository);
    device.setPlayingSong(song);
    _addSongToQueue(device.getPlayQueue(), song);
    device.setReproductor(reproductor);
    //device.run();
  }

  public Device createDevice(DeviceInDTO deviceInDTO){
    Device device = this.mapper.deviceInDTOToDevice(deviceInDTO);
    return this.repository.save(device);
  }

  public void shuffleQueue(Long deviceId) throws Exception {
    Device device = searcherEntity.get(deviceId, repository);
    device.getPlayQueue().shuffle();
  }

  public void addSongToQueue(Long deviceId, SongId songId) throws Exception {
    Device device = searcherEntity.get(deviceId, repository);
    Song song = searcherEntity.get(songId, songRepository);
    this._addSongToQueue(device.getPlayQueue(), song);
  }

  public void addPlaylistToQueue(Long deviceId, Long playlistId) throws Exception {
    Device device = searcherEntity.get(deviceId, repository);
    Playlist playlist = searcherEntity.get(playlistId, playlistRepository);
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
