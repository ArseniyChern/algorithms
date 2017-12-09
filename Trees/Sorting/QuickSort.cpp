#include <iostream>

using namespace std;

/**

Complexity

n^2 - Worst Case

n log n - Average Case

n log n - Best Case


Array = [5,4,3,2,1]


Sorting partition [5,4,3,2,1]

start from 5 as pivot 

storeIndex = 1(4)

compare 5 and 4

swap 4 and 4

storeIndex++ - 2(3)

compare 5 and 3

swap 3 and 3

storeIndex++ - 3(2)

compare 5 and 2

swap 2 and 2 

storeIndex++ - 4(1)

compare 5 and 1

swap 1 and 1 

storeIndex++ = 5(null)

swap 5 and (storeindex - 1)(1)


Array = [1,4,3,2,5]


Sorting partition [1,4,3,2]

pivot = element 0 = 1

storeIndex = 1(4)

compare 1 to 4

compare 1 to 3

compare 1 to 2

swap 1 and (storeIndex-1)(1)


Array = [1,4,3,2,5]


Sorting partition [4,3,2]

pivot = 4 

storeIndex = 1(3)

compare 4 and 3 

swap 3 and 3

storeIndex++ = 2(2)

compare 4 and 2

swap 2 and 2

storeIndex++ = 3(null)

swap 4 and (storeIndex-1)(2)


Array = [1,2,3,4,5]


Sorting partition [2,3]

Pivot = 2

storeIndex = 1(3)

compare 2 and 3

swap 2 and 2


Array = [1,2,3,4,5]


Sorting partition [3]

length = 1 - stop;


Sorted.

**/


void display(int* arr,int n) {
   printf("[");
  
   // navigate through all items 
   for(int i = 0;i<n;i++) {
      printf("%d ",arr[i]);
   }
  
   printf("]\n");
}

int partition(int *arr, int low, int high) {
    int pivot = arr[high];
    
    int storeindex = low-1;
    
    for(int j = low;j <= high-1;j++) {
        if(arr[j] <= pivot) {

            storeindex++;
            if(storeindex != j) {
                arr[storeindex] = arr[storeindex]+arr[j];
                arr[storeindex] = arr[storeindex]-arr[j];
                arr[storeindex] = arr[storeindex]-arr[j];
            }

        }
    }
    
    if(storeindex+1 != high) {
        arr[storeindex+1] = arr[storeindex+1] + arr[high];
        arr[high] = arr[storeindex+1]-arr[high];
        arr[storeindex+1] = arr[storeindex+1]-arr[high];
    }
    
    return storeindex + 1;
}
int* quick(int *intArray, int low, int high) {
    if(low < high) {
        
        int pi = partition(intArray,low,high);
        printf("pi = %d\n ",pi);
        printf("passining in array ");
        display(intArray,10);
        
        quick(intArray,low,pi-1);
        quick(intArray,pi+1,high);
        
        
    }
   
    return intArray;
}


int main() {
    
   printf("Performing quick sort on array ");
   int length = 10;
   int arr[length];
   
   for(int i = length; i > 0; i--) {
       arr[length-i]  = i;
   }
   display(arr,length);
   int* a = quick(arr,0,length-1);
   display(a,length);
   return 0;
}
