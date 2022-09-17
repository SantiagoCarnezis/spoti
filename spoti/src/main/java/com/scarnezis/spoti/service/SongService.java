package com.scarnezis.spoti.service;

import com.scarnezis.spoti.persistance.dto.SongInDTO;
import com.scarnezis.spoti.persistance.entity.Artist;
import com.scarnezis.spoti.persistance.entity.Song;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import com.scarnezis.spoti.persistance.mappers.SongMapper;
import com.scarnezis.spoti.persistance.repository.ArtistRepository;
import com.scarnezis.spoti.persistance.repository.SongRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Service
public class SongService {

  private final SongMapper mapper;
  private final SongRepository songRepository;
  private final ArtistRepository artistRepository;
  private final SearchEntity searcherEntity;

  public SongService(SongMapper mapper,
                     SongRepository songRepository,
                     ArtistRepository artistRepository,
                     SearchEntity searcherEntity) {
    this.mapper = mapper;
    this.songRepository = songRepository;
    this.artistRepository = artistRepository;
    this.searcherEntity = searcherEntity;
  }

  @Transactional
  public Song createSong(SongInDTO songInDTO) throws Exception {
    String artistName = songInDTO.getArtist().getName();
    _validateArtist(artistName);
    Song song = this.mapper.songInDTOToSong(songInDTO);
    return this.songRepository.save(song);
  }

  public List<Song> findAllByName(String songName){
    return this.songRepository.findAllByNameContaining(songName);
  }

  public List<Song> findAllByArtist(Artist artist) throws Exception {
    _validateArtist(artist.getName());
    return this.songRepository.findAllByArtist(artist);
  }

  public void likeSong(SongId songId) throws Exception {
    _setLikeSong(songId, 1);
  }

  public void dislikeSong(SongId songId) throws Exception {
    _setLikeSong(songId, -1);
  }

  private void _validateArtist(String artistName) throws Exception {
    if(!this.artistRepository.existsById(artistName))
      throw new Exception();
  }

  @Transactional
  private void _setLikeSong(SongId songId, Integer number) throws Exception {
    Song song = this.searcherEntity.get(songId, songRepository);
    this.songRepository.setSongLike(song.getName(), song.getArtist(), song.getNumberOfLikes() + number);
  }
}
