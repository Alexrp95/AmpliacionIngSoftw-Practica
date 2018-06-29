package es.codeurjc.ais.tictactoe;

import static org.mockito.Mockito.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import org.junit.Before;
import org.mockito.hamcrest.MockitoHamcrest;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;
/**
 *
 * @author Alejandro Ramirez y Alejandro Garcia
 */
public class TicTacToeDoblesTest {
  private TicTacToeGame partida;
  private Connection conexion1, conexion2;
  private Player jugador1, jugador2;
    
    @Before
    public void setUp(){
      //Construimos la partida
    partida = new TicTacToeGame();
    //Construimos los mock de las conexiones
    conexion1 = mock(Connection.class);
    conexion2 = mock(Connection.class);
    //Construimos los jugadores de la partida
    jugador1 = new Player(1,"x","Jugador1");
    jugador2 = new Player(2,"o", "Jugador2");  
    //Añadimso las conexiones a la partida
    partida.addConnection(conexion1);
    partida.addConnection(conexion2);
    
    
    }
    
    @Test
    public void testComprobarGanador1(){
        
        
        //añadimos el jugador 1 y verificamos las conexiones añadidas anteriormente
        partida.addPlayer(jugador1);
        verify(conexion1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(jugador1)));
        verify(conexion2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(jugador1)));
        //reseteamos las conexiones
        reset(conexion1);
        reset(conexion2);
        //añadimos el jugador 2 y verificamos las conexiones
        partida.addPlayer(jugador2);
        verify(conexion1).sendEvent(eq(EventType.JOIN_GAME),MockitoHamcrest.argThat(hasItems(jugador1,jugador2)));	
        verify(conexion2).sendEvent(eq(EventType.JOIN_GAME),MockitoHamcrest.argThat(hasItems(jugador1,jugador2)));
        
        //verificamos que las conexiones reciben el Turno del jugador 1
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
	
        //Empieza a marcar el jugador 1
        partida.mark(0);
        //verificamos que el turno se cambia al jugador 2
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
        //reset a las conexiones
        reset(conexion1);
        reset(conexion2);
        //Seguimos jugando y hacemos el mismo procedimiento todo el rato
        partida.mark(8);
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
        reset(conexion1);
        reset(conexion2);
        
        partida.mark(1);
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
        reset(conexion1);
        reset(conexion2);
        
        partida.mark(3);
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
        reset(conexion1);
        reset(conexion2);
        
        partida.mark(2);
        //Deberia haber ganado el jugador 1, vamos a comprobarlo 
        //Usamos la clase ArgymentCaptor para recuperar los valores pasados como parametros
        ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
        //Verificamos de nuevo las conexiones para ver si han recibido el evento de Fin de partida
        verify(conexion1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
	verify(conexion2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
        //Construimos el evento de Ganador y le pasamos el "argument" del argument captor
        WinnerValue event = argument.getValue();
        //Solo queda verificar el ganador
        assertThat(argument.getValue().player,equalTo(jugador1));
        assertNotEquals(argument.getValue().player, jugador2);
        //Comprobamos que no hay empate
        assertNotNull(event);
        
    }
    
  
   @Test
    public void testComprobarGanador2(){
        //Construimos el array de posiciones Ganador
        
        
        //añadimos el jugador 1 y verificamos las conexiones añadidas anteriormente
        partida.addPlayer(jugador1);
        verify(conexion1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(jugador1)));
        verify(conexion2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(jugador1)));
        //reseteamos las conexiones
        reset(conexion1);
        reset(conexion2);
        //añadimos el jugador 2 y verificamos las conexiones
        partida.addPlayer(jugador2);
        verify(conexion1).sendEvent(eq(EventType.JOIN_GAME),MockitoHamcrest.argThat(hasItems(jugador1,jugador2)));	
        verify(conexion2).sendEvent(eq(EventType.JOIN_GAME),MockitoHamcrest.argThat(hasItems(jugador1,jugador2)));
        
        //verificamos que las conexiones reciben el Turno del jugador 1
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
	
        //Empieza a marcar el jugador 1
        partida.mark(1);
        //verificamos que el turno se cambia al jugador 2
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
        //reset a las conexiones
        reset(conexion1);
        reset(conexion2);
        //Seguimos jugando y hacemos el mismo procedimiento todo el rato
        partida.mark(6);
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
        reset(conexion1);
        reset(conexion2);
        
        partida.mark(0);
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
        reset(conexion1);
        reset(conexion2);
        
        partida.mark(7);
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
        reset(conexion1);
        reset(conexion2);
        
        partida.mark(4);
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
        reset(conexion1);
        reset(conexion2);
        
        partida.mark(8);
        //Deberia haber ganado el jugador 2, vamos a comprobarlo 
        //Usamos la clase ArgymentCaptor para recuperar los valores pasados como parametros
        ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
        //Verificamos de nuevo las conexiones para ver si han recibido el evento de Fin de partida
        verify(conexion1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
	verify(conexion2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
        //Construimos el evento de Ganador y le pasamos el "argument" del argument captor
        WinnerValue event = argument.getValue();
        //Solo queda verificar el ganador
        assertThat(argument.getValue().player,equalTo(jugador2));
        assertNotEquals(argument.getValue().player, jugador1);
        //Comprobamos que no hay empate
        assertNotNull(event);
        
    }
@Test
    public void testComprobarEmpate(){
         
        //añadimos el jugador 1 y verificamos las conexiones añadidas anteriormente
        partida.addPlayer(jugador1);
        verify(conexion1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(jugador1)));
        verify(conexion2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(jugador1)));
        //reseteamos las conexiones
        reset(conexion1);
        reset(conexion2);
        //añadimos el jugador 2 y verificamos las conexiones
        partida.addPlayer(jugador2);
        verify(conexion1).sendEvent(eq(EventType.JOIN_GAME),MockitoHamcrest.argThat(hasItems(jugador1,jugador2)));	
        verify(conexion2).sendEvent(eq(EventType.JOIN_GAME),MockitoHamcrest.argThat(hasItems(jugador1,jugador2)));
        
        //verificamos que las conexiones reciben el Turno del jugador 1
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
	
        //Empieza a marcar el jugador 1
        partida.mark(0);
        //verificamos que el turno se cambia al jugador 2
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
        //reset a las conexiones
        reset(conexion1);
        reset(conexion2);
        //Seguimos jugando y hacemos el mismo procedimiento todo el rato
        partida.mark(1);
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
        reset(conexion1);
        reset(conexion2);
        
        partida.mark(2);
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
        reset(conexion1);
        reset(conexion2);
        
        partida.mark(3);
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
        reset(conexion1);
        reset(conexion2);
        
        partida.mark(4);
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
        reset(conexion1);
        reset(conexion2);
        
        partida.mark(5);
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
        reset(conexion1);
        reset(conexion2);
        
        partida.mark(6);
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador2));
        reset(conexion1);
        reset(conexion2);
        
        partida.mark(7);
        verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
	verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(jugador1));
        reset(conexion1);
        reset(conexion2);
        
        partida.mark(8);
        //Deberia haber empate vamos a comprobarlo 
        //Usamos la clase ArgymentCaptor para recuperar los valores pasados como parametros
        ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
        //Verificamos de nuevo las conexiones para ver si han recibido el evento de Fin de partida
        verify(conexion1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
	verify(conexion2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
        //Construimos el evento de Ganador/Empate y le pasamos el "argument" del argument captor
        WinnerValue event = argument.getValue();
        //Solo queda verificar el empate
        assertNull(event);
        assertNotEquals(argument.getValue().player, jugador1);
        assertNotEquals(argument.getValue().player, jugador2);
    }
    
}
