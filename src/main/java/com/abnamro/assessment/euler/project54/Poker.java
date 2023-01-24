package com.abnamro.assessment.euler.project54;

import java.util.*;
import java.util.function.Function;

/**
 * In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:
 *
 * High Card: Highest value card.
 * One Pair: Two cards of the same value.
 * Two Pairs: Two different pairs.
 * Three of a Kind: Three cards of the same value.
 * Straight: All cards are consecutive values.
 * Flush: All cards of the same suit.
 * Full House: Three of a kind and a pair.
 * Four of a Kind: Four cards of the same value.
 * Straight Flush: All cards are consecutive values of same suit.
 * Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
 * The cards are valued in the order:
 * 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
 *
 * If two players have the same ranked hands then the rank made up of the highest value wins; for example, a pair of eights beats a pair of fives (see example 1 below). But if two ranks tie, for example, both players have a pair of queens, then highest cards in each hand are compared (see example 4 below); if the highest cards tie then the next highest cards are compared, and so on.
 *
 * Consider the following five hands dealt to two players:
 *
 * Hand	 	Player 1	 	    Player 2	 	    Winner
 * 1	 	5H 5C 6S 7S KD      2C 3S 8S 8D TD      Player 2
 *          Pair of Fives       Pair of Eights
 *
 * 2	 	5D 8C 9S JS AC      2C 5C 7D 8S QH      Player 1
 *          Highest card Ace    Highest card Queen
 *
 * 3        2D 9C AS AH AC      3D 6D 7D TD QD      Player 2
 *          Three Aces          Flush with Diamonds
 *
 * 4	 	4D 6S 9H QH QC      3D 6D 7H QD QS      Player 1
 *          Pair of Queens      Pair of Queens
 *          Highest card Nine   Highest card Seven
 *
 * 5	 	2H 2D 4C 4D 4S      3C 3D 3S 9S 9D      Player 1
 *          Full House          Full House
 *          With Three Fours    with Three Threes
 *
 * The file, poker.txt, contains one-thousand random hands dealt to two players. Each line
 * of the file contains ten cards (separated by a single space): the first five are Player 1's
 * cards and the last five are Player 2's cards. You can assume that all hands are valid
 * (no invalid characters or repeated cards), each player's hand is in no specific order,
 * and in each hand there is a clear winner.
 *
 * How many hands does Player 1 win?
 */
public class Poker {
    // todo : add to enum (simple stream summation)
    public static final int ALL_CARDS =
            Card.ACE.getValue() + Card.KING.getValue() + Card.QUEEN.getValue()
                + Card.JACK.getValue() + Card.TEN.getValue() + Card.NINE.getValue()
                + Card.EIGHT.getValue() + Card.SEVEN.getValue() +Card.SIX.getValue()
                + Card.FIVE.getValue() + Card.FOUR.getValue() + Card.THREE.getValue()
                + Card.TWO.getValue();

    public static final int VALUE_OF_ROYAL_FLUSH =
            Card.ACE.getValue() + Card.KING.getValue() + Card.QUEEN.getValue()
            + Card.JACK.getValue() + Card.TEN.getValue();

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

    int determineWinner(Table table) {
        Hand playerOne = calculateHandValue(table.getPlayerOne());
        Hand playerTwo = calculateHandValue(table.getPlayerTwo());

        if (playerOne.getScore().getValue() > playerTwo.getScore().getValue()) {
            return 1;
        }

        if (playerOne.getScore().getValue() < playerTwo.getScore().getValue()) {
            return 2;
        }

        return determineWinnerOnHighestSideCard(playerOne, playerTwo);
    }

    private static int determineWinnerOnHighestSideCard(Hand playerOne, Hand playerTwo) {
        int winner = 0;

        for(int cardIndex = 0; cardIndex< playerOne.getHighCards().size(); cardIndex++){
            if (playerOne.getHighCards().get(cardIndex) > playerTwo.getHighCards().get(cardIndex)) {
                winner = 1;
                break;
            } else if (playerOne.getHighCards().get(cardIndex) < playerTwo.getHighCards().get(cardIndex)) {
                winner = 2;
                break;
            }
        }
        return winner;
    }

    Hand calculateHandValue(Player player) {
        List<Function<Player, Hand>> operations = Arrays.asList(
                hand -> hasRoyalFlush(player),
                hand -> hasStraightFlush(player),
                hand -> hasFourOfAKind(player),
                hand -> hasFullHouse(player),
                hand -> hasFlush(player),
                hand -> hasStraight(player),
                hand -> hasThreeOfAKind(player),
                hand -> hasTwoPairs(player),
                hand -> hasOnePair(player),
                hand -> hasHighCard(player)
        );

        Hand hand = new Hand(Score.UNDETERMINED, 0);
        int index = 0;
        while(Score.UNDETERMINED == hand.getScore()) {
            hand = operations.get(index++).apply(player);
        }

        return hand;
    }

    Hand hasRoyalFlush(Player player) {
        if(1 == player.getAllCards().stream().filter(v -> v == Poker.VALUE_OF_ROYAL_FLUSH).count()) {
            return new Hand(Score.ROYAL_FLUSH, Card.ACE.getValue());
        }

        return new Hand(Score.UNDETERMINED, 0);
    }

    Hand hasStraightFlush(Player player) {
        if(1 == player.getAllCards().stream().filter(v -> Poker.VALUES_OF_STRAIGHT_FLUSH.stream().anyMatch(sf -> sf.intValue() == v.intValue())).count()) {
            int highCardValue = player.getAllCards().stream()
                    .filter(v -> 0 != v)
                    .findFirst()
                    .map(v -> determineHighCard(v))
                    .get();

            return new Hand(Score.STRAIGHT_FLUSH, highCardValue);
        }

        return new Hand(Score.UNDETERMINED, 0);
    }

    Hand hasFourOfAKind(Player player) {
        // bitwise and
        int result = player.getAllCards().stream()
                .reduce(ALL_CARDS, (subtotal, element) -> subtotal & element)
                .intValue();

        if(result != 0) {
            return new Hand(
                    Score.FOUR_OF_A_KIND,
                    Arrays.asList(result, highCardNextToFourOfAKind(player, result))
            );
        }

        return new Hand(Score.UNDETERMINED, 0);
    }

    Hand hasFullHouse(Player player) {
        Hand threeOfAKind = hasThreeOfAKind(player);
        Hand onePair = hasOnePair(player);

        if(Score.THREE_OF_A_KIND == threeOfAKind.getScore() &&
           Score.ONE_PAIR == onePair.getScore()) {
            return new Hand(
                    Score.FULL_HOUSE,
                    Arrays.asList(threeOfAKind.getHighCards().get(0), onePair.getHighCards().get(0))
            );
        }

        return new Hand(Score.UNDETERMINED, 0);
    }

    Hand hasFlush(Player player) {
        if(1 == player.getAllCards().stream()
                .filter(v -> v != 0)
                .count()
        ) {
            return new Hand(Score.FLUSH, allCardsSorted(player));
        }

        return new Hand(Score.UNDETERMINED, 0);
    }

    Hand hasStraight(Player player) {
        int result = Arrays.stream(Suite.values())
                .map(s -> player.getCardsForSuite(s))
                .reduce(0, (subtotal, element) -> subtotal | element);

        // todo : magic string
        if(Integer.toBinaryString(result).contains("11111")) {
            return new Hand(Score.STRAIGHT, determineHighCard(result));
        }

        return new Hand(Score.UNDETERMINED, 0);
    }

    Hand hasThreeOfAKind(Player player) {
         for(Suite suite : Suite.values()) {
            int result = Arrays.stream(Suite.values())
                    .filter(s -> s != suite)
                    .map(s -> player.getCardsForSuite(s))
                    .reduce(ALL_CARDS, (subtotal, element) -> subtotal & element);

            if(result != 0) {
                List<Integer> highCards = new ArrayList<>();
                highCards.add(result);
                highCards.addAll(highCardsNextToThreeOfAKind(player, result));
                return new Hand(Score.THREE_OF_A_KIND, highCards);
            }
        }

        return new Hand(Score.UNDETERMINED, 0);
    }

    Hand hasTwoPairs(Player player) {
        List<Integer> allCards = allCardsSorted(player);

        // count similar value cards
        Map<Integer, Integer> cardCount = new HashMap<>();
        for(int cardIndex = 0; cardIndex<allCards.size(); cardIndex++) {
            cardCount.put(
                    allCards.get(cardIndex),
                    Optional.ofNullable(cardCount.get(allCards.get(cardIndex))).orElse(0).intValue() + 1
            );
        }

        // build list pairs and memorize the additional high card
        List<Integer> pairCards = new ArrayList<>();
        Integer highCard = 0;
        for(Integer card: cardCount.keySet()) {
            if(cardCount.get(card).intValue() == 2) {
                pairCards.add(card);
            } else {
                highCard = card;
            }
        }

        if(pairCards.size() == 2) {
            sortHighLow(pairCards);
            pairCards.add(highCard);
            return new Hand(Score.TWO_PAIRS, pairCards);
        }

        return new Hand(Score.UNDETERMINED, 0);
    }

    Hand hasOnePair(Player player) {
        List<Integer> allCards = allCardsSorted(player);

        // count similar value cards
        Map<Integer, Integer> cardCount = new HashMap<>();
        for(int cardIndex = 0; cardIndex<allCards.size(); cardIndex++) {
            cardCount.put(
                    allCards.get(cardIndex),
                    Optional.ofNullable(cardCount.get(allCards.get(cardIndex))).orElse(0).intValue() + 1
            );
        }

        // build list pairs and memorize the additional high card
        List<Integer> pairCards = new ArrayList<>();
        List<Integer> highCards = new ArrayList<>();
        for(Integer card: cardCount.keySet()) {
            if(cardCount.get(card).intValue() == 2) {
                pairCards.add(card);
            } else {
                highCards.add(card);
            }
        }

        if(pairCards.size() == 1) {
            sortHighLow(pairCards);
            sortHighLow(highCards);
            pairCards.addAll(highCards);
            return new Hand(Score.ONE_PAIR, pairCards);
        }

        return new Hand(Score.UNDETERMINED, 0);
    }

    Hand hasHighCard(Player player) {
        return new Hand(Score.HIGH_CARD, allCardsSorted(player));
    }

    private int highCardNextToFourOfAKind(Player player, int fourOfAKindCard) {
        return player.getAllCards().stream()
                .filter(v -> v != 0)
                .map(v -> v - fourOfAKindCard)
                .filter(v -> v != 0)
                .findFirst()
                .get();
    }

    private List<Integer> highCardsNextToThreeOfAKind(Player player, int threeOfAKindCard) {
        List<Integer> highCards = new ArrayList<>();

        // iterate over the suites in hand
        for(Suite suite : Suite.values()) {
            // for each suit in hand iterate over the cards
            String cardsAsBinary = Integer.toBinaryString(player.getCardsForSuite(suite));
            for(int index = 0; index < cardsAsBinary.length() ; index++) {
                if(cardsAsBinary.charAt(cardsAsBinary.length() - (index+1)) == '1') {
                    // if the card is not a three of a kind card then it is a highcard
                    if(threeOfAKindCard != Card.values()[index].getValue()) {
                        highCards.add(Card.values()[index].getValue());
                    }
                }
            }
        }

        sortHighLow(highCards);

        return highCards;
    }

    List<Integer> allCardsSorted(Player player) {
        List<Integer> allCards = new ArrayList<>();

        // iterate of the suites in hand
        for(Suite suite : Suite.values()) {
            // for each suit in hand iterate over the cards
            String cardsAsBinary = Integer.toBinaryString(player.getCardsForSuite(suite));
            for(int index = 0; index < cardsAsBinary.length() ; index++) {
                if(cardsAsBinary.charAt(cardsAsBinary.length() - (index+1)) == '1') {
                    allCards.add(Card.values()[index].getValue());
                }
            }
        }

        sortHighLow(allCards);

        return allCards;

    }

    private void sortHighLow(List<Integer> allCards) {
        allCards.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
    }

    int determineHighCard(int suiteScore) {
        // To find the position of the
        // most significant set bit
        int k = (int)(Math.log(suiteScore) / Math.log(2));

        // To return the value of the number
        // with set bit at k-th position
        return 1 << k;
    }

    Table parseHands(String hands) {
        Table table = new Table();
        String[] allCards = hands.split(" ");

        for(int cardNr = 0; cardNr <= 4; cardNr ++) {
            Card card = readCard(cardNr, allCards);
            Suite suite = readSuite(card, cardNr, allCards);

            table.getPlayerOne().setCardsForSuite(suite, table.getPlayerOne().getCardsForSuite(suite).intValue() + card.getValue());
        }

        for(int cardNr = 5; cardNr <= 9; cardNr ++) {
            Card card = readCard(cardNr, allCards);
            Suite suite = readSuite(card, cardNr, allCards);

            table.getPlayerTwo().setCardsForSuite(suite, table.getPlayerTwo().getCardsForSuite(suite).intValue() + card.getValue());
        }

        return table;
    }

    Card readCard(int cardNr, String[] allCards) {
        return Card.forCard(allCards[cardNr].substring(0, 1));
    }

    Suite readSuite(Card card, int cardNr, String[] allCards) {
        return Suite.valueOf(allCards[cardNr].substring(1, 2));
    }
}
