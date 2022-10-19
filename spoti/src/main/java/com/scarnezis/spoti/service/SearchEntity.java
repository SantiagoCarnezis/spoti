package com.scarnezis.spoti.service;

import com.scarnezis.spoti.exceptions.AlreadyExistsElementException;
import com.scarnezis.spoti.exceptions.NoSuchElementInTableException;
import com.scarnezis.spoti.persistance.TableNames;
import com.scarnezis.spoti.persistance.entity.*;
import com.scarnezis.spoti.persistance.entity.id.PlaylistId;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import com.scarnezis.spoti.persistance.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Component
public class SearchEntity {

  private final UserRepository userRepository;
  private final SongRepository songRepository;
  private final DeviceRepository deviceRepository;
  private final ArtistRepository artistRepository;
  private final PlaylistRepository playlistRepository;

  public User getUser(Long userId) throws NoSuchElementInTableException {
    return this.get(userId, userRepository, TableNames.USER);
  }

  public Device getDevice(Long deviceId) throws NoSuchElementInTableException {
    return this.get(deviceId, deviceRepository, TableNames.DEVICE);
  }

  public Artist getArtist(String name) throws NoSuchElementInTableException {
    return this.get(name, artistRepository, TableNames.ARTIST);
  }

  public Playlist getPlaylist(PlaylistId playlistId) throws NoSuchElementInTableException {
    return this.get(playlistId, playlistRepository, TableNames.PLAYLIST);
  }

  public Song getSong(SongId songId) throws NoSuchElementInTableException {
    return this.get(songId, songRepository, TableNames.SONG);
  }

  public void validateExistsArtist(String artistName) throws AlreadyExistsElementException {
    this._validateExists(artistName, artistRepository, TableNames.ARTIST);
  }

  public void validateExistsPlaylist(PlaylistId playlistId) throws AlreadyExistsElementException {
    this._validateExists(playlistId, playlistRepository, TableNames.PLAYLIST);
  }

  public void validateExistsSong(SongId songId) throws AlreadyExistsElementException {
    this._validateExists(songId, songRepository, TableNames.SONG);
  }

  private <E, ID> E get(ID id, JpaRepository<E, ID> repository, String tableName)
      throws NoSuchElementInTableException {

    String message = String.format("No such %s with id %s", tableName, id.toString());
    return repository
        .findById(id)
        .orElseThrow(() -> new NoSuchElementInTableException(message));
  }

  private <E, ID> void _validateExists(ID id, JpaRepository<E, ID> repository, String tableName)
      throws NoSuchElementInTableException {

    String message = String.format("Already exists a %s with id %s", tableName, id.toString());
    if( repository.existsById(id))
      throw new AlreadyExistsElementException(message);
  }
}
