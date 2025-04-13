package by.peregud.rzd.enums;

import javax.persistence.AttributeConverter;

public class WagonTypeConverter implements AttributeConverter<WagonType, String> {


    @Override
    public String convertToDatabaseColumn(WagonType attribute) {
        return attribute.name() + " | " + attribute.getNameType() + " | " + attribute.getTareWeight() + " | " + attribute.getLoadCapacity();
    }

    @Override
    public WagonType convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return null;

        String[] parts = dbData.split(" \\| ");
        String enumName = parts[0].trim();

        return WagonType.valueOf(enumName);


    }
}
