package solutions.KthLargestElementinaStream;

/*

int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8

*/

class KthLargest {

    int[] heap;

    public KthLargest(int k, int[] nums) {
        heap = new int[k];
        for(int i = 0; i < k; i++){
            if (i < nums.length){
                heap[i] = nums[i];
            } else {
                heap[i] = Integer.MIN_VALUE;
            }
        }
        for (int i = k/2; i >= 0; i--){ // heapify from the end
            heapify(heap, i);
        }
        for (int i = k; i < nums.length; i++){
            add(nums[i]);
        }
    }
    
    public int add(int val) {
        if (heap[0] < val) {
            heap[0] = val;
            heapify(heap, 0);
        }
        return heap[0];
    }

    public void heapify(int[] heap, int i) {
        int l = i * 2 + 1, r = l + 1, small = i;

        if (l < heap.length && heap[small] > heap[l]){
            small = l;
        }
        if (r < heap.length && heap[small] > heap[r]){
            small = r;
        }
        if (small != i){
            swap(heap, small, i);
            heapify(heap, small);
        }
    }

    private void swap(int[] arr, int i,  int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
}

