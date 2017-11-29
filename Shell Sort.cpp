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

int* shell(int *arr, int n) {
   int gap = n/2;
      
  while(gap > 0) {
          
      for(int i = gap; i < n;i++) {
              
       int temp = arr[i];
              
       int j = i;
              
       while (j >= gap && arr[j-gap] > temp ) {
         arr[j] = arr[j-gap];
         j -= gap;
       }
              
       arr[j] = temp;
              
      }

      gap=gap/2;
    }
   
   return arr;
}

int main()
{
    
   int arr[] = {8,7,6,5,4,3,2,1};
   
   display(shell(arr,8),8);
   
}

