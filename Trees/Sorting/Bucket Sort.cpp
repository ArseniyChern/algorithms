#include < iostream >
#include < vector >
#include < algorithm >

using namespace std;

/**

Bucket sort



*/

void display(int * arr, int n) {
  int i;
  printf("[");

  for (i = 0; i < n; i++) {
    printf("%d ", arr[i]);
  }

  printf("]\n");
}



int * bucket(int * data, int count) {
  int minValue = data[0];
  int maxValue = data[0];

  for (int i = 1; i < count; i++) {
    if (data[i] > maxValue) {
      maxValue = data[i];
    }
    if (data[i] < minValue) {
      minValue = data[i];
    }
  }

  int bucketLength = maxValue - minValue + 1;
  vector < int > * bucket = new vector < int > [bucketLength];

  for (int i = 0; i < bucketLength; i++) {
    bucket[i] = vector < int > ();
  }

  for (int i = 0; i < count; i++) {
    bucket[data[i] - minValue].push_back(data[i]);
  }

  int k = 0;
  for (int i = 0; i < bucketLength; i++) {
    int bucketSize = bucket[i].size();

    if (bucketSize > 0) {
      for (int j = 0; j < bucketSize; j++) {
        data[k] = bucket[i][j];
        k++;
      }
    }
  }

  return data;

}

int main() {

  int arr[] = {8,7,6,5,4,3,2,1};

  display(bucket(arr, 8), 8);

}