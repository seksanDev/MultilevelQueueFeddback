package Feedback.Controller;

import Feedback.Model.Model;

import java.util.ArrayList;

public class Controller {
    Model model = new Model();
    ArrayList<Model> jobQueue = new ArrayList<Model>();
    ArrayList<Model> terminateQueue = new ArrayList<Model>();
    ArrayList<Model> queue1 = new ArrayList<Model>();
    ArrayList<Model> queue2 = new ArrayList<Model>();
    ArrayList<Model> queue3 = new ArrayList<Model>();
    ArrayList<Model> cdQueue = new ArrayList<Model>();
    int pId = 1;
    int timeQuantum = 0;
    int burstTime = 0;
    int resetTimeQuantum;
    int waitingTime = 0;
    int ioTime = 0;
    double avgWaitingTime = 0;
    double avgTurnaroundTime = 0;

    public Controller() {
    }

    ////////////////////////////////////////addProcess////////////////////////////////////////////////////////////////////////////
    public void addProcess(int clock, int timeQuantumQ1, int timeQuantumQ2) {
        model = new Model(pId, 0, clock, 1, timeQuantumQ1);
        resetTimeQuantum = timeQuantumQ2;
        pId++;
        jobQueue.add(model);
        queue1.add(model);
    }
    ////////////////////////////////////////process////////////////////////////////////////////////////////////////////////////

    public void queue1() {
        try {
            for (int i = 0; i < jobQueue.size(); i++) {
                if (queue1.get(0) == jobQueue.get(i)) {
                    timeQuantum = jobQueue.get(i).getTimeQuantum();
                    timeQuantum--;
                    jobQueue.get(i).setTimeQuantum(timeQuantum);

                    burstTime = jobQueue.get(i).getBurstTime();
                    burstTime++;
                    jobQueue.get(i).setBurstTime(burstTime);
                    jobQueue.get(i).setStatus(2);

                    if (jobQueue.get(i).getTimeQuantum() == 0) {
                        jobQueue.get(i).setStatus(1);
                        jobQueue.get(i).setTimeQuantum(resetTimeQuantum);
                        jobQueue.get(i).setQueue(jobQueue.get(i).getQueue() + 1);
                        queue2.add(jobQueue.get(i));
                        queue1.remove(0);

                    }
                } else if (jobQueue.get(i).getStatus() != "Waiting") {
                    jobQueue.get(i).setStatus(1);
                }
            }
        } catch (java.lang.IndexOutOfBoundsException e) {

        }
    }

    public void queue2() {
        try {
            for (int i = 0; i < jobQueue.size(); i++) {
                if (queue2.get(0) == jobQueue.get(i)) {
                    timeQuantum = jobQueue.get(i).getTimeQuantum();
                    timeQuantum--;
                    jobQueue.get(i).setTimeQuantum(timeQuantum);

                    burstTime = jobQueue.get(i).getBurstTime();
                    burstTime++;
                    jobQueue.get(i).setBurstTime(burstTime);
                    jobQueue.get(i).setStatus(2);

                    if (jobQueue.get(i).getTimeQuantum() == 0) {
                        jobQueue.get(i).setStatus(1);
                        jobQueue.get(i).setQueue(jobQueue.get(i).getQueue() + 1);
                        queue3.add(jobQueue.get(i));
                        queue2.remove(0);
                    }
                } else if (jobQueue.get(i).getStatus() != "Waiting") {
                    jobQueue.get(i).setStatus(1);
                }
            }
        } catch (java.lang.IndexOutOfBoundsException e) {

        }
    }

    public void queue3() {
        try {
            for (int i = 0; i < jobQueue.size(); i++) {
                if (queue3.get(0) == jobQueue.get(i)) {
                    jobQueue.get(i).setStatus(2);
                    burstTime = jobQueue.get(i).getBurstTime();
                    burstTime++;
                    jobQueue.get(i).setBurstTime(burstTime);
                } else if (jobQueue.get(i).getStatus() != "Waiting") {
                    jobQueue.get(i).setStatus(1);
                }
            }
        } catch (java.lang.IndexOutOfBoundsException e) {

        }
    }

    public void running() {
        try {
            if (!queue1.isEmpty()) {
                queue1();
            }
            if (queue1.isEmpty() && !queue2.isEmpty()) {
                queue2();
            }
            if (queue1.isEmpty() && queue2.isEmpty() && !queue3.isEmpty()) {
                queue3();
            }

        } catch (java.lang.IndexOutOfBoundsException e) {

        }
    }

    public void removeQueue(int clock) {
        try {
            for (int i = 0; i < jobQueue.size(); i++) {
                if (!queue1.isEmpty()) {
                    if (queue1.get(0).getStatus() == "Running") {
                        if (jobQueue.get(i) == queue1.get(0)) {
                            jobQueue.get(i).setStatus(4);
                            jobQueue.get(i).setTurnaroundTime(clock);
                            avgTurnaroundTime(jobQueue.get(i).getTurnaroundTime());
                            avgWaitingTime(jobQueue.get(i).getWaitingTime());
                            terminateQueue.add(jobQueue.get(i));
                            queue1.remove(0);
                            jobQueue.remove(i);
                        }
                    }
                }
                if (!queue2.isEmpty()) {
                    if (queue2.get(0).getStatus() == "Running") {
                        if (jobQueue.get(i) == queue2.get(0)) {
                            jobQueue.get(i).setStatus(4);
                            jobQueue.get(i).setTurnaroundTime(clock);
                            avgTurnaroundTime(jobQueue.get(i).getTurnaroundTime());
                            avgWaitingTime(jobQueue.get(i).getWaitingTime());
                            terminateQueue.add(jobQueue.get(i));
                            queue2.remove(0);
                            jobQueue.remove(i);
                        }
                    }
                }
                if (!queue3.isEmpty()) {

                    if (queue3.get(0).getStatus() == "Running") {
                        if (jobQueue.get(i) == queue3.get(0)) {
                            jobQueue.get(i).setStatus(4);
                            jobQueue.get(i).setTurnaroundTime(clock);
                            avgTurnaroundTime(jobQueue.get(i).getTurnaroundTime());
                            avgWaitingTime(jobQueue.get(i).getWaitingTime());
                            terminateQueue.add(jobQueue.get(i));
                            queue3.remove(0);
                            jobQueue.remove(i);
                        }
                    }
                }
            }

        } catch (java.lang.IndexOutOfBoundsException e) {

        }
    }

    public void waitingTime() {
        for (int i = 0; i < jobQueue.size(); i++) {
            if (jobQueue.get(i).getStatus() == "Ready") {
                waitingTime = jobQueue.get(i).getWaitingTime();
                waitingTime++;
                jobQueue.get(i).setWaitingTime(waitingTime);

            }
        }
    }

    public void avgWaitingTime(int waitingTime) {
        this.avgWaitingTime = this.avgWaitingTime + waitingTime;
    }

    public String getAvgWaitingTime() {
        if (terminateQueue.isEmpty()) {
            return "0";
        } else
            return String.format("%.2f", avgWaitingTime / terminateQueue.size());
    }

    public void avgTurnaroundTime(int turnaroundTime) {
        this.avgTurnaroundTime = this.avgTurnaroundTime + turnaroundTime;
    }

    public String getAvgTurnaroundTime() {
        if (terminateQueue.isEmpty()) {
            return "0";
        } else
            return String.format("%.2f", avgTurnaroundTime / terminateQueue.size());
    }

    /////////////////////////////////////////////IO///////////////////////////////////////////////////////////////
    public void cdQueue() {
        try {
            ioTime = cdQueue.get(0).getIoTime();
            ioTime++;
            cdQueue.get(0).setIoTime(ioTime);
        } catch (java.lang.IndexOutOfBoundsException e) {

        }
    }

    public void addCdQueue() {
        try {
            if (!queue1.isEmpty()) {
                for (int i = 0; i < queue1.size(); i++) {
                    if (queue1.get(i).getStatus() == "Running") {
                        queue1.get(i).setStatus(3);
                        cdQueue.add(queue1.get(i));
                        queue1.remove(i);
                        break;
                    }
                }
            }
            if (!queue2.isEmpty()) {
                for (int i = 0; i < queue2.size(); i++) {
                    if (queue2.get(i).getStatus() == "Running") {
                        queue2.get(i).setStatus(3);
                        cdQueue.add(queue2.get(i));
                        queue2.remove(i);
                        break;
                    }
                }
            }
            if (!queue3.isEmpty()) {
                for (int i = 0; i < queue3.size(); i++) {
                    if (queue3.get(i).getStatus() == "Running") {
                        queue3.get(i).setStatus(3);
                        cdQueue.add(queue3.get(i));
                        queue3.remove(i);
                        break;
                    }
                }
            }
        } catch (java.lang.IndexOutOfBoundsException e) {

        }
    }

    public void endCdQueue() {
        try {
            if (cdQueue.get(0).getQueue() == 1) {
                cdQueue.get(0).setStatus(1);
                queue1.add(cdQueue.get(0));
                cdQueue.remove(0);
            } else if (cdQueue.get(0).getQueue() == 2) {
                cdQueue.get(0).setStatus(1);
                queue2.add(cdQueue.get(0));
                cdQueue.remove(0);
            } else {
                cdQueue.get(0).setStatus(1);
                queue3.add(cdQueue.get(0));
                cdQueue.remove(0);
            }
        } catch (java.lang.IndexOutOfBoundsException e) {

        }
    }

    public void waitingTimeCdQueue() {
        for (int i = 1; i < cdQueue.size(); i++) {
            if (cdQueue.get(i).getStatus() == "Waiting") {
                waitingTime = cdQueue.get(i).getWaitingTime();
                waitingTime++;
                cdQueue.get(i).setWaitingTime(waitingTime);

            }
        }
    }

    ////////////////////////////////////////Show////////////////////////////////////////////////////////////////////////////

    public String showJobQueue() {
        String text = "";
        for (int index = 0; index < jobQueue.size(); index++) {
            text = text + jobQueue.get(index).getProcessID() + " ";
            text = text + jobQueue.get(index).getBurstTime() + " ";
            text = text + jobQueue.get(index).getArrivalTime() + " ";
            text = text + jobQueue.get(index).getWaitingTime() + " ";
            text = text + jobQueue.get(index).getStatus() + " ";
            text = text + jobQueue.get(index).getIoTime() + " ";
            text = text + jobQueue.get(index).getQueue() + " ";
            text = text + ",";
        }
        return text;
    }

    public String showQueue1() {
        String text = "";
        for (int index = 0; index < queue1.size(); index++) {
            text = text + queue1.get(index).getProcessID() + " ";
            text = text + queue1.get(index).getStatus() + " ";
            text = text + ",";
        }
        return text;
    }

    public String showQueue2() {
        String text = "";
        for (int index = 0; index < queue2.size(); index++) {
            text = text + queue2.get(index).getProcessID() + " ";
            text = text + queue2.get(index).getStatus() + " ";
            text = text + ",";
        }
        return text;
    }

    public String showQueue3() {
        String text = "";
        for (int index = 0; index < queue3.size(); index++) {
            text = text + queue3.get(index).getProcessID() + " ";
            text = text + queue3.get(index).getStatus() + " ";
            text = text + ",";
        }
        return text;
    }

    public String showTerminateQueue() {
        String text = "";
        for (int index = 0; index < terminateQueue.size(); index++) {
            text = text + terminateQueue.get(index).getProcessID() + " ";
            text = text + terminateQueue.get(index).getStatus() + " ";
            text = text + terminateQueue.get(index).getTurnaroundTime() + " ";
            text = text + terminateQueue.get(index).getWaitingTime() + " ";
            text = text + ",";
        }
        return text;
    }


    public String showCdQueue() {
        String text = "";
        for (int index = 0; index < cdQueue.size(); index++) {
            text = text + cdQueue.get(index).getProcessID() + " ";
            text = text + cdQueue.get(index).getStatus() + " ";
            text = text + cdQueue.get(index).getIoTime() + " ";
            text = text + cdQueue.get(index).getWaitingTime() + " ";
            text = text + ",";
        }
        return text;
    }
}