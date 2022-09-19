package homework;

import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList {
    private static final int INITIAL_SIZE = 15;
    private Integer[] data;
    private int capacity;

    public IntegerListImpl() {
        data = new Integer[INITIAL_SIZE];
        capacity = 0;
    }

    public IntegerListImpl(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Размер списка должен быть больше нуля!");
        }
        data = new Integer[i];
        capacity = 0;
    }

    @Override
    public Integer add(Integer item) {
        return add(capacity, item);
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index < 0 || index > capacity) {
            throw new IllegalArgumentException("Некорректный индекс!");
        }
        if (capacity == data.length) {
            Grow();
        }
        if (item == null) {
            throw new IllegalArgumentException("Индекс не должен быть null!");
        }
        if (index < capacity) {
            System.arraycopy(data, index, data, index + 1, capacity - index);
        }
        data[index] = item;
        capacity++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (index < 0 || index >= capacity) {
            throw new IllegalArgumentException("Некорректный индекс!");
        }
        if (item == null) {
            throw new IllegalArgumentException("Индекс не должен быть null!");
        }
        return data[index] = item;
    }

    @Override
    public void Grow() {
        Integer[] dataCopy = Arrays.copyOf(data, (int) (data.length * 1.5));
        data = dataCopy;

    }

    @Override
    public Integer remove(Integer item) {
        int indexForRemoving = indexOf(item);
        if (indexForRemoving < 0) {
            throw new IllegalArgumentException("Такого элемента не существует!");
        }
        return remove(indexForRemoving);

    }

    @Override
    public Integer remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Некорректный индекс!");
        }
        if (index >= capacity) {
            throw new IllegalArgumentException("Индекс: " + index + ", Размер: " + capacity);
        }
        Integer removed = data[index];
        System.arraycopy(data, index + 1, data, index, capacity - 1 - index);
        data[--capacity] = null;
        return removed;
    }

    @Override
    public boolean contains(Integer item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Нельзя добавить null!");
        }
        Integer[] arrayForSearch = toArray();
        sortInsertion(arrayForSearch);

        int min = 0;
        int max = arrayForSearch.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(arrayForSearch[mid])) {
                return true;
            }
            if (item < arrayForSearch[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Индекс не должен быть null!");
        }
        for (int i = 0; i < capacity; i++) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Индекс не должен быть null!");
        }
        for (int i = capacity; i >= 0; i++) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index >= capacity) {
            throw new IllegalArgumentException("Некорректный индекс!");
        }
        return data[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null || size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < capacity; i++) {
            if (!get(i).equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(data, null);
        capacity = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] array = new Integer[capacity];
        System.arraycopy(data, 0, array, 0, capacity);
        return array;
    }

    private void sortInsertion(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;
    }
    private static void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
    }
