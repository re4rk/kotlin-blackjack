package blackjack.domain.participants

import blackjack.domain.card.Cards

class Score(val cards: Cards) {
    val score: Int
        get() = if (isMaxScoreInRange) maxScore else minScore

    val minScore: Int
        get() = cards.toList().sumOf { it.value.value }

    val maxScore: Int
        get() = minScore + if (cards.containsACE() && validateAceCondition) ACE_OTHER_NUMBER_DIFF else 0

    private val validateAceCondition: Boolean
        get() = minScore + ACE_OTHER_NUMBER_DIFF <= BLACKJACK_NUMBER

    private val isMaxScoreInRange: Boolean
        get() = maxScore <= BLACKJACK_NUMBER

    companion object {
        private const val ACE_OTHER_NUMBER_DIFF = 10
        private const val BLACKJACK_NUMBER = 21
    }
}
