package net.janhorak.game.ne.engine;

import java.util.EnumSet;
import java.util.Set;

/**
 *
 * @author Jan
 */
public enum GameState {

    STARTUP {
        @Override
        public Set<GameState> possibleFollowUps() {
            return EnumSet.of(MENU);
        }
    },

    MENU {
        @Override
        public Set<GameState> possibleFollowUps() {
            return EnumSet.of(SHUTDOWN, GAME);
        }
    },
    
    GAME {
        @Override
        public Set<GameState> possibleFollowUps() {
            return EnumSet.of(PAUSE, MENU);
        }
    },
    PAUSE {
        @Override
        public Set<GameState> possibleFollowUps() {
            return EnumSet.of(GAME);
        }
    },

    SHUTDOWN 
    ;

    public Set<GameState> possibleFollowUps() {
        return EnumSet.noneOf(GameState.class);
    }
    
    public boolean isChangeAllowed(GameState nextState){
        return possibleFollowUps().contains(nextState);
    }
    
    public boolean isInState(GameState state){
        return this == state;
    }

}
