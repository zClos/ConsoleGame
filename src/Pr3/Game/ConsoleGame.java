package Pr3.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Pr3-5
 * Create a game. You have a field 10x10 cells. Your initial position is left down corner. 
 * You should follow the hero to the top right corner through the field of obstacles.
 * Use ‘w’, ’e’, ’n’, ’s’ (or alternatively '4', '6', '8', '2') symbols read from console.
 * Use all possible forms of unary increment and decrement operations to move the hero in the given directions.
 * Each time assign the result of the operation to your variable. Use a switch
 * statement a the program. 
 * Show the current position of the hero on the field in console as “X” symbol.
 * Created by Alexander Alkhimyonok on 02.05.2017.
 */

public class ConsoleGame {
    
//I was need to do a bit changes at this method cuz it can't return input {w,a,s,d} control keys
    private char getDirection() {       
        char direction = 0;

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(System.in));
            direction = br.readLine().toLowerCase().charAt(0);
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Ошибка ввода!!! Вы не ввели напраление!");
        } catch (IOException ex){
//            NOP
        }

        return direction;
    }
    
    public static void main(String[] args) {
        ConsoleGame game = new ConsoleGame();
        Field gameField = new Field(10);
        Hero exit = new Hero('E');
        Hero hero = new Hero('H');
        gameField.initGameField();
        exit.locateToField(gameField);
        hero.locateToField(gameField);
        System.out.println("Найдите выход из лабиринта\nВаше положение - Н, выход - Е\nДля преемещения используйте: w,a,s,d или 8,4,2,6 соответственно");
        gameField.showField();
        
        boolean stateMotion;
        do{
            char direction = game.getDirection();
            stateMotion = hero.move(direction, gameField);
            gameField.showField();
        } while (stateMotion);
    }
}
