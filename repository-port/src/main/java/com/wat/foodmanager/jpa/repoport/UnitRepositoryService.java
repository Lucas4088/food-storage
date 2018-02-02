package com.wat.foodmanager.jpa.repoport;

import com.wat.foodmanager.model.Unit;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UnitRepositoryService {
    CompletableFuture<List<Unit>> listUnits();

    CompletableFuture<Unit> getUnit(String name);

    void addAllUnits(List<Unit> units);
}
