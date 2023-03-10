package blackjack.domain.result

import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Guest
import blackjack.domain.participants.Participants

class BlackJackResult(dealer: Dealer, guests: List<Guest>) {
    val guests: List<BlackJackResultUser> = guests.map { guest ->
        BlackJackResultUser(guest.name, calculateGuestRevenue(guest, dealer))
    }

    val dealer = BlackJackResultUser(dealer.name, calculateDealerRevenue())

    constructor(participants: Participants) : this(participants.dealer, participants.guests)

    private fun calculateDealerRevenue(): Int = guests.sumOf { -it.revenue }

    private fun calculateGuestRevenue(guest: Guest, dealer: Dealer): Int =
        (guest.state.matchWith(dealer).rate * guest.bettingMoney.value).toInt()
}
