package com.scarnezis.spoti.service;

import com.scarnezis.spoti.exceptions.NoSuchElementInTableException;
import com.scarnezis.spoti.persistance.dto.UserInDTO;
import com.scarnezis.spoti.persistance.entity.Device;
import com.scarnezis.spoti.persistance.entity.User;
import com.scarnezis.spoti.persistance.mappers.UserMapper;
import com.scarnezis.spoti.persistance.repository.DeviceRepository;
import com.scarnezis.spoti.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Service
public class UserService {

  private final UserRepository userRepository;
  private final DeviceRepository deviceRepository;
  private final UserMapper userMapper;
  private final SearchEntity searcherEntity;

  public User signIn(UserInDTO userInDTO) throws NoSuchElementInTableException {
    User user = this.userMapper.userInDTOToUser(userInDTO);
    return this.userRepository.save(user);
  }

  @Transactional
  public void logIn(Long deviceId, Long userId) throws NoSuchElementInTableException {
    User user = searcherEntity.getUser(userId);
    this.deviceRepository.logOut(userId);
    Device device = searcherEntity.getDevice(deviceId);
    device.setUser(user);
    this.deviceRepository.save(device);
  }

  public void logOut(Long deviceId) throws NoSuchElementInTableException {
    Device device = searcherEntity.getDevice(deviceId);
    device.logOut();
    this.deviceRepository.save(device);
  }
}
