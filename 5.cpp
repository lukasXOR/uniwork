#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main()
{
    float firstNum;
    float secondNum;
    cout << "first number\n";
    cin >> firstNum;
    cout << "second number\n";
    cin >> secondNum;
    cout << "original order: \n" << firstNum << "\n" << secondNum << "\n";
    cout << "swapped order: \n" << secondNum << "\n" << firstNum ;
}