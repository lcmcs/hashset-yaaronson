package edu.touro.cs.mcon364;

import java.util.*;

public class Main implements Set<String> {
    int initialCapacity = 16;
    float loadFactor = .75;

    public Main(int initialCapacity, float loadFactor ) {
        if (initialCapacity < 0){
            throw new IllegalArgumentException("capacity too small");
        }
        if (loadFactor < 0 || loadFactor > 75){
            throw new IllegalArgumentException("load factor not good");
        }
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
    }
    public Main() {

    }
    private int size = 0;
    //List<String>[] list = new List<String>[initialCapacity];
    List[] list1 = new List[initialCapacity];

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
           if (o.getClass() != String.class){
               return false;
           }
           String x = (String)o;
           int index = x.hashCode()%initialCapacity;
           List one = list1[index];
           if (one == null){
            return false;
        }
            if (one.contains(x)){
                return true;
        }

        return false;
    }

    @Override
    public Iterator<String> iterator() {
        return new MyHashSetIterator();
    }

    @Override
    public Object[] toArray() {
        String[] copy = new String[size];
        Iterator<String> w = iterator();
        int currentPlaceOf = 0;
        while( w.hasNext()){
            copy[currentPlaceOf] = (String) w.next();
            currentPlaceOf++;
        }
        return copy;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(String s) {
        if (size/((double)initialCapacity) >= loadFactor){
            resize(2 * initialCapacity +1);
        }
        int element;
        if (s == null){
            element = 0;
        }
        else {
            element = s.hashCode() % initialCapacity;
        }
        if (list1[element] == null) {
            list1[element] = new ArrayList<String>();
        }
        if (list1[element].contains(s)) {
            return false;
        }
        list1[element].add(s);
        size++;
        return true;
    }
    private void resize(int i){
        List [] capacity = new List[i];
        Iterator w = iterator();
        while( w.hasNext()){
            String currentElement =  (String) w.next();
            int element = currentElement.hashCode()%capacity.length;
            if (capacity[element]==null){
                capacity[element]= new ArrayList<String>();
            }
            capacity[element].add(currentElement);
        }
        initialCapacity = i;
        list1 = capacity;
    }

    public boolean remove(Object o) {
        List element;
        if (o == null){
            element = list1[0];
        }
        else {
        element =   list1[o.hashCode() % initialCapacity];
        }
        boolean x =  element.remove(o);
        if (x == true){
            size--;
        }
        return x;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }
    private class MyHashSetIterator implements Iterator<String>{
        private int MainArray = 0; //main array
        private int currList = 0; // buckets
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if (size() - currentIndex == 0) {
                return false;
            }
            return true;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            while (list1[MainArray] == null) { //index where we at rn
                MainArray++;
            }

            List bucket = list1[MainArray]; //where we are in main array and we know it is not null
            if (currList >= bucket.size()) { //if we are at end of one array lists move to next
                MainArray++;
                currList = 0;
                while (list1[MainArray] == null) {
                    MainArray++;
                }
                 bucket = list1[MainArray]; //moves this to where we are after we moved it
            } // all this to make sure we in right place

            T currentPlace = (T) bucket.get(currList);
            elementsReturned++;
            currList++;
            return currentPlace;
    }

    @Override
    public void clear() {
       size = 0;
       for (int i =0; i < list1.length; i++){
            list1[i] = null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Main strings = (Main) o;
        return initialCapacity == strings.initialCapacity && Float.compare(strings.loadFactor, loadFactor) == 0 && size == strings.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(initialCapacity, loadFactor);
    }

}
