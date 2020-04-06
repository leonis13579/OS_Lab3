import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static class MyThread extends Thread {
        private int sleepTime;
        private int num;
        private boolean status;

        MyThread(int num, int sleepTime) {
            this.num = num;
            this.sleepTime = sleepTime;
            status = true;
        }

        @Override
        public void run() {
            System.out.println(num + " поток начал работу");

            try {
                sleep(sleepTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("\t" + num + " поток закончил работу");
            status = false;
        }
    }

    public static MyThread[] Threads = new MyThread[5];

    public static boolean check() {
        boolean answer = true;

        for (MyThread thread : Threads) {
            if (thread.status) {answer = false;}
        }

        return answer;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        boolean work = true;

        while (work) {
            System.out.println("Введите время работы потоков");

            for (int i = 1; i < 6; i++) {
                System.out.println("Время работы " + i + " потока - ");
                Threads[i - 1] = new MyThread(i, in.nextInt());
            }

            System.out.println("\n");

            for (int i = 0; i < 5; i++) {
                Threads[i].start();
                Thread.sleep(1);
            }

            while (!check()){}

            System.out.println("Повторить запуск потоков (да) или остановить работу программы (нет)?");
            String answ = in.next();
            if (answ.equals("нет") || answ.equals("НЕТ")) {
                work = false;
            }

        }
    }
}

