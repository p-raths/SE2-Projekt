package ch.hsr.se2.kartenverwaltung.domain;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by roberto on 29.03.15.
 */
public class CardType {
    private String typeName;
    private String typeDescription;
    private HashMap<Integer, Card> cardMap;
    private HashMap<Integer, Attribute> defaultAttributesMap;

    public CardType(final String typeName, final String typeDescription) {
        this.typeName = typeName;
        this.typeDescription = typeDescription;
        this.cardMap = new HashMap<>();
        this.defaultAttributesMap = new HashMap<>();
    }

    public void addCard(final Card newCard) {
        this.cardMap.put(newCard.getCardId(), newCard);
    }

    public Collection<Card> getAllCards() {
        return cardMap.values();
    }

    public Card getCard(final int cardKey) {
        return cardMap.get(cardKey);
    }

    public void addDefaultAttribute(final Attribute attribute) {
        this.defaultAttributesMap.put(attribute.getId(), attribute);
    }

    public Attribute getDefaultAttribute(final int attributeKey) {
        return this.defaultAttributesMap.get(attributeKey);
    }

    public Collection<Attribute> getAllDefaultAttributes() {
        return this.defaultAttributesMap.values();
    }

    public String getTypeName() {
        return typeName;
    }

    public String getTypeDescription() {
        return typeDescription;
    }
}
