package MyMod.cards;

import basemod.abstracts.CustomCard;

public abstract class AbstractMRCard extends CustomCard {

    public static String TESTIMG="img/cards/test_attack.png";

    public AbstractMRCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }


}
