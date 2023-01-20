package com.scarnezis.spoti.controllers;

import com.scarnezis.spoti.controller.SongController;
import com.scarnezis.spoti.domain.Artist;
import com.scarnezis.spoti.domain.Gender;
import com.scarnezis.spoti.domain.Song;
import com.scarnezis.spoti.service.SongService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = SongController.class)
public class SongControllerTests {

  @MockBean
  private SongService songService;
  @Autowired
  private MockMvc mockMvc;
  private Artist survivor;
  private Artist acdc;
  private Song eyeOfTheTiger;
  private Song burningHeart;
  private Song backInBlack;
  private Song highwayToTiger;
  private List<Song> allSongs;

  @BeforeEach
  public void setUp(){

    survivor = new Artist("Survivor",
        LocalDate.of(1978, Month.JANUARY, 1), LocalDate.now());

    acdc = new Artist("AC/DC",
        LocalDate.of(1973, Month.JANUARY, 1), LocalDate.now());

    eyeOfTheTiger = new Song("Eye of the Tiger", survivor.getName(),
        survivor, Gender.ROCK, Duration.ofSeconds(100), LocalDate.now(), 1) ;

    burningHeart = new Song("Burning Heart", survivor.getName(),
        survivor, Gender.ROCK, Duration.ofSeconds(100), LocalDate.now(), 0);

    highwayToTiger = new Song("Highway to Tiger", acdc.getName(),
        acdc, Gender.ROCK, Duration.ofSeconds(100), LocalDate.now(), 0) ;

    backInBlack = new Song("Back in Black", acdc.getName(),
        acdc, Gender.ROCK, Duration.ofSeconds(100), LocalDate.now(), 0);

    allSongs = Arrays.asList(eyeOfTheTiger, burningHeart, highwayToTiger, backInBlack);
  }


  @Test
  public void getAllSongs() throws Exception {

    Mockito.when(songService.findAll()).thenReturn(allSongs);

    mockMvc.perform(get("/song/"))
        .andExpect(status().is(200))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.size()", Matchers.is(4)))
        .andExpect(jsonPath("$[0].name", Matchers.is(eyeOfTheTiger.getName())))
        .andExpect(jsonPath("$[1].name", Matchers.is(burningHeart.getName())))
        .andExpect(jsonPath("$[2].name", Matchers.is(highwayToTiger.getName())))
        .andExpect(jsonPath("$[3].name", Matchers.is(backInBlack.getName())));
  }

  @Test
  public void getAllSongsFilterBySong() throws Exception {

    Mockito.when(songService.findAllByName("Tiger"))
        .thenReturn(allSongs.stream().filter(
            song -> song.getName().contains("Tiger"))
            .collect(Collectors.toList()));

    mockMvc.perform(get("/song?name=Tiger"))
        .andExpect(status().is(200))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.size()", Matchers.is(2)))
        .andExpect(jsonPath("$[0].name", Matchers.is(eyeOfTheTiger.getName())))
        .andExpect(jsonPath("$[1].name", Matchers.is(highwayToTiger.getName())));
  }

  @Test
  public void getAllSongsFilterByArtist() throws Exception {

    Mockito.when(songService.findAllByArtist("Survivor"))
        .thenReturn(allSongs.stream().filter(
            song -> song.getArtist_name().equalsIgnoreCase("Survivor"))
            .collect(Collectors.toList()));

    mockMvc.perform(get("/song?artist=Survivor"))
        .andExpect(status().is(200))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.size()", Matchers.is(2)))
        .andExpect(jsonPath("$[0].name", Matchers.is(eyeOfTheTiger.getName())))
        .andExpect(jsonPath("$[0].artist_name", Matchers.is(eyeOfTheTiger.getArtist_name())))
        .andExpect(jsonPath("$[1].name", Matchers.is(burningHeart.getName())))
        .andExpect(jsonPath("$[1].artist_name", Matchers.is(burningHeart.getArtist_name())));
  }

  @Test
  public void getAllSongsFilterBySongAndArtist() throws Exception {

    Mockito.when(songService.findAllByNameAndArtist("Tiger", "Survivor", 0, 5))
        .thenReturn(allSongs.stream().filter(
            song -> song.getArtist_name().equalsIgnoreCase("Survivor")
                && song.getName().contains("Tiger") )
            .collect(Collectors.toList()));

    mockMvc.perform(get("/song?name=Tiger&artist=Survivor"))
        .andExpect(status().is(200))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.size()", Matchers.is(1)))
        .andExpect(jsonPath("$[0].name", Matchers.is(eyeOfTheTiger.getName())))
        .andExpect(jsonPath("$[0].artist_name", Matchers.is(eyeOfTheTiger.getArtist_name())));
  }

  @Test
  public void whenSongIsLikedIncreaseCountOfLikes() throws Exception {

    Song eyeOfTheTigerIncreased = eyeOfTheTiger;
    eyeOfTheTigerIncreased.setLikesCounter(eyeOfTheTigerIncreased.getLikesCounter() + 1);

    Mockito.when(songService.likeSong("Eye of the Tiger", "Survivor"))
        .thenReturn(eyeOfTheTigerIncreased);

    mockMvc.perform(patch("/song/Eye of the Tiger/artist/Survivor/like"))
        .andExpect(status().is(200))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name", Matchers.is(eyeOfTheTigerIncreased.getName())))
        .andExpect(jsonPath("$.likesCounter", Matchers.is(2)));
  }

  @Test
  public void whenSongIsDislikedDecreaseCountOfLikes() throws Exception {

    Song eyeOfTheTigerDecreased = eyeOfTheTiger;
    eyeOfTheTigerDecreased.setLikesCounter(eyeOfTheTigerDecreased.getLikesCounter() - 1);

    Mockito.when(songService.quitLikeSong("Eye of the Tiger", "Survivor"))
        .thenReturn(eyeOfTheTigerDecreased);

    mockMvc.perform(patch("/song/Eye of the Tiger/artist/Survivor/dislike"))
        .andExpect(status().is(200))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name", Matchers.is("Eye of the Tiger")))
        .andExpect(jsonPath("$.likesCounter", Matchers.is(0)));
  }


}
