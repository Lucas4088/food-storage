package com.wat.foodmanager;

import com.foodmanager.guiport.UnitFrontendService;
import com.wat.foodmanager.jpa.repoport.UnitRepositoryService;
import com.wat.foodmanager.model.Exceptions.UnableToReadEmbededFileException;
import com.wat.foodmanager.model.Unit;
import com.wat.foodmanager.model.utils.XmlFileReader;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UnitServiceCore implements UnitFrontendService {

    private static final Logger logger = Logger.getLogger(CategoryServiceCore.class);

    private UnitRepositoryService unitRepositoryService;

    public UnitServiceCore(UnitRepositoryService unitRepositoryService) {
        this.unitRepositoryService = unitRepositoryService;

        XmlFileReader xmlFileReader = new XmlFileReader("units.xml");

        try {
            unitRepositoryService.addAllUnits(xmlFileReader.readFile());
        } catch (UnableToReadEmbededFileException unableToReadEmbededFileException) {
            logger.error("Unable to read units dictionary", unableToReadEmbededFileException);
        }
    }

    @Override
    public List<Unit> listUnits() {
        return unitRepositoryService.listUnits().join();
    }

    @Override
    public Unit getUnit(String name) {
        return unitRepositoryService.getUnit(name).join();
    }
}
