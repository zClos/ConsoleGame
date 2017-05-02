package Pr3.Game;


/**
 * Pr3-5 (Support class) 
 * It's described activity & data of game field. 
 * Created by Alexander Alkhimyonok on 02.05.2017.
 */

class Field {
    private char[][] field;
    private int blockDensity = 4;
    
    public Field(){
        this(10);
    }
    public Field(int fieldSize){
        this.field = new char[fieldSize][fieldSize];
    }
    
    public void showField(){
        System.out.println("Текущее состояние поля");
        for (char[] field1 : field) {
            for (int x = 0; x < field1.length; x++) {
                System.out.print(field1[x] + " ");
            }
            System.out.println("");
        }
    }
    
    public void initGameField(){
        for (char[] field1 : field) {
            for (int x = 0; x < field1.length; x++) {
                field1[x] = this.randomBarrier();
            }
        }
    }
    
    private char randomBarrier(){
        return ((int)(Math.random()*blockDensity) == 0) ? 'X' : '.' ;
    }
    
    public char[][] getField(){
        return field;
    }
    
    public int sizeGameField(){
        return field.length;
    }
    
    
}
