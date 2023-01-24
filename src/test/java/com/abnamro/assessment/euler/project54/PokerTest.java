package com.abnamro.assessment.euler.project54;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.abnamro.assessment.euler.project54.BruteForce.VALUES_OF_STRAIGHT_FLUSH;
import static com.abnamro.assessment.euler.project54.BruteForce.VALUE_OF_ROYAL_FLUSH;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PokerTest {
    private BruteForce underTest = new BruteForce();
    
    @Test
    void shouldParseHands() {
        Table table = underTest.parseHands("JS 4H 8C 10H JH 2C 9H 9C AS JD");

        assertEquals(64, table.playerOne.get(Suite.C));
        assertEquals(0, table.playerOne.get(Suite.D));
        assertEquals(772, table.playerOne.get(Suite.H));
        assertEquals(512, table.playerOne.get(Suite.S));

        assertEquals(129, table.playerTwo.get(Suite.C));
        assertEquals(512, table.playerTwo.get(Suite.D));
        assertEquals(128, table.playerTwo.get(Suite.H));
        assertEquals(4096, table.playerTwo.get(Suite.S));
    }

    @Test
    void shouldDetectRoyalFlush() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, 0);
        cards.put(Suite.D, 0);
        cards.put(Suite.H, VALUE_OF_ROYAL_FLUSH);
        cards.put(Suite.S, 0);

        Hand result = underTest.isRoyalFlush(cards);
        assertEquals(Score.ROYAL_FLUSH, result.getScore(), "should be a royal flush");
    }

    @Test
    void shouldDetectItIsNotARoyalFlush() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.EIGHT.getValue());
        cards.put(Suite.D, 0);
        cards.put(Suite.H, VALUE_OF_ROYAL_FLUSH - Card.JACK.getValue());
        cards.put(Suite.S, 0);

        Hand result = underTest.isRoyalFlush(cards);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a royal flush");
    }

    @Test
    void shouldDetectStraightFlush() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, 0);
        cards.put(Suite.D, VALUES_OF_STRAIGHT_FLUSH.get(2));
        cards.put(Suite.H, 0);
        cards.put(Suite.S, 0);

        Hand result = underTest.isStraightFlush(cards);
        assertEquals(Score.STRAIGHT_FLUSH, result.getScore(), "should be a straight flush");
        assertEquals(Card.JACK.getValue(), result.getHighCards().get(0), "should detect the proper high card");
    }

    @Test
    void shouldDetectItIsNotAStraightFlushWhenNonConsecutiveOfSameSuite() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, 0);
        cards.put(Suite.D, 0);
        cards.put(Suite.H, VALUES_OF_STRAIGHT_FLUSH.get(2) - Card.TEN.getValue() + Card.TWO.getValue());
        cards.put(Suite.S, 0);

        Hand result = underTest.isStraightFlush(cards);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a straight flush");
    }

    @Test
    void shouldDetectItIsNotAStraightFlushWhenMultipleSuites() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, 0);
        cards.put(Suite.D, 0);
        cards.put(Suite.H, VALUES_OF_STRAIGHT_FLUSH.get(2) - Card.TEN.getValue());
        cards.put(Suite.S, Card.EIGHT.getValue());

        Hand result = underTest.isStraightFlush(cards);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a straight flush");
    }

    @Test
    void shouldDetectFourOfAKind() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.NINE.getValue());
        cards.put(Suite.H, Card.NINE.getValue() + Card.SIX.getValue());
        cards.put(Suite.S, Card.NINE.getValue());

        Hand result = underTest.isFourOfAKind(cards);
        assertEquals(Score.FOUR_OF_A_KIND, result.getScore(), "should be a four of a kind");
        assertEquals(Card.NINE.getValue(), result.getHighCards().get(0), "should detect the card of the four of a kind");
        assertEquals(Card.SIX.getValue(), result.getHighCards().get(1), "should detect the proper high card");
    }

    @Test
    void shouldDetectItIsNotFourOfAKind() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.SEVEN.getValue());
        cards.put(Suite.H, Card.NINE.getValue() + Card.SIX.getValue());
        cards.put(Suite.S, Card.NINE.getValue());

        Hand result = underTest.isFourOfAKind(cards);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a four of a kind");
    }

    @Test
    void shouldDetectThreeOfAKind() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.KING.getValue());
        cards.put(Suite.H, Card.NINE.getValue() + Card.SIX.getValue());
        cards.put(Suite.S, Card.NINE.getValue());

        Hand result = underTest.isThreeOfAKind(cards);
        assertEquals(Score.THREE_OF_A_KIND, result.getScore(), "should be a three of a kind");
        assertEquals(Card.NINE.getValue(), result.getHighCards().get(0), "should detect the card of the three of a kind");
        assertEquals(Card.KING.getValue(), result.getHighCards().get(1), "should detect the proper high card");
        assertEquals(Card.SIX.getValue(), result.getHighCards().get(2), "should detect the proper high card");
    }

    @Test
    void shouldDetectThreeOfAKindWithHighCardsOfTheSameSuite() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.KING.getValue() + Card.SIX.getValue());
        cards.put(Suite.H, Card.NINE.getValue());
        cards.put(Suite.S, Card.NINE.getValue());

        Hand result = underTest.isThreeOfAKind(cards);
        assertEquals(Score.THREE_OF_A_KIND, result.getScore(), "should be a three of a kind");
        assertEquals(Card.NINE.getValue(), result.getHighCards().get(0), "should detect the card of the three of a kind");
        assertEquals(Card.KING.getValue(), result.getHighCards().get(1), "should detect the proper high card");
        assertEquals(Card.SIX.getValue(), result.getHighCards().get(2), "should detect the proper high card");
    }

    @Test
    void shouldDetectItIsNotThreeOfAKind() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.SIX.getValue());
        cards.put(Suite.D, Card.KING.getValue());
        cards.put(Suite.H, Card.NINE.getValue() + Card.SIX.getValue());
        cards.put(Suite.S, Card.NINE.getValue());

        Hand result = underTest.isThreeOfAKind(cards);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a three of a kind");
    }

    @Test
    void shouldDetectFullHouse() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.NINE.getValue()+ Card.JACK.getValue());
        cards.put(Suite.H, Card.NINE.getValue() );
        cards.put(Suite.S, Card.JACK.getValue());

        Hand result = underTest.isFullHouse(cards);
        assertEquals(Score.FULL_HOUSE, result.getScore(), "should be a full house");
        assertEquals(Card.NINE.getValue(), result.getHighCards().get(0), "should detect the card of three of a kind");
        assertEquals(Card.JACK.getValue(), result.getHighCards().get(1), "should detect the card of the one pair");
    }

    @Test
    void shouldDetectItIsNotFullHouseWhenOnlyThreeOfAKind() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.NINE.getValue()+ Card.JACK.getValue());
        cards.put(Suite.H, Card.NINE.getValue() );
        cards.put(Suite.S, Card.SIX.getValue());

        Hand result = underTest.isFullHouse(cards);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a full house");
    }

    @Test
    void shouldDetectItIsNotFullHouseWhenOnlyOnePair() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.THREE.getValue()+ Card.JACK.getValue());
        cards.put(Suite.H, Card.NINE.getValue() );
        cards.put(Suite.S, Card.SIX.getValue());

        Hand result = underTest.isFullHouse(cards);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a full house");
    }

    @Test
    void shouldDetectAFlush() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, 0);
        cards.put(Suite.D, 0);
        cards.put(Suite.H, Card.THREE.getValue() + Card.SIX.getValue() + Card.NINE.getValue() + Card.JACK.getValue() + Card.ACE.getValue());
        cards.put(Suite.S, 0);

        Hand result = underTest.isFlush(cards);
        assertEquals(Score.FLUSH, result.getScore(), "should be a flush");
        assertEquals(Card.ACE.getValue(), result.getHighCards().get(0), "should detect the proper high card");
        assertEquals(Card.JACK.getValue(), result.getHighCards().get(1), "should detect the proper high card");
        assertEquals(Card.THREE.getValue(), result.getHighCards().get(4), "should detect the proper high card");
    }

    @Test
    void shouldDetectItIsNotAFlush() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.SIX.getValue());
        cards.put(Suite.D, 0);
        cards.put(Suite.H, Card.THREE.getValue() + Card.NINE.getValue() + Card.JACK.getValue() + Card.ACE.getValue());
        cards.put(Suite.S, 0);

        Hand result = underTest.isFlush(cards);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a flush");
    }

    @Test
    void shouldDetectAStraight() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.SIX.getValue() + Card.NINE.getValue());
        cards.put(Suite.D, Card.FIVE.getValue());
        cards.put(Suite.H, Card.SEVEN.getValue() + Card.EIGHT.getValue());
        cards.put(Suite.S, 0);

        Hand result = underTest.isStraight(cards);
        assertEquals(Score.STRAIGHT, result.getScore(), "should be a straight");
        assertEquals(Card.NINE.getValue(), result.getHighCards().get(0), "should detect the straight high card");
    }

    @Test
    void shouldDetectItIsNotAStraight() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.SIX.getValue() + Card.TEN.getValue());
        cards.put(Suite.D, Card.FIVE.getValue());
        cards.put(Suite.H, Card.SEVEN.getValue() + Card.EIGHT.getValue());
        cards.put(Suite.S, 0);

        Hand result = underTest.isStraight(cards);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a straight");
    }

    @Test
    void shouldDetectTwoPairs() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.NINE.getValue() + Card.JACK.getValue());
        cards.put(Suite.H, Card.TEN.getValue() );
        cards.put(Suite.S, Card.JACK.getValue());

        Hand result = underTest.isTwoPairs(cards);
        assertEquals(Score.TWO_PAIRS, result.getScore(), "should be a two pair");
        assertEquals(Card.JACK.getValue(), result.getHighCards().get(0), "should detect the high card of the pairs");
        assertEquals(Card.NINE.getValue(), result.getHighCards().get(1), "should detect the low card of the pairs");
        assertEquals(Card.TEN.getValue(), result.getHighCards().get(2), "should detect the high card");
    }

    @Test
    void shouldDetectItIsNotTwoPairs() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.EIGHT.getValue());
        cards.put(Suite.D, Card.NINE.getValue() + Card.JACK.getValue());
        cards.put(Suite.H, Card.TEN.getValue() );
        cards.put(Suite.S, Card.JACK.getValue());

        Hand result = underTest.isTwoPairs(cards);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a two pair");
    }

    @Test
    void shouldDetectOnePair() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.NINE.getValue() + Card.JACK.getValue());
        cards.put(Suite.H, Card.TEN.getValue());
        cards.put(Suite.S, Card.THREE.getValue());

        Hand result = underTest.isOnePair(cards);
        assertEquals(Score.ONE_PAIR, result.getScore(), "should be a one pair");
        assertEquals(Card.NINE.getValue(), result.getHighCards().get(0), "should detect the card of the pair");
        assertEquals(Card.JACK.getValue(), result.getHighCards().get(1), "should detect the high card");
        assertEquals(Card.THREE.getValue(), result.getHighCards().get(3), "should detect the high card");
    }

    @Test
    void shouldDetectItIsNotAPair() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.ACE.getValue());
        cards.put(Suite.D, Card.NINE.getValue() + Card.JACK.getValue());
        cards.put(Suite.H, Card.TEN.getValue());
        cards.put(Suite.S, Card.THREE.getValue());

        Hand result = underTest.isOnePair(cards);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a pair");
    }

    // todo
//    @Test
//    void shouldDetectItIsNotAPairIfItIsAThreeOfAKind() {
//        Map<Suite, Integer> cards = new HashMap<>();
//        cards.put(Suite.C, Card.TEN.getValue());
//        cards.put(Suite.D, Card.TEN.getValue() + Card.JACK.getValue());
//        cards.put(Suite.H, Card.TEN.getValue());
//        cards.put(Suite.S, Card.THREE.getValue());
//
//        Hand result = underTest.isOnePair(cards);
//        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a pair");
//    }

    @Test
    void shouldDetectHighCard() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.THREE.getValue());
        cards.put(Suite.D, Card.NINE.getValue() + Card.JACK.getValue());
        cards.put(Suite.H, Card.TEN.getValue());
        cards.put(Suite.S, Card.SIX.getValue());

        Hand result = underTest.isHighCard(cards);
        assertEquals(Score.HIGH_CARD, result.getScore(), "should be a high card");
        assertEquals(Card.JACK.getValue(), result.getHighCards().get(0), "should detect high card");
        assertEquals(Card.NINE.getValue(), result.getHighCards().get(2), "should detect the high card");
        assertEquals(Card.THREE.getValue(), result.getHighCards().get(4), "should detect the high card");
    }

    @Test
    void shouldValueTheHandAsARoyalFlush() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, 0);
        cards.put(Suite.D, 0);
        cards.put(Suite.H, VALUE_OF_ROYAL_FLUSH);
        cards.put(Suite.S, 0);

        Hand result = underTest.isRoyalFlush(cards);
        assertEquals(Score.ROYAL_FLUSH, result.getScore(), "should be a royal flush");
    }
    @Test
    void shouldValueTheHandAsAFullHouse() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.NINE.getValue()+ Card.JACK.getValue());
        cards.put(Suite.H, Card.NINE.getValue() );
        cards.put(Suite.S, Card.JACK.getValue());

        Hand result = underTest.calculateHandValue(cards);
        assertEquals(Score.FULL_HOUSE, result.getScore(), "should be a full house");
        assertEquals(Card.NINE.getValue(), result.getHighCards().get(0), "should detect the card of three of a kind");
        assertEquals(Card.JACK.getValue(), result.getHighCards().get(1), "should detect the card of the one pair");
    }

    @Test
    void shouldValueTheHandAsAOnePair() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.NINE.getValue() + Card.JACK.getValue());
        cards.put(Suite.H, Card.TEN.getValue());
        cards.put(Suite.S, Card.THREE.getValue());

        Hand result = underTest.isOnePair(cards);
        assertEquals(Score.ONE_PAIR, result.getScore(), "should be a one pair");
        assertEquals(Card.NINE.getValue(), result.getHighCards().get(0), "should detect the card of the pair");
        assertEquals(Card.JACK.getValue(), result.getHighCards().get(1), "should detect the high card");
        assertEquals(Card.THREE.getValue(), result.getHighCards().get(3), "should detect the high card");
    }
}
