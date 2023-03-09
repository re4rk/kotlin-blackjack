package blackjack.domain.blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackJackBuilderTest {
    @Test
    fun `BlackJack을 초기화 할 수 있다`() {
        val blackJack = blackJack {
            participants {
                dealer()
                guest("아크", 10)
                guest("로피", 10)
            }
            initDrawAll()
        }
        assertAll(
            { assertThat(blackJack.guests[0].name.toString()).isEqualTo("아크") },
            { assertThat(blackJack.guests[0].cards.size).isEqualTo(2) },
            { assertThat(blackJack.guests[1].name.toString()).isEqualTo("로피") },
            { assertThat(blackJack.guests[1].cards.size).isEqualTo(2) },
            { assertThat(blackJack.dealer.name.toString()).isEqualTo("딜러") },
            { assertThat(blackJack.dealer.cards.size).isEqualTo(2) },
            { assertThat(blackJack.cardDeck.size).isEqualTo(46) },
        )
    }
}
