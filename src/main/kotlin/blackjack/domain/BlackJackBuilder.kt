package blackjack.domain

class BlackJackBuilder {
    private lateinit var cardDeck: CardDeck
    private lateinit var participants: Participants
    fun cardDeck(cards: List<Card>) {
        cardDeck = CardDeck(cards)
    }

    fun participants(name: String, names: List<String>) {
        participants = ParticipantsBuilder().apply {
            dealer(name, cardDeck)
            users(names, cardDeck)
        }.build()
    }
    fun build(): BlackJack {
        return BlackJack(cardDeck, participants)
    }

    companion object {
        fun init(block: BlackJackBuilder.() -> Unit): BlackJack =
            BlackJackBuilder().apply(block).build()
    }
}
