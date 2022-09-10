package com.scarnezis.spoti.service;

import com.scarnezis.spoti.persistance.dto.SongInDTO;
import com.scarnezis.spoti.persistance.entity.Song;
import com.scarnezis.spoti.persistance.mappers.SongMapper;
import com.scarnezis.spoti.persistance.repository.ArtistRepository;
import com.scarnezis.spoti.persistance.repository.SongRepository;

import java.util.List;

public class SongService {

  private final SongMapper mapper;
  private final SongRepository songRepository;
  private final ArtistRepository artistRepository;

  public SongService(SongMapper mapper, SongRepository songRepository, ArtistRepository artistRepository) {
    this.mapper = mapper;
    this.songRepository = songRepository;
    this.artistRepository = artistRepository;
  }

  public Song createSong(SongInDTO songInDTO){
    //TODO the artist must exist and the song must not exist yet
    Song song = this.mapper.songInDTOToSong(songInDTO);
    return this.songRepository.save(song);
  }

  public List<Song> findAllByName(String songName){
    return this.songRepository.findAllByName(songName);
  }

  public List<Song> findAllByArtist(String artistName){
    return this.songRepository.findAllByArtist(artistName);
  }

  public void likeSong(Long songId){
    //TODO
  }

  public void quitLikeSong(Long songId){
    //TODO
  }
}
