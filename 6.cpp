#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main()
{
    int birthYr;
    cout << "what year were you born\n";
    cin >> birthYr;
    cout << "you were born in the year: " << 2022 - birthYr;
    return 0;
}