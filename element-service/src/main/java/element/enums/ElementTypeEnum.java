package element.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum ElementTypeEnum {
    HEAD("head"),
    HAIR("hair"),
    EYE("eye"),
    EYEBROWN("eyebrown"),
    NOSE("nose"),
    MOUTH("mouth"),
    EARS("ears"),
    BODY("body"),
    HAT("hat"),
    TOP("top"),
    TOPCOLOUR("topcolour"),
    HARCOLOUR("harcolour"),
    ;
    private final String label;

    public String label() {
        return label;
    }

    public static ElementTypeEnum of(String type) {
        for (ElementTypeEnum e : ElementTypeEnum.values()) {
            if (e.label().equalsIgnoreCase(type)) {
                return e;
            }
        }
        return null;
    }
}
