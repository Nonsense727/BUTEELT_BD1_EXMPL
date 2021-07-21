from flashcards_core.database import Deck, Card
from flashcards_core.schedulers import RandomScheduler


def test_random(session, temp_db):
    """
    Test that the Random algorithm works as espected.

    FIXME this is a stub!
    """
    deck = Deck.create(db=session, name="TestDeck", description="Test Deck")
    deck.set_state(db=session, state={"unseen_first": True, "never_repeat": True})
    card = Card.create(db=session, deck_id=deck.id)
    card = Card.create(db=session, deck_id=deck.id)
    card = Card.create(db=session, deck_id=deck.id)

    scheduler = RandomScheduler(db=session, deck=deck)

    for i in range(7):
        card = scheduler.next_card()
        print(card)
        scheduler.process_test_result(card, True)
