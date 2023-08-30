package com.mjc.stage2;

public class ThreadSafeSingleton {
  private static ThreadSafeSingleton instance;

  public static synchronized ThreadSafeSingleton getInstance() {
    if (instance == null) {
      instance = new ThreadSafeSingleton();
    }
    return instance;
  }
}
