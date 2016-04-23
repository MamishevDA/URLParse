/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dmitriy.mamishev
 */
interface start {

    public boolean startEngine();
}
interface prt {

    public void prtEngine();
}

class Car implements start,prt {

    Car(boolean start) {
        this.starnEngine = start;
    }

    @Override
    public boolean startEngine() {
        if (!starnEngine) {
            starnEngine = true;
        }
        return starnEngine;
    }
    @Override
    public void prtEngine(){
        System.out.println(this.starnEngine);
    }

    public void openDoors() {

    }
    private boolean starnEngine;
}

public class TI {
    public static void main(String[] args) {
        start s = new Car(false);
        s.startEngine();
        prt p = (prt) s;
        p.prtEngine();
        
        Car r = new Car(false);
        r.startEngine();
        r.prtEngine();
        r.openDoors();
    }

}
