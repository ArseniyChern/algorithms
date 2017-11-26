#include <iostream>

using namespace std;

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
