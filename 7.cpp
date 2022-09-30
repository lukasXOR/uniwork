#include <iostream>
#include <vector>
#include <string>
#include <cstring>
using namespace std;
/*
this program won't be fully accurate due to 
not accounting for leap years so expect it
to output a number that less then the actual
amount of days you've been alive
*/
class Months {
    public:
    int days[12] = {31,28,31,30,31,30,31,31,30,31,30,31};
    int today[3] = {30,9,2022};
    int totalDays = 0;
    int test(int day, int months,int year) {
        int currentMon = months - 1;
        totalDays += days[currentMon] - day; //push extra days of current month
        for (int i = 0; i < (12 - (currentMon + 1)); i++) { // push days of extra months
            totalDays += days[i + currentMon];
        }
        for (int i = 0; i < today[2] - (year + 1); i++) { // push extra years
            totalDays += 365;
        };
        for (int i = 0; i < today[1] - 2; i++) { // add extra months of current year
            totalDays += days[i];
        }
        totalDays += day; // add extra days
        return totalDays;
    };
};
int main()
{   
    Months mon;
    int usrday;
    int usrmonth;
    int usryear;
    cout << "what day(number) were you born\n";
    cin >> usrday;
    cout << "what month were you born\n";
    cin >> usrmonth;
    cout << "what year were you born\n";
    cin >> usryear;
    cout << "days: " << mon.test(usrday,usrmonth,usryear);
    return 0;
}