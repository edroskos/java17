package net.edroskos.spaceecon.model;

public record Lightyears(Double value) implements Comparable<Lightyears> {
  @Override
  public int compareTo(Lightyears that) {
    return value.compareTo(that.value);
  }
}
