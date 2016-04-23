/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dmitriy.mamishev
 */
interface eat {

    boolean doEat();
}

interface drink extends eat {

    boolean doDrink();
}

public class TTT implements drink {

    @Override
    public boolean doEat(){
        return true;
    }

    @Override
    public boolean doDrink(){
        return true;
    }
    public static void main(String[] args) {
drink t = new TTT();
t.doDrink();

    }

}
