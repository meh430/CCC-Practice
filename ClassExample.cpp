#include <iostream>

Class ClassExample {
    private:
        int x;
        int y;

    public:
        ClassExample() {
            std::cout << Created Object << std::endl;
        }

        ~ClassExample() {
            std::cout << Destroyed Object << std::endl;
        }
};

int main() {
    ClassExample object;
}