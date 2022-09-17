package com.scarnezis.spoti.persistance.mappers;

import com.scarnezis.spoti.persistance.dto.SongInDTO;
import com.scarnezis.spoti.persistance.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDate;

@Mapper(imports = {LocalDate.class, PlayQueue.class})
public interface SongMapper {

  @Mapping(target = "releaseDate", expression = "java( LocalDate.now() ) ")
  @Mapping(target = "numberOfLikes", constant = "0")
  Song songInDTOToSong(SongInDTO songInDTO);

  @Mapping(target = "addedAt", expression = "java( LocalDate.now() ) ")
  @Mapping(source = "user", target = "addedBy")
  Track songToTrack(Song song, User user);

  @Mapping(target = "index", expression = "java( playQueue.getLastSongIndex() ) ")
  EnqueuedSong songToEnqueuedSong(Song song, PlayQueue playQueue);
}
