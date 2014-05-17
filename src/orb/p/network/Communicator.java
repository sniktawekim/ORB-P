/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orb.p.network;

/**
 *
 * @author blainezor
 */
public abstract class Communicator extends Thread {

    abstract public void sendMessage(String message);

    abstract public void run();

}
