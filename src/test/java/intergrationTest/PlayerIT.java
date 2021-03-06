package intergrationTest;

import card.Energy;
import card.Pokemon;
import cardcontainer.Deck;
import cardcontainer.Hand;
import parser.*;
import org.junit.Before;
import org.junit.Test;
import player.Coin;
import player.Player;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by luckyfang0601 on 2017-07-14.
 */
public class PlayerIT {

    private static final int PRIZE_CARD_LIMIT = 6;
    private Player player;
    private ArrayList<Energy> energyArray;
    private Coin playerCoin;
    private Retreat retreat;
    private ArrayList<Attack> attacks;
    private Deck newDeck;
    private Hand newHand;
    private ArrayList<Pokemon> poks;




    @Before
    public void beforeEachTest(){

        player = new Player();
        energyArray= new ArrayList<Energy>(20);
        retreat = new Retreat("fighting",1);
        ArrayList<AbilityLogic> logic = new ArrayList<AbilityLogic>();
       // logic.add(new Dam(new ArrayList<String>(Arrays.asList("dam:target:choice:opponent-bench:30"))));
        Ability ability = new Ability("Rain Splash","damage", logic);
        Requirement requirement=new Requirement("general",2);
        ArrayList<Requirement> requirements = new ArrayList<Requirement>();
        requirements.add(requirement);

        Attack attack = new Attack(requirements,ability);
        ArrayList<Attack> attacks = new ArrayList<Attack>();
        attacks.add(attack);
    }

    @Test
    public void putPokOnBench() throws Exception {


        Pokemon card1 = new Pokemon("Raichu", 27, "pokemon", 90,energyArray, "stage-one","pikachu",retreat,attacks,poks);
        player.putPokOnBench(card1);
        assertEquals(1,player.getBench().getNoOfCards());
    }


    @Test
    public void hasActivePokemon() throws Exception {
        assertFalse((player.hasActivePokemon()));
        Pokemon card1 = new Pokemon("Raichu", 27, "pokemon", 90,energyArray, "stage-one","pikachu",retreat,attacks,poks);
        player.setActivePokemon(card1);
        assertTrue(player.hasActivePokemon());
    }

    @Test
    public void getNoCardsDeck() throws Exception {
        newDeck = new Deck();
        newDeck.populateDeck("src/main/resources/deck/deck1.txt");
        player.setDeck(newDeck);
        assertEquals(60,player.getNoCardsDeck());
        player.drawCard();
        assertEquals(59,player.getNoCardsDeck());

    }




}