#include <iostream>
using namespace std;

void display(int* arr,int n) {
   printf("[");
  
   for(int i = 0;i<n;i++) {
      printf("%d ",arr[i]);
   }
  
   printf("]\n");
}

int* merge(int arr[], int l, int m, int r)
{

	//l = 0 m = 4 r = 9 (last call)

	//n1 = 5 n2 = 5 (last call) = length of arrays
    int n1 = m - l + 1;
    int n2 =  r - m;
 
    int L[n1], R[n2];
 
 	//Splitting into 2 separate arrays, left and right
    for (int i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (int j = 0; j < n2; j++)
        R[j] = arr[m + 1+ j];
 
    // Merge the temp arrays back into arr[l..r]

    int i = 0; // Initial index of first subarray
    int j = 0; // Initial index of second subarray
    int k = l; // Initial index of merged subarray

    while (i < n1 && j < n2)
    {
        if (L[i] <= R[j])
        {
            arr[k] = L[i];
            i++;
        }
        else
        {
            arr[k] = R[j];
            j++;
        }
        k++;
    }
 
    // Copy the remaining elements of L[], if there are any 
    while (i < n1)
    {
        arr[k] = L[i];
        i++;
        k++;
    }
 
    // Copy the remaining elements of R[], if there are any 
    while (j < n2)
    {
        arr[k] = R[j];
        j++;
        k++;
    }
    
    return arr;
}
 
// l is for left index and r is right index of the sub-array of arr to be sorted 
int* mergeSort(int arr[], int l, int r)
{

    if (l < r)
    {
        
        int m = (l+r)/2;
 
        //l = 0, m = 4 r = 9 (1st iter)
        mergeSort(arr, l, m);
        mergeSort(arr, m+1, r);
 
        merge(arr, l, m, r);
    }
    return arr;
}

int main() {
    int length = 10;
    
    int a[length];
    
    for(int i = length; i > 0;i--) {
        a[length-i] = i;
    }
    
	mergeSort(a,0,length-1);
	display(a,length);
	return 0;
}