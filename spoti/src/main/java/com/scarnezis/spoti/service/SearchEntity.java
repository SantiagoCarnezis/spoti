package com.scarnezis.spoti.service;

import com.scarnezis.spoti.exceptions.NoSuchElementInTableException;
import com.scarnezis.spoti.persistance.TableNames;
import com.scarnezis.spoti.persistance.entity.*;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import com.scarnezis.spoti.persistance.repository.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class SearchEntity {

  private UserRepository userRepository;
  private SongRepository songRepository;
  private DeviceRepository deviceRepository;
  private ArtistRepository artistRepository;
  private PlaylistRepository playlistRepository;

  public User getUser(Long userId) throws NoSuchElementInTableException {
    return this.get(userId, userRepository, TableNames.USER);
  }

  public Device getDevice(Long deviceId) throws NoSuchElementInTableException {
    return this.get(deviceId, deviceRepository, TableNames.DEVICE);
  }

  public Artist getArtist(String name) throws NoSuchElementInTableException {
    return this.get(name, artistRepository, TableNames.ARTIST);
  }

  public Playlist getPlaylist(Long playlistId) throws NoSuchElementInTableException {
    return this.get(playlistId, playlistRepository, TableNames.PLAYLIST);
  }

  public Song getSong(SongId songId) throws NoSuchElementInTableException {
    return this.get(songId, songRepository, TableNames.SONG);
  }

  private <E, ID> E get(ID id, JpaRepository<E, ID> repository, String tableName)
      throws NoSuchElementInTableException {
    Optional<E> optional = repository.findById(id);
    if(!optional.isPresent()){
      String message = String.format("No se encontro el id %s en la tabla %s", id.toString(), tableName);
      throw new NoSuchElementInTableException(message);
    }
    return optional.get();
  }
}
