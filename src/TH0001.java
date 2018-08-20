import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 多优先级排序，插入排序
 */
public class TH0001 {
    public static void main(String[] args) {
        List<Cat> arrayList = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        for (int i=0; i<100; ++i) {
            Cat cat = new TH0001().new Cat();
            cat.setIndex(random.nextInt(100));
            cat.setColor(MYCOLOR.values()[random.nextInt(11)]);
            arrayList.add(cat);
        }
        System.out.println(arrayList);
        //将arraryList排序，优先保证color的顺序，其次保证index的顺序
        //1. 插入排序
        for (int i=1; i<100; ++i) {
            Cat now = arrayList.get(i);
            for (int j=i-1; j>=0; --j) {
                if (now.getColor().ordinal() < arrayList.get(j).getColor().ordinal() ||
                        (now.getColor().ordinal() == arrayList.get(j).getColor().ordinal() && 
                                now.getIndex() < arrayList.get(j).getIndex())) {
                    arrayList.set(j+1, arrayList.get(j));
                } else {
                    arrayList.set(j+1, now);
                    break;
                }
                if (0 == j) {
                    arrayList.set(0, now);
                }
            }
        }
        System.out.println(arrayList);
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

        @Override
        public String toString() {
            return "[index=" + index + ", color=" + color + "]";
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