package com.scarnezis.spoti.service;

import com.scarnezis.spoti.exceptions.ResourceAlreadyExistsException;
import com.scarnezis.spoti.exceptions.ResourceNotFoundException;
import com.scarnezis.spoti.dto.PlaylistInDTO;
import com.scarnezis.spoti.domain.Playlist;
import com.scarnezis.spoti.domain.Song;
import com.scarnezis.spoti.domain.Track;
import com.scarnezis.spoti.domain.User;
import com.scarnezis.spoti.domain.id.PlaylistId;
import com.scarnezis.spoti.domain.id.SongId;
import com.scarnezis.spoti.mappers.PlaylistMapper;
import com.scarnezis.spoti.mappers.SongMapper;
import com.scarnezis.spoti.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Service
public class PlaylistService {

  private final PlaylistRepository repository;
  private final PlaylistMapper playlistMapper;
  private final SongMapper songMapper;
  private final UserService userService;
  private final SongService songService;

  public Playlist get(PlaylistId playlistId){
    return repository
        .findById(playlistId)
        .orElseThrow(() ->
            new ResourceNotFoundException("Playlist " + playlistId));
  }

  public List<Track> getTracks(PlaylistId playlistId){
    return (List<Track>) this.get(playlistId).getTracks();
  }

  public void validateExists(PlaylistId playlistId) {
    if( repository.existsById(playlistId))
      throw new ResourceAlreadyExistsException(playlistId.toString());
  }

  @Transactional
  public Playlist createPlaylist(PlaylistInDTO playlistInDTO, Long userId)
      throws ResourceNotFoundException, ResourceAlreadyExistsException {
    User user = userService.get(userId);
    Playlist playlist = this.playlistMapper.playlistInDTOToPlaylist(playlistInDTO);
    playlist.setUser(user);
    this.validateExists(playlist.getPlaylistId());
    return this.repository.save(playlist);
  }

  @Transactional
  public void deletePlaylist(PlaylistId playlistId) throws ResourceNotFoundException {
    Playlist playlist = this.get(playlistId);
    User user = userService.get(playlistId.getUser_id());
    this.repository.delete(playlist);
  }

  @Transactional
  public void addSong(SongId songId, PlaylistId playlistId) throws ResourceNotFoundException {
    Playlist playlist = this.get(playlistId);
    Track track = _getTrack(songId, playlistId.getUser_id());
    playlist.addSong(track);
    this.repository.save(playlist);
  }

  @Transactional
  public void removeSong(SongId songId, PlaylistId playlistId) throws ResourceNotFoundException {
    Playlist playlist = this.get(playlistId);
    Track track = _getTrack(songId, playlistId.getUser_id());
    playlist.removeSong(track);
    this.repository.save(playlist);
  }

  public Page<Playlist> findAllByName(String playlistName, Integer offset, Integer pageSize){
    return this.repository.findAllByNameContaining(playlistName, PageRequest.of(offset, pageSize));
  }

  public Page<Playlist> findAll(Integer offset, Integer pageSize){
    return this.repository.findAll(PageRequest.of(offset, pageSize));
  }

  private Track _getTrack(SongId songId, Long userId) throws ResourceNotFoundException {
    User user = userService.get(userId);
    Song song = songService.get(songId);
    return this.songMapper.songToTrack(song, user);
  }
}
