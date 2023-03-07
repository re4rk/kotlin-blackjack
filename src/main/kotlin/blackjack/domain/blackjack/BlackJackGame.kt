package blackjack.domain.blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Guest
import blackjack.domain.participants.User

class BlackJackGame {
    lateinit var getCommand: (String) -> Boolean

    fun dealerTurn(dealer: Dealer, cardDeck: CardDeck, output: () -> Unit) {
        if (dealer.isBlackJack()) return
        if (dealer.isContinue()) {
            dealer.draw(cardDeck.nextCard())
            output()
        }
    }

    fun guestsTurn(guests: List<Guest>, cardDeck: CardDeck, output: (User) -> Unit) =
        guests.forEach { guest -> guestTurn(guest, cardDeck, output) }

    private fun guestTurn(guest: Guest, cardDeck: CardDeck, output: (User) -> Unit) {
        if (guest.isBlackJack()) return
        when (getCommand(guest.name.toString())) {
            true -> guestDraw(guest, cardDeck, output)
            false -> output(guest)
        }
    }

    private fun guestDraw(guest: Guest, cardDeck: CardDeck, output: (User) -> Unit) {
        guest.draw(cardDeck.nextCard())
        output(guest)
        if (guest.isContinue()) {
            guestTurn(guest, cardDeck, output)
        }
    }
}
