package Heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// min heap
public class Heap<T extends Comparable<T>> {
    private List<T> list;
    Heap(){
        list = new ArrayList<>();
    }

    Heap(List<T> list){
        this.list = new ArrayList<>(list);
    }

    void swap(int i, int j){
        T temp = list.get(i);
        list.set(i,list.get(j));
        list.set(j,temp);
    }

    int parent(int i){
        return (i-1)/2;
    }

    int left(int i){
        return (2*i)+1;
    }

    int right(int i){
        return (2*i) + 2;
    }

    public void insert(T val){
        list.add(val);
        upheap(list.size()-1);
    }

    private void upheap(int i) {
        if(i == 0){
            return;
        }
        var p = parent(i);
        if(list.get(i).compareTo(list.get(p)) < 0){
            swap(i,p);
            upheap(p);
        }
    }

    public T remove() throws Exception {
        if (list.isEmpty()) {
            throw new Exception("Removing from an empty heap!");
        }

        T val = list.get(0);
        T last = list.remove(list.size()-1);

        if(!list.isEmpty()){
            list.set(0,last);
            downheap(0);
        }
        return val;
    }

    public void downheap(int i) {

        int min = i;
        int l = left(i);
        int r = right(i);

        if(l<list.size() && list.get(min).compareTo(list.get(l)) > 0){
            min = l;
        }
        if(r<list.size()  && list.get(min).compareTo(list.get(r)) > 0){
            min = r;
        }

        if(min != i){
            swap(min,i);
            downheap(min);
        }

    }

    public List<T> heapSort() throws Exception {
        List<T> ans= new ArrayList<>();
        while (!list.isEmpty()){
            ans.add(remove());
        }
        return ans;
    }

    public List<T> sort(){
        int start = (list.size() - 2)/2;
        for (int i = start; i>=0;i--){
            downheap(i);
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
            var heap = new Heap<>(Arrays.asList(12,2,3,7,9,8,10));
            System.out.println(heap.sort());



            System.out.println(heap.heapSort());


//        var heap = new Heap<Integer>();
//        heap.insert(34);
//        heap.insert(45);
//        heap.insert(22);
//        heap.insert(89);
//        heap.insert(76);
//
//        List<Integer> list = heap.heapSort();
//        System.out.println(list);
    }
}
