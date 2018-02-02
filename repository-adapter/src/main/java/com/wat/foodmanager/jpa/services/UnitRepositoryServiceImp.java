package com.wat.foodmanager.jpa.services;

import com.wat.foodmanager.jpa.repoport.UnitRepositoryService;
import com.wat.foodmanager.jpa.repositories.UnitRepository;
import com.wat.foodmanager.jpa.utils.Mapper;
import com.wat.foodmanager.model.Unit;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class UnitRepositoryServiceImp implements UnitRepositoryService {

    private UnitRepository unitRepository;

    public UnitRepositoryServiceImp(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Async
    public CompletableFuture<List<Unit>> listUnits() {
        return CompletableFuture.completedFuture(unitRepository.findAll()
                .stream()
                .map(Mapper::map)
                .collect(Collectors.toList()));
    }

    @Override
    @Async
    public CompletableFuture<Unit> getUnit(String name) {
        return CompletableFuture.completedFuture(Mapper.map(unitRepository.getByUnitName(name)));
    }

    @Override
    @Async
    public void addAllUnits(List<Unit> units) {
        units.forEach(unit -> unitRepository.save(Mapper.map(unit)));
    }
}
