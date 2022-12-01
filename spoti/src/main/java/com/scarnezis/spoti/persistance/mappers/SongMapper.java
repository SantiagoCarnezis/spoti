package com.scarnezis.spoti.persistance.mappers;

import com.scarnezis.spoti.persistance.dto.SongInDTO;
import com.scarnezis.spoti.persistance.entity.*;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    imports = {LocalDate.class, PlayQueue.class, SongInDTO.class})
public interface SongMapper {

  @Mapping(target = "releaseDate", expression = "java( LocalDate.now() ) ")
  @Mapping(target = "countOfLikes", constant = "0")
  @Mapping(target = "artist_name", expression = "java( songInDTO.getArtist().getName() ) ")
  Song songInDTOToSong(SongInDTO songInDTO);

  @Mapping(target = "addedAt", expression = "java( LocalDate.now() ) ")
  @Mapping(source = "user", target = "addedBy")
  Track songToTrack(Song song, User user);

  @Mapping(target = "index", expression = "java( playQueue.getLastSongIndex() ) ")
  EnqueuedSong songToEnqueuedSong(Song song, PlayQueue playQueue);
}
