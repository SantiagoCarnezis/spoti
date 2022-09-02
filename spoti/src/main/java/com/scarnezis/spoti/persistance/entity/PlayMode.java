package com.scarnezis.spoti.persistance.entity;

import java.util.Collections;
import java.util.List;

public enum PlayMode {
  RANDOM{
    @Override
    public PlayQueue createQueue(List songs) {
      Collections.sort(songs);
      return new PlayQueue(songs);
    }
  },
  NORMAL{
    @Override
    public PlayQueue createQueue(List songs) {
      return new PlayQueue(songs);
    }
  };

  public abstract PlayQueue createQueue(List songs);
}
