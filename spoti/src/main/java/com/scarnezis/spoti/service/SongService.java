package com.scarnezis.spoti.service;

import com.scarnezis.spoti.exceptions.NoSuchElementInTableException;
import com.scarnezis.spoti.persistance.dto.SongInDTO;
import com.scarnezis.spoti.persistance.entity.Artist;
import com.scarnezis.spoti.persistance.entity.Song;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import com.scarnezis.spoti.persistance.mappers.SongMapper;
import com.scarnezis.spoti.persistance.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Setter
@Service
public class SongService {

  private final SongMapper mapper;
  private final SongRepository songRepository;
  private final SearchEntity searcherEntity;

  @Transactional
  public Song createSong(SongInDTO songInDTO, String artistName) {
    Artist artist = searcherEntity.getArtist(artistName);
    songInDTO.setArtist(artist);
    Song song = this.mapper.songInDTOToSong(songInDTO);
    searcherEntity.validateExistsSong(song.getSong_id());
    this.songRepository.save(song);
    return song;
  }

  public List<Song> findAllByName(String songName){
    return this.songRepository.findAllByNameContaining(songName);
  }

  public List<Song> findAllByArtist(String artistName) {
    return this.songRepository.findAllByArtist_nameContaining(artistName);
  }

  public List<Song> findAllByNameAndArtist(String songName, String artistName){
    return this.songRepository.findAllByNameAndArtist_name(songName, artistName);
  }

  public List<Song> findAll(){
    return this.songRepository.findAll();
  }

  public Song likeSong(String songName, String artistName) throws NoSuchElementInTableException{
    return _setLikeSong(songName, artistName, 1);
  }

  public Song quitLikeSong(String songName, String artistName) throws NoSuchElementInTableException{
    return _setLikeSong(songName, artistName,-1);
  }

  @Transactional
  private Song _setLikeSong(String songName, String artistName, Integer number) throws NoSuchElementInTableException {
    SongId songId = new SongId(songName, artistName);
    Song song = this.searcherEntity.getSong(songId);
    int likes = Math.max(song.getNumberOfLikes() + number, 0);
    song.setNumberOfLikes(likes);
    return this.songRepository.save(song);
  }
}
