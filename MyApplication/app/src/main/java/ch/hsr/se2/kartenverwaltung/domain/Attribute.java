package ch.hsr.se2.kartenverwaltung.domain;

/**
 * Created by roberto on 29.03.15.
 */
public class Attribute {
    private int attributeId;
    private String attributeName;
    private String attributeValue;


    public Attribute(final String attributeName, final String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public void setAttributeId(final int attributeId) {
        this.attributeId = attributeId;
    }

    public Integer getId() {
        return this.attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }
}
