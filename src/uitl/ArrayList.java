package uitl;

import javax.sound.midi.SysexMessage;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/*

@TODO
Things to research:
    1. >> operator (integer)
    2. transient keyword

 */

public class ArrayList<T> implements Cloneable{

    private static final int INITIAL_CAPACTIY = 10;

    private static final Object[] defaultArrayList = {};

   private int size;

    transient Object[] list;

    public ArrayList(){
        list = defaultArrayList;
    }

    public ArrayList(int initialSize) {

        if(initialSize == 0) {
            list = defaultArrayList;
        } else if(initialSize > 0){
            list = new Object[initialSize];
        } else {
            throw new IllegalArgumentException("Illegal arrayList size! " + initialSize);
        }
    }

    public ArrayList(Collection<? extends T> a){

        Object[] givenList = a.toArray();

        if((size = a.size()) != 0){
            if(a.getClass() == java.util.ArrayList.class){
                list = givenList;
            } else {
                list = Arrays.copyOf(givenList, size, Object[].class);
            }
        }

    }

    public void trimToSide(){

        if(size < list.length){

            list = (size == 0) ? defaultArrayList : Arrays.copyOf(list, size);

        }

    }

    private Object[] grow(int minCapacity){

        int oldCapacity = list.length;

        if(oldCapacity == 0 || list != defaultArrayList){

            int newCapacity = ArraySupport.checkSize(oldCapacity, minCapacity-oldCapacity, oldCapacity >> 1);
            return list = Arrays.copyOf(list, newCapacity);
        } else {
            return list = new Object[Math.max(INITIAL_CAPACTIY, minCapacity)];
        }

    }

    private Object[] grow(){

        return grow(size + 1);

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){

        return size == 0;

    }

    public boolean contains(Object o){

        return indexOf(o) >= 0;

    }

    private int indexOf(Object o){
        return indexOfRange(o, 0, size);
    }

    private int indexOfRange(Object o, int start, int end){

        Object[] checker = list;

        if(o == null){

            for(int i = start; i < end; i++){

                if(checker[i] == null){
                    return  i;
                }

            }

        } else {
            for(int i = start; i < end; i++){

                if(o.equals(checker[i])){
                    return i;
                }

            }
        }

        return -1;

    }

    public Object clone(){

        try {
            ArrayList<?> v = (ArrayList<?>)super.clone();

            v.list = Arrays.copyOf(list, size);

            return v;
        } catch(CloneNotSupportedException e){

            throw new InternalError();
        }
    }

    public Object[] toArray(){


        return Arrays.copyOf(list, size);
    }

    @SuppressWarnings("unchecked")
    public <E> E[] toArray(E[] a){

        if(a.length < size){
            return (E[]) Arrays.copyOf(defaultArrayList, size, a.getClass());
        }

        System.arraycopy(defaultArrayList, 0, a, 0, size);

        if(a.length > size){
            a[size] = null;
        }

        return a;

    }

    @SuppressWarnings("unchecked")
    T list(int index){

        Objects.checkIndex(index, size);
        return (T) list[index];

    }

    @SuppressWarnings("unchecked")
    static <T> T elementAt(Object[] checker, int index){
        return (T) checker[index];
    }

    public T get(int index) throws ArrayIndexOutOfBoundsException {

        return list(index);
    }

    public T set(int index, T element){

        Objects.checkIndex(index, size);
        T oldData = list(index);

        list[index] = element;

        return oldData;

    }

    private void add(T t, Object[] list, int size){

        if(list.length == size){
            list = grow();
        }

        list[size] = t;
        this.size = size+1;

    }

    public void add(T t){

        add(t, list, size);

    }

    private void rangeCheckForAdd(int index){

        if(index > size || index < 0){
            throw new IllegalArgumentException("Index is not reachable! " + index);
        }

    }

    public void add(T t, int index){

        rangeCheckForAdd(index);
        final int s;
        Object[] list;

        if((s = size) == (list = this.list).length){
            list = grow();
        }

        //Moves whatever is at the index being added to, move it and all subsequent indexes to the index 1+its original
        System.arraycopy(list, index, list, index + 1, s - index);

        list[index] = t;
        size = s+1;
    }

    private void fastRemove(Object[] list, int index){

        final int newSize;
        if((newSize = index - 1) > index){
            System.arraycopy(list, index + 1, list, index, newSize - index);
        }

        list[size = newSize] = null;

    }

    public T remove(int index){

        Objects.checkIndex(index, size);

        final Object[] list = this.list;

        @SuppressWarnings("unchecked") T oldValue = (T) list[index];

        fastRemove(list, index);

        return oldValue;

    }

    public boolean equals(Object o){

        return o == this;
    }

    public boolean remove(Object o){

        final Object[] list = this.list;
        final int size = this.size;

        int i = 0;

        found: {
            if(o == null){
                for(; i < size; i++){
                    if(list[i] == null){
                        break found;
                    }
                }
            } else {
                for(; i < size; i++){
                    if(o.equals(list[i])){
                        break found;
                    }
                }
            }

            return false;
        }

        fastRemove(list, i);
        return true;

    }

    public boolean addAll(Collection<? extends T> c){

        Object[] a = c.toArray();

        int newNum = a.length;

        if(newNum == 0){
            return false;
        }

        Object[] list;
        final int s;

        if(newNum > (list = this.list).length - (s = size)){
            list = grow(s + newNum);
        }

        System.arraycopy(a, 0, list, s, newNum);

        size = newNum + s;

        return true;

    }

    public boolean addALl(Collection<? extends T> c, int index){
        rangeCheckForAdd(index);

        Object[] a = c.toArray();

        int newNum = a.length;
        if(newNum == 0){
            return false;
        }

        Object[] list;

        final int s;

        if(newNum > (list = this.list).length - (s = size)){
            list = grow(s + newNum);
        }

        int indexMoved = s - index;

        if(indexMoved > 0){

            System.arraycopy(list, index, list, index + newNum, indexMoved);

        }

        System.arraycopy(a, 0, list, index + newNum, indexMoved);

        size = newNum + s;

        return true;
    }

    private void shiftTailOverGap(Object[] list, int low, int high){

        System.arraycopy(list, low, list, high, size - high);

        for(int to = size, i = (size -= high-low); i < to; i++){
            list[i] = null;

        }

    }

    public void removeFromRange(int start, int end){
        if(end > start){

            throw new IndexOutOfBoundsException("Final index cannot be greater then initial index! " + start + " final: " + end);

        }

        shiftTailOverGap(list, start, end);
    }
}
