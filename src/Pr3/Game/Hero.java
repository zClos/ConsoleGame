package Pr3.Game;

/**
 *
 * @author User
 */
class Hero {
    private int x = 0;
    private int y = 0;
    private char name;
    
    public Hero (char name){
        this.name = name;
    }
    
    public int getHeroX(){
        return x;
    }
    
    public int getHeroY(){
        return y;
    }
    
    public void setHeroName(char name) {
        this.name = name;
    }
    
    public int getHeroName() {
        return name;
    }
    
    public void setHeroToField(char[][] field, int y, int x) {
        this.y = y;
        this.x = x;
        field[y][x] = name;
    }
    
    public void locateToField(Field gameField){
        int setX;
        int setY;
        do {
            setY = (int)(Math.random()*gameField.sizeGameField());
            setX = (int)(Math.random()*gameField.sizeGameField());
        } while (this.checkLocation(gameField.getField(), setY , setX));
        setHeroToField(gameField.getField(), setY, setX);
    }
    
    private boolean checkLocation(char[][] curField, int curY, int curX) {
        return (curField[curY][curX] != 'X');
    }
    
    private boolean moveCondition(char[][] curField, char direction){
        int checkY = this.getHeroY();
        int checkX = this.getHeroX();
        try{
            switch (direction) {
                case 'w':
                case '8':
                    checkY--;
                    return  curField[checkY][checkX] == '.';
                case 'd':
                case '6':
                    checkX++;
                    return  curField[checkY][checkX] == '.';
                case 's':
                case '2':
                    checkY++;
                    return  curField[checkY][checkX] == '.';
                case 'a':
                case '4':
                    checkX--;
                    return  curField[checkY][checkX] == '.';
                default:
                    System.out.println("Для преемещения используйте: w,a,s,d или 8,4,2,6");
                    return false;
            }
        } catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Вы пытаетесь выйти за границы поля");
            return false;
        }
    }
    
    private int nextCell(char direction, int coord){
        switch (direction) {
            case 'w':
            case '8':
                return --coord;
            case 'd':
            case '6':
                return ++coord;
            case 's':
            case '2':
                return ++coord;
            case 'a':
            case '4':
                return --coord;
            default:
                return 0;
        }
    }
    
    private boolean exitCondition(char[][] curField, char direction) {
        int curY = this.getHeroY();
        int curX = this.getHeroX();
        
        switch (direction) {
            case 'w':
            case '8':
            case 's':
            case '2':
                curY = this.nextCell(direction, curY);
                break;
            case 'd':
            case '6':
            case 'a':
            case '4':
                curX = this.nextCell(direction, curX);
                break;
        }
        
        return curField[curY][curX] == 'E';
    }
    
    private void replaceHero(char[][] curField, int curY,int curX, char direction){
        this.y = curY;
        this.x = curX;
        switch (direction) {
            case 'w':
            case '8':
                curField[curY][curX] = name;
                curField[++curY][curX] = '.';
                break;
            case 'd':
            case '6':
                curField[curY][curX] = name;
                curField[curY][--curX] = '.';
                break;
            case 's':
            case '2':
                curField[curY][curX] = name;
                curField[--curY][curX] = '.';
                break;
            case 'a':
            case '4':
                curField[curY][curX] = name;
                curField[curY][++curX] = '.';
                break;
        }
    }
    
    public boolean move(char direction, Field field){
        int curY = this.getHeroY();
        int curX = this.getHeroX();
        
        switch (direction){
            case 'w':
            case '8':
            case 's':
            case '2':
                curY = this.nextCell(direction, curY);
                break;
            case 'd':
            case '6':
            case 'a':
            case '4':
                curX = this.nextCell(direction, curX);
                break;
        }
        
        if (this.moveCondition(field.getField(), direction)) {
            this.replaceHero(field.getField(), curY, curX, direction);
            return true;
        } else if (this.exitCondition(field.getField(), direction)) {
            this.replaceHero(field.getField(), curY, curX, direction);
            System.out.println("Поздравляю! Вы нашли выход!");
            return false;
        } else {
            return true;
        }
    }
}
