package net.edroskos.spaceecon.model;

public record WarpFactor(Double value) implements Comparable<WarpFactor> {
  @Override
  public int compareTo(WarpFactor that) {
    return value.compareTo(that.value);
  }
}
