import java.util.ArrayList;
import java.util.Random;

public class TH0001 {
    public static void main(String[] args) {

        ArrayList<Cat> arrayList = new ArrayList<>();
        Random random = new Random();
        for (int i=0; i<100; ++i) {
            Cat cat = new TH0001().new Cat();
            cat.setIndex(i);
            cat.setColor(MYCOLOR.values()[random.nextInt(11)]);
            arrayList.add(cat);
        }

        //将arraryList排序，优先保证color的顺序，其次保证index的顺序

    }

    private class Cat {
        int index;
        private MYCOLOR color;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public MYCOLOR getColor() {
            return color;
        }

        public void setColor(MYCOLOR color) {
            this.color = color;
        }
    }

    private enum MYCOLOR {
        RED,
        YELLOW,
        WHITE,
        BLACK,
        GREEN,
        BLUE,
        ORANGE,
        PURPLE,
        PINK,
        GREY,
        BROWN
    }
}