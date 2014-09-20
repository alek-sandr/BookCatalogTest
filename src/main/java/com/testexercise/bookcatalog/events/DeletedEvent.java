package com.testexercise.bookcatalog.events;

public class DeletedEvent {
  protected boolean entityFound = true;

  public boolean isEntityFound() {
    return entityFound;
  }
}
