package ch.hsr.se2.kartenverwaltung.domain;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by roberto on 29.03.15.
 */
public class AttributeType {
    private int attributeTypeId;
    private String attributeTypeName;
    private int maxLength;
    private int maxPrecision;
    private HashMap<Integer, Attribute> attributeHashMap;

    public AttributeType(final String attributeTypeName, final int maxLength, final int maxPrecision) {
        this.attributeTypeName = attributeTypeName;
        this.maxLength = maxLength;
        this.maxPrecision = maxPrecision;
        this.attributeHashMap = new HashMap<>();
    }

    public int getAttributeTypeId() {
        return this.attributeTypeId;
    }

    public void setAttributeTypeId(final int attributeTypeId) {
        this.attributeTypeId = attributeTypeId;
    }

    public void setAttribute(final Attribute attribute) {
        this.attributeHashMap.put(attribute.getId(), attribute);
    }

    public Attribute getAttribute(final int attributeKey) {
        return this.attributeHashMap.get(attributeKey);
    }

    public Collection<Attribute> getAllAttributes() {
        return this.attributeHashMap.values();
    }

    public String getAttributeTypeName() {
        return attributeTypeName;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public int getMaxPrecision() {
        return maxPrecision;
    }
}
