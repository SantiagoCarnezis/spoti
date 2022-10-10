package com.scarnezis.spoti.persistance.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInDTO {

  private String name;
  private String email;
  private LocalDate birthday;
}
