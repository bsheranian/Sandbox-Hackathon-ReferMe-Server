package service;

import java.io.IOException;

public abstract class ServiceTemplate<T, V> {
  public abstract V doRequest(T request);
}