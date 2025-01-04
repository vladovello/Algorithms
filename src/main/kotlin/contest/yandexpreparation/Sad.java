package contest.yandexpreparation;

public class Sad {
    int count = 0;

    public synchronized void inc() {
        if (count < 10) {
            ++count;
            inc();
        }
    }

    public String getNull() {
        return null;
    }

    public static void main(String[] args) {
        var sad = new Sad();
        sad.inc();

        label12: {
            break label12;
        }

        System.out.println(sad.count);
    }
}
