package by.peregud.rzd.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum WagonType {
    SEMI_WAGON1("6-axle all-metal open-top wagon", 94_000, 31_000),
    SEMI_WAGON2("4-axle all-metal open-top wagon, model 12-1000", 69_000, 22_000),
    COVERED1("4-axle covered wagon (with metal end wall), model 11-066", 66_000, 23_000),
    COVERED2("4-axle all-metal covered wagon", 68_000, 22_880),
    COVERED3("4-axle covered wagon, model 11-274", 50_000, 35_000);



    private final String nameType;
    private final int loadCapacity;
    private final int tareWeight;
}


