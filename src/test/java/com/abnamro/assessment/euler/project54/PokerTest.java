package com.abnamro.assessment.euler.project54;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static com.abnamro.assessment.euler.project54.Poker.VALUE_OF_ROYAL_FLUSH;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PokerTest {
    public static final List<Integer> VALUES_OF_STRAIGHT_FLUSH = Arrays.asList(
            Card.KING.getValue() + Card.QUEEN.getValue() + Card.JACK.getValue() + Card.TEN.getValue() + Card.NINE.getValue(),
            Card.QUEEN.getValue() + Card.JACK.getValue() + Card.TEN.getValue() + Card.NINE.getValue() + Card.EIGHT.getValue(),
            Card.JACK.getValue() + Card.TEN.getValue() + Card.NINE.getValue() + Card.EIGHT.getValue() + Card.SEVEN.getValue(),
            Card.TEN.getValue() + Card.NINE.getValue() + Card.EIGHT.getValue() + Card.SEVEN.getValue() + Card.SIX.getValue(),
            Card.NINE.getValue() + Card.EIGHT.getValue() + Card.SEVEN.getValue() + Card.SIX.getValue() + Card.FIVE.getValue(),
            Card.EIGHT.getValue() + Card.SEVEN.getValue() + Card.SIX.getValue() + Card.FIVE.getValue() + Card.FOUR.getValue(),
            Card.SEVEN.getValue() + Card.SIX.getValue() + Card.FIVE.getValue() + Card.FOUR.getValue() + Card.THREE.getValue(),
            Card.SIX.getValue() + Card.FIVE.getValue() + Card.FOUR.getValue() + Card.THREE.getValue() + Card.TWO.getValue()
    );

    private final Poker underTest = new Poker();

    @Test
    void shouldDetectRoyalFlush() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, 0);
        cards.put(Suite.D, 0);
        cards.put(Suite.H, VALUE_OF_ROYAL_FLUSH);
        cards.put(Suite.S, 0);
        Player player = new Player(cards);

        Hand result = underTest.hasRoyalFlush(player);
        assertEquals(Score.ROYAL_FLUSH, result.getScore(), "should be a royal flush");
    }

    @Test
    void shouldDetectItIsNotARoyalFlushWhileAllDifferentCardsArePresent() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, 0);
        cards.put(Suite.D, Card.JACK.getValue());
        cards.put(Suite.H, VALUE_OF_ROYAL_FLUSH - Card.JACK.getValue());
        cards.put(Suite.S, 0);
        Player player = new Player(cards);

        Hand result = underTest.hasRoyalFlush(player);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a royal flush");
    }

    @Test
    void shouldDetectItIsNotARoyalFlush() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.EIGHT.getValue());
        cards.put(Suite.D, 0);
        cards.put(Suite.H, VALUE_OF_ROYAL_FLUSH - Card.JACK.getValue());
        cards.put(Suite.S, 0);
        Player player = new Player(cards);

        Hand result = underTest.hasRoyalFlush(player);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a royal flush");
    }

    @Test
    void shouldDetectStraightFlush() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, 0);
        cards.put(Suite.D, VALUES_OF_STRAIGHT_FLUSH.get(2));
        cards.put(Suite.H, 0);
        cards.put(Suite.S, 0);
        Player player = new Player(cards);

        Hand result = underTest.hasStraightFlush(player);
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
        Player player = new Player(cards);

        Hand result = underTest.hasStraightFlush(player);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a straight flush");
    }

    @Test
    void shouldDetectItIsNotAStraightFlushWhenMultipleSuites() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, 0);
        cards.put(Suite.D, 0);
        cards.put(Suite.H, VALUES_OF_STRAIGHT_FLUSH.get(2) - Card.TEN.getValue());
        cards.put(Suite.S, Card.TEN.getValue());
        Player player = new Player(cards);

        Hand result = underTest.hasStraightFlush(player);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a straight flush");
    }

    @Test
    void shouldDetectFourOfAKind() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.NINE.getValue());
        cards.put(Suite.H, Card.NINE.getValue() + Card.SIX.getValue());
        cards.put(Suite.S, Card.NINE.getValue());
        Player player = new Player(cards);

        Hand result = underTest.hasFourOfAKind(player);
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
        Player player = new Player(cards);

        Hand result = underTest.hasFourOfAKind(player);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a four of a kind");
    }

    @Test
    void shouldDetectThreeOfAKind() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.KING.getValue());
        cards.put(Suite.H, Card.NINE.getValue() + Card.SIX.getValue());
        cards.put(Suite.S, Card.NINE.getValue());
        Player player = new Player(cards);

        Hand result = underTest.hasThreeOfAKind(player);
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
        Player player = new Player(cards);

        Hand result = underTest.hasThreeOfAKind(player);
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
        Player player = new Player(cards);

        Hand result = underTest.hasThreeOfAKind(player);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a three of a kind");
    }

    @Test
    void shouldDetectFullHouse() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.NINE.getValue()+ Card.JACK.getValue());
        cards.put(Suite.H, Card.NINE.getValue() );
        cards.put(Suite.S, Card.JACK.getValue());
        Player player = new Player(cards);

        Hand result = underTest.hasFullHouse(player);
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
        Player player = new Player(cards);

        Hand result = underTest.hasFullHouse(player);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a full house");
    }

    @Test
    void shouldDetectItIsNotFullHouseWhenOnlyOnePair() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.THREE.getValue()+ Card.JACK.getValue());
        cards.put(Suite.H, Card.NINE.getValue() );
        cards.put(Suite.S, Card.SIX.getValue());
        Player player = new Player(cards);

        Hand result = underTest.hasFullHouse(player);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a full house");
    }

    @Test
    void shouldDetectAFlush() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, 0);
        cards.put(Suite.D, 0);
        cards.put(Suite.H, Card.THREE.getValue() + Card.SIX.getValue() + Card.NINE.getValue() + Card.JACK.getValue() + Card.ACE.getValue());
        cards.put(Suite.S, 0);
        Player player = new Player(cards);

        Hand result = underTest.hasFlush(player);
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
        Player player = new Player(cards);

        Hand result = underTest.hasFlush(player);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a flush");
    }

    @Test
    void shouldDetectAStraight() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.SIX.getValue() + Card.NINE.getValue());
        cards.put(Suite.D, Card.FIVE.getValue());
        cards.put(Suite.H, Card.SEVEN.getValue() + Card.EIGHT.getValue());
        cards.put(Suite.S, 0);
        Player player = new Player(cards);

        Hand result = underTest.hasStraight(player);
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
        Player player = new Player(cards);

        Hand result = underTest.hasStraight(player);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a straight");
    }

    @Test
    void shouldDetectTwoPairs() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.NINE.getValue() + Card.JACK.getValue());
        cards.put(Suite.H, Card.TEN.getValue() );
        cards.put(Suite.S, Card.JACK.getValue());
        Player player = new Player(cards);

        Hand result = underTest.hasTwoPairs(player);
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
        Player player = new Player(cards);

        Hand result = underTest.hasTwoPairs(player);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a two pair");
    }

    @Test
    void shouldDetectOnePair() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.NINE.getValue() + Card.JACK.getValue());
        cards.put(Suite.H, Card.TEN.getValue());
        cards.put(Suite.S, Card.THREE.getValue());
        Player player = new Player(cards);

        Hand result = underTest.hasOnePair(player);
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
        Player player = new Player(cards);

        Hand result = underTest.hasOnePair(player);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a pair");
    }

    /**
     * Since we decide if it is a full house by validating the user has a
     * three-of-a-kind and a one-pair... the one-pair calculation should not be
     * tricked to give a positive result on a three of a kind.
     */
    @Test
    void shouldDetectItIsNotAPairIfItIsAThreeOfAKind() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.TEN.getValue());
        cards.put(Suite.D, Card.TEN.getValue() + Card.JACK.getValue());
        cards.put(Suite.H, Card.TEN.getValue());
        cards.put(Suite.S, Card.THREE.getValue());
        Player player = new Player(cards);

        Hand result = underTest.hasOnePair(player);
        assertEquals(Score.UNDETERMINED, result.getScore(), "should not be a pair");
    }

    @Test
    void shouldDetectHighCard() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.THREE.getValue());
        cards.put(Suite.D, Card.NINE.getValue() + Card.JACK.getValue());
        cards.put(Suite.H, Card.TEN.getValue());
        cards.put(Suite.S, Card.SIX.getValue());
        Player player = new Player(cards);

        Hand result = underTest.hasHighCard(player);
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
        Player player = new Player(cards);

        Hand result = underTest.hasRoyalFlush(player);
        assertEquals(Score.ROYAL_FLUSH, result.getScore(), "should be a royal flush");
    }
    @Test
    void shouldValueTheHandAsAFullHouse() {
        Map<Suite, Integer> cards = new HashMap<>();
        cards.put(Suite.C, Card.NINE.getValue());
        cards.put(Suite.D, Card.NINE.getValue()+ Card.JACK.getValue());
        cards.put(Suite.H, Card.NINE.getValue() );
        cards.put(Suite.S, Card.JACK.getValue());
        Player player = new Player(cards);

        Hand result = underTest.calculateHandValue(player);
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
        Player player = new Player(cards);

        Hand result = underTest.hasOnePair(player);
        assertEquals(Score.ONE_PAIR, result.getScore(), "should be a one pair");
        assertEquals(Card.NINE.getValue(), result.getHighCards().get(0), "should detect the card of the pair");
        assertEquals(Card.JACK.getValue(), result.getHighCards().get(1), "should detect the high card");
        assertEquals(Card.THREE.getValue(), result.getHighCards().get(3), "should detect the high card");
    }

    @Test
    void shouldDetermineWinnerFullHouseVSTwoPair() {
        Map<Suite, Integer> cardsPlayerOne = new HashMap<>();
        cardsPlayerOne.put(Suite.C, Card.NINE.getValue());
        cardsPlayerOne.put(Suite.D, Card.NINE.getValue()+ Card.JACK.getValue());
        cardsPlayerOne.put(Suite.H, Card.NINE.getValue() );
        cardsPlayerOne.put(Suite.S, Card.JACK.getValue());
        Player playerOne = new Player(cardsPlayerOne);

        Map<Suite, Integer> cardsPlayerTwo = new HashMap<>();
        cardsPlayerTwo.put(Suite.C, Card.NINE.getValue());
        cardsPlayerTwo.put(Suite.D, Card.NINE.getValue() + Card.JACK.getValue());
        cardsPlayerTwo.put(Suite.H, Card.TEN.getValue() );
        cardsPlayerTwo.put(Suite.S, Card.JACK.getValue());
        Player playerTwo = new Player(cardsPlayerTwo);

        Table table = new Table(playerOne, playerTwo);
        assertEquals(1, underTest.determineWinner(table));

        table = new Table(playerTwo, playerOne);
        assertEquals(2, underTest.determineWinner(table));
    }

    @Test
    void shouldDetermineWinnerHighCardVSHighCard() {
        Map<Suite, Integer> cardsPlayerOne = new HashMap<>();
        cardsPlayerOne.put(Suite.C, Card.NINE.getValue());
        cardsPlayerOne.put(Suite.D, Card.TWO.getValue()+ Card.JACK.getValue());
        cardsPlayerOne.put(Suite.H, Card.KING.getValue() );
        cardsPlayerOne.put(Suite.S, Card.SIX.getValue());
        Player playerOne = new Player(cardsPlayerOne);

        Map<Suite, Integer> cardsPlayerTwo = new HashMap<>();
        cardsPlayerTwo.put(Suite.C, Card.NINE.getValue());
        cardsPlayerTwo.put(Suite.D, Card.TWO.getValue() + Card.JACK.getValue());
        cardsPlayerTwo.put(Suite.H, Card.KING.getValue() );
        cardsPlayerTwo.put(Suite.S, Card.SEVEN.getValue());
        Player playerTwo = new Player(cardsPlayerTwo);

        Table table = new Table(playerOne, playerTwo);
        assertEquals(2, underTest.determineWinner(table));

        table = new Table(playerTwo, playerOne);
        assertEquals(1, underTest.determineWinner(table));
    }

    @Test
    void shouldDetermineWinnerStraightVSStraight() {
        Map<Suite, Integer> cardsPlayerOne = new HashMap<>();
        cardsPlayerOne.put(Suite.C, Card.SEVEN.getValue());
        cardsPlayerOne.put(Suite.D, Card.EIGHT.getValue()+ Card.JACK.getValue());
        cardsPlayerOne.put(Suite.H, Card.NINE.getValue() );
        cardsPlayerOne.put(Suite.S, Card.TEN.getValue());
        Player playerOne = new Player(cardsPlayerOne);

        Map<Suite, Integer> cardsPlayerTwo = new HashMap<>();
        cardsPlayerTwo.put(Suite.C, Card.NINE.getValue());
        cardsPlayerTwo.put(Suite.D, Card.QUEEN.getValue() + Card.JACK.getValue());
        cardsPlayerTwo.put(Suite.H, Card.KING.getValue() );
        cardsPlayerTwo.put(Suite.S, Card.TEN.getValue());
        Player playerTwo = new Player(cardsPlayerTwo);

        Table table = new Table(playerOne, playerTwo);
        assertEquals(2, underTest.determineWinner(table));

        table = new Table(playerTwo, playerOne);
        assertEquals(1, underTest.determineWinner(table));
    }

    @Test
    void shouldDetermineWinnerOnePairVSOnePair() {
        Map<Suite, Integer> cardsPlayerOne = new HashMap<>();
        cardsPlayerOne.put(Suite.C, Card.SEVEN.getValue());
        cardsPlayerOne.put(Suite.D, Card.EIGHT.getValue()+ Card.JACK.getValue());
        cardsPlayerOne.put(Suite.H, Card.EIGHT.getValue() );
        cardsPlayerOne.put(Suite.S, Card.TEN.getValue());
        Player playerOne = new Player(cardsPlayerOne);

        // the 9H is the determining card here
        Map<Suite, Integer> cardsPlayerTwo = new HashMap<>();
        cardsPlayerTwo.put(Suite.C, Card.EIGHT.getValue());
        cardsPlayerTwo.put(Suite.D, Card.TEN.getValue() + Card.JACK.getValue());
        cardsPlayerTwo.put(Suite.H, Card.NINE.getValue() );
        cardsPlayerTwo.put(Suite.S, Card.EIGHT.getValue());
        Player playerTwo = new Player(cardsPlayerTwo);

        Table table = new Table(playerOne, playerTwo);
        assertEquals(2, underTest.determineWinner(table));

        table = new Table(playerTwo, playerOne);
        assertEquals(1, underTest.determineWinner(table));
    }

    @Test
    void shouldPlayAllHands() throws FileNotFoundException {
        List<Table> tables = readFile();

        Map<Integer, Integer> count = new HashMap<>();
        count.put(1, 0);
        count.put(2, 0);

        for(Table table : tables) {
            int winner = underTest.determineWinner(table);

            count.put(winner, count.get(winner) + 1);
        }

        System.out.println("Player One won " + count.get(1) + " times.");
        System.out.println("Player Two won " + count.get(2) + " times.");

        assertEquals(376, count.get(1));
    }

    private List<Table> readFile() throws FileNotFoundException {
        List<Table> tables = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("./target/test-classes/p054/poker.txt"))) {
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (nextLine != null && !nextLine.isEmpty()) {
                    tables.add(PokerHandDataParser.parseHands(nextLine));
                }
            }
        }

        return tables;
    }
}
