// C++ Array List implementation

#include <stdexcept>
#include <iomanip>
#include <iostream>
#include <string>

template <class T>
class ArrayList {
private:
    T* arr;
    int capacity;
    int size;

public:
    ArrayList(int capacity=10) {
        this->capacity = capacity;
        this->size = 0;
        this->arr = new T[capacity];
    }

    void push_back(T data) {
        if (size == capacity) {
            capacity *= 2;
            T* newArr = new T[capacity];
            for (int i = 0; i < size; i++) {
                newArr[i] = arr[i];
            }
            delete[] arr;
            arr = newArr;
        }
        arr[size++] = data;
    }

    void pop_back() {
        if (size == 0) {
            throw std::out_of_range("Index out of range");
        }
        size--;
    }

    T& operator[](int index) {
        if (index < 0 || index >= size) {
            throw std::out_of_range("Index out of range");
        }
        return arr[index];
    }

    void printMemoryMap() {
        // format output in two columns: variable name and address
        std::cout << std::left;
        std::cout << std::setw(20) << "Variable name" << std::setw(20) << "Address" << std::endl;
        std::cout << std::setw(20) << "-------------" << std::setw(20) << "-------" << std::endl;
        std::cout << std::setw(20) << "this" << (long)this << std::endl;
        std::cout << std::setw(20) << "capacity" << (long)&capacity << std::endl;
        std::cout << std::setw(20) << "size" << (long)&size << std::endl;
        std::cout << std::setw(20) << "arr" << (long)arr << std::endl;
        for (int i = 0; i < size; i++) {
            std::cout << std::setw(20) << "arr[" + std::to_string(i) + "]" << (long)&arr[i] << std::endl;
        }
    }
};

int main() {
    ArrayList<int> list;
    list.push_back(1);
    list.push_back(2);
    list.push_back(3);
    list.pop_back();
    std::cout << list[0] << std::endl;
    std::cout << list[1] << std::endl;
    list.printMemoryMap();

    for (int i = 0; i < 20; i++) list.push_back(i);
    list.printMemoryMap();
    return 0;
}