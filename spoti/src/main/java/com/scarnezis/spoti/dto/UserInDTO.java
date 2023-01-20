package com.scarnezis.spoti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInDTO {

  private String name;
  private String email;
  private LocalDate birthday;
}
