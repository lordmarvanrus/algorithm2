package homework;

public interface IntegerList {
        static int partition(int[] arr, int begin, int end);

        Integer add(Integer item);

        Integer add(int index, Integer item);

        Integer set(int index, Integer item);

        void Grow();

        Integer remove(Integer item);

        Integer remove(int index);

        boolean contains(Integer item);

        int indexOf(Integer item);

        int lastIndexOf(Integer item);

        Integer get(int index);

        boolean equals(IntegerList otherList);

        int size();

        boolean isEmpty();

        void clear();

        Integer[] toArray();
}
