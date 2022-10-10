package com.scarnezis.spoti.service;

import com.scarnezis.spoti.exceptions.NoSuchElementInTableException;
import com.scarnezis.spoti.persistance.dto.PlaylistInDTO;
import com.scarnezis.spoti.persistance.entity.Playlist;
import com.scarnezis.spoti.persistance.entity.Song;
import com.scarnezis.spoti.persistance.entity.Track;
import com.scarnezis.spoti.persistance.entity.User;
import com.scarnezis.spoti.persistance.entity.id.PlaylistId;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import com.scarnezis.spoti.persistance.mappers.PlaylistMapper;
import com.scarnezis.spoti.persistance.mappers.SongMapper;
import com.scarnezis.spoti.persistance.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Service
public class PlaylistService {

  private final PlaylistRepository playlistRepository;
  private final PlaylistMapper playlistMapper;
  private final SongMapper songMapper;
  private final SearchEntity searcherEntity;

  @Transactional
  public Playlist createPlaylist(PlaylistInDTO playlistInDTO, Long userId) throws NoSuchElementInTableException {
    User user = searcherEntity.getUser(userId);
    //playlistInDTO.setUser(user);
    Playlist playlist = this.playlistMapper.playlistInDTOToPlaylist(playlistInDTO);
    playlist.setUser(user);
    playlist.setUser_id(user.getId());
    //user.addPlaylist(playlist);
    Playlist playlistPersisted = this.playlistRepository.save(playlist);
    return playlistPersisted;
    //TODO no lo persiste, creo q hay un problema de recursisvidad con los setters
  }

  @Transactional
  public void deletePlaylist(PlaylistId playlistId) throws NoSuchElementInTableException {
    Playlist playlist = this.searcherEntity.getPlaylist(playlistId);
    User user = searcherEntity.getUser(playlistId.getUser_id());
    this.playlistRepository.delete(playlist);
  }

  public List<Playlist> findAllByUser(Long userId){
    return this.playlistRepository.findAllByUser_id(userId);
  }

  @Transactional
  public void addSong(SongId songId, PlaylistId playlistId) throws NoSuchElementInTableException {
    Playlist playlist = searcherEntity.getPlaylist(playlistId);
    Track track = _getTrack(songId, playlistId.getUser_id());
    playlist.addSong(track);
    this.playlistRepository.save(playlist);
  }

  @Transactional
  public void removeSong(SongId songId, PlaylistId playlistId) throws NoSuchElementInTableException {
    Playlist playlist = searcherEntity.getPlaylist(playlistId);
    Track track = _getTrack(songId, playlistId.getUser_id());
    playlist.removeSong(track);
    this.playlistRepository.save(playlist);
  }

  public List<Playlist> findAllByName(String playlistName){
    return this.playlistRepository.findAllByNameContaining(playlistName);
  }

  private Track _getTrack(SongId songId, Long userId) throws NoSuchElementInTableException {
    User user = searcherEntity.getUser(userId);
    Song song = searcherEntity.getSong(songId);
    return this.songMapper.songToTrack(song, user);
  }
}
