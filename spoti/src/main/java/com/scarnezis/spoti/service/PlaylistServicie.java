package com.scarnezis.spoti.service;

import com.scarnezis.spoti.exceptions.NoSuchElementInTableException;
import com.scarnezis.spoti.persistance.dto.PlaylistInDTO;
import com.scarnezis.spoti.persistance.entity.Playlist;
import com.scarnezis.spoti.persistance.entity.Song;
import com.scarnezis.spoti.persistance.entity.Track;
import com.scarnezis.spoti.persistance.entity.User;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import com.scarnezis.spoti.persistance.mappers.PlaylistMapper;
import com.scarnezis.spoti.persistance.mappers.SongMapper;
import com.scarnezis.spoti.persistance.repository.PlaylistRepository;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
public class PlaylistServicie {

  private PlaylistRepository playlistRepository;
  private PlaylistMapper playlistMapper;
  private SongMapper songMapper;
  private SearchEntity searcherEntity;

  @Transactional
  public Playlist createPlaylist(PlaylistInDTO playlistInDTO, Long userId) throws NoSuchElementInTableException {
    User user = searcherEntity.getUser(userId);
    Playlist playlist = this.playlistMapper.playlistInDTOToPlaylist(playlistInDTO);
    user.addPlaylist(playlist);
    return this.playlistRepository.save(playlist);
  }

  @Transactional
  public void deletePlaylist(PlaylistInDTO playlistInDTO, Long userId) throws NoSuchElementInTableException {
    User user = searcherEntity.getUser(userId);
    Playlist playlist = this.playlistMapper.playlistInDTOToPlaylist(playlistInDTO);
    user.removePlaylist(playlist);
    this.playlistRepository.delete(playlist);
  }

  public List<Playlist> findAllByUser(Long userId){
    return this.playlistRepository.findAllByUser(userId);
  }

  @Transactional
  public void addSong(SongId songId, Long playlistId, Long userId) throws NoSuchElementInTableException {
    Playlist playlist = searcherEntity.getPlaylist(playlistId);
    Track track = _getTrack(songId, userId);
    playlist.addSong(track);
  }

  @Transactional
  public void removeSong(SongId songId, Long playlistId, Long userId) throws NoSuchElementInTableException {
    Playlist playlist = searcherEntity.getPlaylist(playlistId);
    Track track = _getTrack(songId, userId);
    playlist.removeSong(track);
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
