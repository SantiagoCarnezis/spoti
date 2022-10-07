package com.scarnezis.spoti.service;

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

import java.util.Optional;

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
