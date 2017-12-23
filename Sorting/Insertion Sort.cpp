#include <iostream>

using namespace std;

/**

Complexity = O(n + f(n)) where f(n) is the inversion count ~ O(n^2)

[5,4,3]

Here, inversion count is 3, so complexity will be n + 3, or 6. 

For worst case scenario inversion count is equal to (n(n-1))/2, in this case that is (3(3-1))/2 = 3:

Algorithm Steps:

check 4 & 5 - 1

swap 4 & 5 - 2
[4,5,3]

check 3 & 5 - 3

swap 3 & 5 - 4
[4,3,5]

check 3 & 4 - 5

swap 3 and 4 - 6
[3,4,5]

O(n + f(n)) = (3+3) = 6 ~ n^2 = 9

**/

void display(int* arr,int n) {
   int i;
   printf("[");
  
   // navigate through all items 
   for(i = 0;i<n;i++) {
      printf("%d ",arr[i]);
   }
  
   printf("]\n");
}

int* insertion(int *intArray, int n) {
    
   int valueToInsert;
   int holePosition;
   int i;
  
   for(i = 1; i < n; i++) { 
  
      valueToInsert = intArray[i];
    
      holePosition = i;
    
      while (holePosition > 0 && intArray[holePosition-1] > valueToInsert) {
         intArray[holePosition] = intArray[holePosition-1];
         holePosition--;
      }

      if(holePosition != i) {
         intArray[holePosition] = valueToInsert;
      }

    
   }  
    return intArray;
}


int main() {
    
   printf("Performing insertion sort\n");
   int length = 100;
   int arr[length];
   
   for(int i = length; i > 0; i--) {
       arr[length-i]  = i;
   }
   
   int* a = insertion(arr,length);
   display(a,length);
   return 0;
}
