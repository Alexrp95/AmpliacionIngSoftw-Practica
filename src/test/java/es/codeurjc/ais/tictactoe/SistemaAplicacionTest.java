package es.codeurjc.ais.tictactoe;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro
 */
public class SistemaAplicacionTest {
    private WebDriver webdriver1;
    private WebDriver webdriver2;
    
    
    @BeforeClass
    public static void SetUpBeforeClass(){
        //Iniciamos el set up de la instancia del navegador
        ChromeDriverManager.getInstance().setup();
        //Arrancamos la webapp
        WebApp.start();
    }
    @AfterClass
	public static void tearDownAfterClass() throws Exception {
		//Pararemos la aplicacion una vez acabados los test
		WebApp.stop();
	}
        @Before
	public void setUp() throws Exception {
		//Construimos los navegadires web y los iniciamos
		webdriver1 = new ChromeDriver();
		webdriver2 = new ChromeDriver();
		 
		webdriver1.get("http://localhost:8080/");
		webdriver2.get("http://localhost:8080/");
		
		webdriver1.findElement(By.id("nickname")).sendKeys("Jugador 1");
		webdriver2.findElement(By.id("startBtn")).click();	
		
		webdriver1.findElement(By.id("nickname")).sendKeys("Jugador 2");
		webdriver2.findElement(By.id("startBtn")).click();
	}

	@After
	public void tearDown() throws Exception {
		//Cerramos los navegadores al acabar los test
		if(webdriver1 != null)
			webdriver1.quit();
		if(webdriver2 != null)
			webdriver2.quit();
	}
        @Test
	public void testComprobacionGanador1() throws InterruptedException {
		
		//Jugamos una partida con navegador1 y navegador2
		webdriver1.findElement(By.id("cell-3")).click();
		webdriver2.findElement(By.id("cell-0")).click();
		webdriver1.findElement(By.id("cell-4")).click();
		webdriver2.findElement(By.id("cell-1")).click();
		webdriver1.findElement(By.id("cell-5")).click();
		
		try {
                    //Verificamos que se han recibido las alertas en ambos navegadores sobre el resultado de la partida
			
                    assertEquals(webdriver1.switchTo().alert().getText(),"Jugador 1 wins! Jugador 2 looses.");
                    assertEquals(webdriver2.switchTo().alert().getText(),"Jugador 1 wins! Jugador 2 looses.");
		    
	    } catch (Exception e) {
	        Thread.sleep(1000);
	    }
	}
	
	@Test
	public void testComprobacionGanador2() throws InterruptedException {
		
		//Jugamos una partida con navegador1 y navegador2
		webdriver1.findElement(By.id("cell-0")).click();
		webdriver2.findElement(By.id("cell-6")).click();
		webdriver1.findElement(By.id("cell-3")).click();
		webdriver2.findElement(By.id("cell-7")).click();
		webdriver1.findElement(By.id("cell-5")).click();
		webdriver2.findElement(By.id("cell-8")).click();
		
		try {
			//Verificamos que ambos navegadores reciben las alertas del resultado de la partida
                    assertEquals(webdriver1.switchTo().alert().getText(),"Jugador 2 gana! Jugador 1 pierde.");
                    assertEquals(webdriver2.switchTo().alert().getText(),"Jugador 2 gana! Jugador 1 pierde.");
		    
	    } catch (Exception e) {
	        Thread.sleep(1000);
	    }
	}
	
	@Test
	public void testComprobacionEmpate() throws InterruptedException {
		
		//Jugamos una partida con navegador1 y navegador2
		webdriver1.findElement(By.id("cell-0")).click();
		webdriver2.findElement(By.id("cell-1")).click();
		webdriver1.findElement(By.id("cell-2")).click();
		webdriver2.findElement(By.id("cell-3")).click();
		webdriver1.findElement(By.id("cell-4")).click();
		webdriver2.findElement(By.id("cell-5")).click();
		webdriver1.findElement(By.id("cell-6")).click();
		webdriver2.findElement(By.id("cell-7")).click();
		webdriver1.findElement(By.id("cell-8")).click();
		
		try {
			
			//Verificamos que no hay ganadores en la partida y verificamos las alertas de ambos navegadores
		    
			assertEquals(webdriver1.switchTo().alert().getText(),"Draw!");
                        assertEquals(webdriver2.switchTo().alert().getText(),"Draw!");
			
		} catch (Exception e) {
		    Thread.sleep(1000);
		}
	}
}
        

