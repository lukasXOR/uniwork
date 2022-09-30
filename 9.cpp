#include <iostream>
#include <vector>
#include <string>
#include <cstring>
using namespace std;
int main()
{   
    float savings;
    cout << "savings: \n";
    cin >> savings;
    cout << "value after interest for 1 year " << savings * 1.01;
    return 0;
}