package com.testexercise.bookcatalog.events;

public class UpdatedEvent {
  protected boolean entityFound = true;

  public boolean isEntityFound() {
    return entityFound;
  }
}
