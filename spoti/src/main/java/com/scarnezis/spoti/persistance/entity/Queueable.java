package com.scarnezis.spoti.persistance.entity;

import java.util.Collection;

public interface Queueable {

  Collection<Song> getSongsForQueue();
}
