package Lab12.Elevator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InternalPanelAgent extends Thread {

    BlockingQueue<InternalCall> input = new ArrayBlockingQueue<>(100);
    ElevatorCar elevatorCar;

    static class InternalCall{
        private final int toFloor;
        InternalCall(int toFloor){
            this.toFloor = toFloor;
        }
    }

    InternalPanelAgent(ElevatorCar elevatorCar){
        this.elevatorCar = elevatorCar;
    }

    public void run(){
        for(;;){
            InternalCall ic = null;
            try {
                ic = input.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (ic != null && ic.toFloor == elevatorCar.getFloor()) continue;
            if(ic != null && ic.toFloor > elevatorCar.getFloor()){
                ElevatorStops.get().setLiftStopUp(ic.toFloor);
            }else{
                ElevatorStops.get().setLiftStopDown(ic.toFloor);
            }
        }
    }

}