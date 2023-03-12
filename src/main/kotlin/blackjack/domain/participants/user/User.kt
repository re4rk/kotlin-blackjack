package blackjack.domain.participants.user

import blackjack.domain.card.Card
import blackjack.domain.state.Hit
import blackjack.domain.state.State

abstract class User(val name: Name, var state: State) {
    val score: Int
        get() = state.score.value

    val isHit: Boolean
        get() = state is Hit

    open fun draw(card: Card) {
        state = state.draw(card)
    }
}
