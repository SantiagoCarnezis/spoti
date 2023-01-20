package com.scarnezis.spoti.service;

import com.scarnezis.spoti.exceptions.ResourceAlreadyExistsException;
import com.scarnezis.spoti.exceptions.ResourceNotFoundException;
import com.scarnezis.spoti.dto.UserInDTO;
import com.scarnezis.spoti.domain.User;
import com.scarnezis.spoti.mappers.UserMapper;
import com.scarnezis.spoti.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Service
public class UserService {

  private final UserRepository repository;
  private final UserMapper userMapper;

  public User get(Long userId) {
    return repository
        .findById(userId)
        .orElseThrow(() ->
            new ResourceNotFoundException("User " + userId));
  }

  public User signIn(UserInDTO userInDTO) throws ResourceNotFoundException {
    User user = this.userMapper.userInDTOToUser(userInDTO);
    return this.repository.save(user);
  }
}
