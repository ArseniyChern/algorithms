#include <iostream>

using namespace std;

/**
	Swap 2 numbers without a temporary variable
*/

int main() {
	int x = 1;
	int y = 2;

	x = x+y;
	y = x-y;
	x = x-y;

	printf("x = %d, y = %d",x,y);
}

