package Feedback.Model;


public class Model {
    String status[] = {"New", "Ready", "Running", "Waiting", "Terminate"};
    private int processID;
    private int arrivalTime;
    private int waitingTime;
    private int ioTime;
    private int burstTime;
    private int index;
    private int turnaroundTime;
    private int timeQuantum;
    private int queue;


    public Model() {
    }

    public Model(int processID, int index, int arrivalTime, int queue, int timeQuantum) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.index = index;
        this.queue = queue;
        this.timeQuantum = timeQuantum;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public int getTimeQuantum() {
        return timeQuantum;
    }

    public void setTimeQuantum(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public String getStatus() {
        return status[index];
    }

    public void setStatus(int index) {
        this.index = index;
    }

    public int getProcessID() {
        return processID;
    }

    public void setProcessID(int processID) {
        this.processID = processID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getIoTime() {
        return ioTime;
    }

    public void setIoTime(int ioTime) {
        this.ioTime = ioTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

//    boolean getStatusProcess() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
