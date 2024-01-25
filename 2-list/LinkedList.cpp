// C++ LinkedList implementation


#include <stdexcept>
#include <iomanip>
#include <iostream>
#include <string>

template <class T>
class LinkedList {
private:
    struct Node {
        T data;
        Node* next;
    };

    Node* head;
    int size;

public:
    LinkedList() {
        head = nullptr;
        size = 0;
    }

    void push_back(T data) {
        Node* newNode = new Node;
        newNode->data = data;
        newNode->next = nullptr;
        if (head == nullptr) {
            head = newNode;
        } else {
            Node* curr = head;
            while (curr->next != nullptr) {
                curr = curr->next;
            }
            curr->next = newNode;
        }
    }

    void pop_back() {
        if (head == nullptr) {
            throw std::out_of_range("Index out of range");
        }
        if (head->next == nullptr) {
            delete head;
            head = nullptr;
        } else {
            Node* curr = head;
            while (curr->next->next != nullptr) {
                curr = curr->next;
            }
            delete curr->next;
            curr->next = nullptr;
        }
    }

    void printMemoryMap() {
        // format output in two columns: variable name and address
        std::cout << std::left;
        std::cout << std::setw(20) << "Variable name" << std::setw(20) << "Address" << std::endl;
        std::cout << std::setw(20) << "-------------" << std::setw(20) << "-------" << std::endl;
        std::cout << std::setw(20) << "this" << (long)this << std::endl;
        std::cout << std::setw(20) << "size" << (long)&size << std::endl;
        std::cout << std::setw(20) << "head" << (long)head << std::endl;

        std::cout << "Nodes:" << std::endl;
        std::cout << std::setw(5) << "i" << std::setw(20) << "Address of node" << std::setw(20) << "Address of node->data" << std::setw(20) << "Contents of node->next" << std::endl;
        Node* curr = head;
        int i = 0;
        while (curr != nullptr) {
            std::cout << std::setw(5) << i << std::setw(20) << (long)curr << std::setw(20) << (long)&(curr->data) << std::setw(20) << (long)curr->next << std::endl;
            curr = curr->next;
            i++;
        }
    }

};

int main() {
    LinkedList<int> list;
    for(int i = 0; i < 6; i++) list.push_back(i);
    list.pop_back();
    list.printMemoryMap();
    return 0;
}