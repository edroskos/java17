package net.edroskos.spaceecon.model;

import com.google.common.collect.ImmutableSortedMap;

public record Starship(
    Government designer,
    String name,
    ImmutableSortedMap<Lightyears, WarpFactor> warpDrive,
    Cost cost

) {}
