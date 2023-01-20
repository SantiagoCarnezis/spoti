package com.scarnezis.spoti.service;

import com.scarnezis.spoti.exceptions.ResourceAlreadyExistsException;
import com.scarnezis.spoti.exceptions.ResourceNotFoundException;
import com.scarnezis.spoti.dto.SongInDTO;
import com.scarnezis.spoti.domain.Artist;
import com.scarnezis.spoti.domain.Song;
import com.scarnezis.spoti.domain.id.SongId;
import com.scarnezis.spoti.mappers.SongMapper;
import com.scarnezis.spoti.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Setter
@Service
public class SongService {

  private final SongMapper mapper;
  private final SongRepository repository;
  private final ArtistService artistService;

  public Song get(SongId songId) {
    return repository
        .findById(songId)
        .orElseThrow(() ->
            new ResourceNotFoundException("Song " + songId));
  }

  public void validateExists(SongId songId) {
    if( repository.existsById(songId))
      throw new ResourceAlreadyExistsException(songId.toString());
  }

  @Transactional
  public Song createSong(SongInDTO songInDTO, String artistName) {
    Artist artist = artistService.get(artistName);
    songInDTO.setArtist(artist);
    Song song = this.mapper.songInDTOToSong(songInDTO);
    this.validateExists(song.getSong_id());
    return this.repository.save(song);
  }

  public Page<Song> findAllByName(String songName, Integer offset, Integer pageSize){
    return this.repository.findAllByNameContaining(songName, PageRequest.of(offset, pageSize));
  }

  public Page<Song> findAllByArtist(String artistName, Integer offset, Integer pageSize) {
    return this.repository.findAllByArtist_nameContaining(artistName, PageRequest.of(offset, pageSize));
  }

  public Page<Song> findAllByNameAndArtist(String songName, String artistName, Integer offset, Integer pageSize){
    return this.repository.findAllByNameAndArtist_name(songName, artistName, PageRequest.of(offset, pageSize));
  }

  public Page<Song> findAll(Integer offset, Integer pageSize){
    return this.repository.findAll(PageRequest.of(offset, pageSize));
  }

  public Song likeSong(String songName, String artistName) throws ResourceNotFoundException {
    return _setLikeSong(songName, artistName, 1);
  }

  public Song quitLikeSong(String songName, String artistName) throws ResourceNotFoundException {
    return _setLikeSong(songName, artistName,-1);
  }

  @Transactional
  private Song _setLikeSong(String songName, String artistName, Integer number) throws ResourceNotFoundException {
    Song song = this.get(new SongId(songName, artistName));
    int likes = Math.max(song.getLikesCounter() + number, 0);
    this.repository.setSongLike(songName, artistName, likes);
    return song;
  }
}
