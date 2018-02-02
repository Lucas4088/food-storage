package com.foodmanager.guiport;

import com.wat.foodmanager.model.Unit;

import java.util.List;

public interface UnitFrontendService {

    List<Unit> listUnits();

    Unit getUnit(String name);
}
