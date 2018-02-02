package gui.services;

import com.foodmanager.guiport.UnitFrontendService;
import com.wat.foodmanager.model.Unit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {

    private UnitFrontendService unitFrontendService;

    public UnitService(UnitFrontendService unitFrontendService) {
        this.unitFrontendService = unitFrontendService;
    }

    public List<Unit> listUnits() {
        return unitFrontendService.listUnits();
    }

    public Unit getUnit(String name) {
        return unitFrontendService.getUnit(name);
    }
}
