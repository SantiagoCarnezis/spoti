package com.scarnezis.spoti.service;

import com.scarnezis.spoti.persistance.dto.SongInDTO;
import com.scarnezis.spoti.persistance.entity.Song;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import com.scarnezis.spoti.persistance.mappers.SongMapper;
import com.scarnezis.spoti.persistance.repository.ArtistRepository;
import com.scarnezis.spoti.persistance.repository.SongRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

  private final SongMapper mapper;
  private final SongRepository songRepository;
  private final ArtistRepository artistRepository;

  public SongService(SongMapper mapper, SongRepository songRepository, ArtistRepository artistRepository) {
    this.mapper = mapper;
    this.songRepository = songRepository;
    this.artistRepository = artistRepository;
  }

  @Transactional
  public Song createSong(SongInDTO songInDTO) throws Exception {
    if(!this.artistRepository.existsById(songInDTO.getArtist().getName()))
      throw new Exception();
    Song song = this.mapper.songInDTOToSong(songInDTO);
    return this.songRepository.save(song);
  }

  public List<Song> findAllByName(String songName){
    return this.songRepository.findAllByNameContaining(songName);
  }

  public List<Song> findAllByArtist(String artistName){
    return this.songRepository.findAllByArtist(artistName);
  }

  public void likeSong(SongId songId) throws Exception {
    _setLikeSong(songId, 1);
  }

  public void dislikeSong(SongId songId) throws Exception {
    _setLikeSong(songId, -1);
  }

  @Transactional
  private void _setLikeSong(SongId songId, Integer number) throws Exception {
    Optional<Song> optionalSong = this.songRepository.findById(songId);
    if(optionalSong.isPresent())
      throw new Exception();
    Song song = optionalSong.get();
    this.songRepository.setSongLike(song.getName(), song.getArtist(), song.getNumberOfLikes() + number);
  }
}
