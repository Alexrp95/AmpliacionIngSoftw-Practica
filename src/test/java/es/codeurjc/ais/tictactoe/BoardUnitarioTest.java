package es.codeurjc.ais.tictactoe;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Alejandro Ramirez y Alejandro Garcia
 */
public class BoardUnitarioTest {
    //Usamos el constructor vacio para crear el tablero
    Board tablero = new Board();
    //Creamos a nuestros 2 participantes para el juego
    Player jugador1 = new Player(1, "x", "Jugador 1");
    Player jugador2 = new Player(2, "o", "Jugador 2");

    //Comenzamos con el primer test, ganara el jugador 1
    @Test
    public void testComprobacionGanador1(){
     //Este array guarda las posiciones que queremos que sean del ganador
     //forzamos el test para que el jugador 1 haga esa jugada
        int[] arrayGanador = {0,1,2};
        
        //EL jugador 1 coloca una X en la casilla 0
        tablero.getCell(0).value = jugador1.getLabel();
        //Turno del jugador 2
        tablero.getCell(3).value = jugador2.getLabel();
        //Marcamos la casilla 1 con la X del jugador 1, como anteriormente hizimos con la casilla 00
        tablero.getCell(1).value = jugador1.getLabel();
        //Turno del jugador 2        
        tablero.getCell(5).value = jugador2.getLabel();
        //Marcamos por ultimo la casilla 2 con la X del jugador 1
        tablero.getCell(2).value = jugador1.getLabel();
    
        //Comprobamos las posiciones del ganador
        assertThat(arrayGanador,equalTo(tablero.getCellsIfWinner(jugador1.getLabel())));
        //Comprobamos el resultado del jugador 2
        assertNull(tablero.getCellsIfWinner(jugador2.getLabel()));
        
        //Comprobamos el empate
        assertEquals(false, tablero.checkDraw()); 
    }

    //en este test Comprobaremos que gana el jugador 2 y pierde el jugador 1
    @Test
    public void testComprobacionGanador2(){
     
        //Creamos el array de la posicion del Ganador
        int[] arrayGanador = {2,5,8};
        //Empezamos a colocar las marcas con el jugador 1
        tablero.getCell(4).value = jugador1.getLabel();
        //Turno del jugador 2, marcamos con O
        tablero.getCell(2).value = jugador2.getLabel();
        //Marcamos con el jugador 1
        tablero.getCell(0).value = jugador1.getLabel();
        //Marcamos con el jugador 2
        tablero.getCell(5).value = jugador2.getLabel();
        //Marcamos con el jugador 1
        tablero.getCell(3).value = jugador1.getLabel();
        //Marcamos con el jugador 2
        tablero.getCell(8).value = jugador2.getLabel();
        
        //Comprabamos los ganadores
        //En este caso primero el AssertNull del que no ha ganado
        assertNull(tablero.getCellsIfWinner(jugador1.getLabel()));
        //Ahora el ganador
        assertThat(arrayGanador,equalTo(tablero.getCellsIfWinner(jugador2.getLabel())));
        //Comprobamso el empate
        assertEquals(false, tablero.checkDraw());
    }

    //En este test comprobaremos el empate
    @Test
    public void testComprobacionEmpate(){
        //No crearemos un array de ganador porque forzaremos el empate
        //Empezaremos marcando con el jugador 1
        tablero.getCell(0).value = jugador1.getLabel();
        //Marcamos con el jugador 2
        tablero.getCell(1).value = jugador2.getLabel();
        //Marcamos con el jugador 1
        tablero.getCell(3).value = jugador1.getLabel();
        //Repetimos el proceso hasta que se llenen las casillas del tablero
        tablero.getCell(6).value = jugador2.getLabel();
        tablero.getCell(7).value = jugador1.getLabel();
        tablero.getCell(4).value = jugador2.getLabel();
        tablero.getCell(5).value = jugador1.getLabel();
        tablero.getCell(8).value = jugador2.getLabel();
        tablero.getCell(2).value = jugador1.getLabel();
    
        //Comprobamos que no ha ganado ninguno
        assertNull(tablero.getCellsIfWinner(jugador1.getLabel()));
        assertNull(tablero.getCellsIfWinner(jugador2.getLabel()));
        //Verificamos el empate
        assertEquals(true, tablero.checkDraw());
    }
}
