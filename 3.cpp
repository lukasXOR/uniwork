#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main()
{
    int rectLen;
    int rectWid;
    cout << "what is the length of your rectangle\n";
    cin >> rectLen;
    cout << "what is the width of your rectangle\n";
    cin >> rectWid;
    cout << "perimeter: " << rectLen + rectWid << "\narea: " << rectLen * rectWid;
    return 0;
}