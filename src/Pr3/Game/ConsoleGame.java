package Pr3.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author User
 */
public class ConsoleGame {
    
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
        char dir = game.getDirection();
        stateMotion = hero.move(dir, gameField);
        gameField.showField();
        } while (stateMotion);
    }
}
