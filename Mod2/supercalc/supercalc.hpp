//
//  supercalc.hpp
//  supercalc
//
//  Created by Vsevolod Molchanov on 08.06.17.
//  Copyright © 2017 Vsevolod Molchanov. All rights reserved.
//

#ifndef supercalc_hpp
#define supercalc_hpp

#include <stdio.h>
#include <functional>
#include <vector>

using namespace std;

template<class T>
class Cell {
private:
    T value;
    string type;
    std::function<T()> function;
public:
    explicit Cell() : value(T()), function([&value = value](){return value;}), type("Ref") {};
    Cell(T val) : value(val), function([value = value](){return value;}), type("Const") {};
    Cell(::function<T()> fun) : value(T()), function(fun), type("Formula") {};
    Cell(const Cell<T> &rhs) {
        type = "Formula";
        function = rhs.function;
    }
    

    Cell<T> &operator=(Cell<T> &&rhs) {
        if(rhs.type == "Formula") {
            type = "Formula";
            function = rhs.function;
        } else if(rhs.type == "Const") {
            type = "Ref";
            value = rhs.value;
            function = [&value = value](){return value;};
        }
        return *this;
    }
    
    
    Cell<T> &operator+=(const Cell<T> &rhs) {
        type = "Formula";
        function = [l = function, p = &rhs](){return l() + p->function();};
        return *this;
    }
    Cell<T> &operator+=(Cell<T> &&rhs) {
        type = "Formula";
        function = [l = function, r = rhs.function](){return l() + r();};
        return *this;
    }
    
    
    Cell<T> &operator-=(const Cell<T> &rhs) {
        type = "Formula";
        function = [l = function, p = &rhs](){return l() - p->function();};
        return *this;
    }
    Cell<T> &operator-=(Cell<T> &&rhs) {
        type = "Formula";
        function = [l = function, r = rhs.function](){return l() - r();};
        return *this;
    }
    
    
    Cell<T> &operator*=(const Cell<T> &rhs) {
        type = "Formula";
        function = [l = function, p = &rhs](){return l() * p->function();};
        return *this;
    }
    Cell<T> &operator*=(Cell<T> &&rhs) {
        type = "Formula";
        function = [l = function, r = rhs.function](){return l() * r();};
        return *this;
    }
    
    
    Cell<T> &operator/=(const Cell<T> &rhs) {
        type = "Formula";
        function = [l = function, p = &rhs](){return l() / p->function();};
        return *this;
    }
    Cell<T> &operator/=(Cell<T> &&rhs) {
        type = "Formula";
        function = [l = function, r = rhs.function](){return l() / r();};
        return *this;
    }
    
    
    Cell<T> operator-() {
        return Cell([r = function](){return -r();});
    }
    
    
    friend Cell<T> operator+(const Cell<T>& lhs, const Cell<T>& rhs) {
        return Cell([l = &lhs, r = &rhs](){return l->function() + r->function();});
    }
    friend Cell<T> operator+(const Cell<T>& lhs, Cell<T>&& rhs) {
        return Cell([l = &lhs, r = rhs.function](){return l->function() + r();});
    }
    friend Cell<T> operator+(Cell<T>&& lhs, const Cell<T>& rhs) {
        return Cell([l = lhs.function, r = &rhs](){return l() + r->function();});
    }
    friend Cell<T> operator+(Cell<T>&& lhs, Cell<T>&& rhs) {
        return Cell([l = lhs.function, r = rhs.function](){return l() + r();});
    }
    
    
    friend Cell<T> operator-(const Cell<T>& lhs, const Cell<T>& rhs) {
        return Cell([l = &lhs, r = &rhs](){return l->function() - r->function();});
    }
    friend Cell<T> operator-(const Cell<T>& lhs, Cell<T>&& rhs) {
        return Cell([l = &lhs, r = rhs.function](){return l->function() - r();});
    }
    friend Cell<T> operator-(Cell<T>&& lhs, const Cell<T>& rhs) {
        return Cell([l = lhs.function, r = &rhs](){return l() - r->function();});
    }
    friend Cell<T> operator-(Cell<T>&& lhs, Cell<T>&& rhs) {
        return Cell([l = lhs.function, r = rhs.function](){return l() - r();});
    }
    
    
    friend Cell<T> operator*(const Cell<T>& lhs, const Cell<T>& rhs) {
        return Cell([l = &lhs, r = &rhs](){return l->function() * r->function();});
    }
    friend Cell<T> operator*(const Cell<T>& lhs, Cell<T>&& rhs) {
        return Cell([l = &lhs, r = rhs.function](){return l->function() * r();});
    }
    friend Cell<T> operator*(Cell<T>&& lhs, const Cell<T>& rhs) {
        return Cell([l = lhs.function, r = &rhs](){return l() * r->function();});
    }
    friend Cell<T> operator*(Cell<T>&& lhs, Cell<T>&& rhs) {
        return Cell([l = lhs.function, r = rhs.function](){return l() * r();});
    }
    
    
    friend Cell<T> operator/(const Cell<T>& lhs, const Cell<T>& rhs) {
        return Cell([l = &lhs, r = &rhs](){return l->function() / r->function();});
    }
    friend Cell<T> operator/(const Cell<T>& lhs, Cell<T>&& rhs) {
        return Cell([l = &lhs, r = rhs.function](){return l->function() / r();});
    }
    friend Cell<T> operator/(Cell<T>&& lhs, const Cell<T>& rhs) {
        return Cell([l = lhs.function, r = &rhs](){return l() / r->function();});
    }
    friend Cell<T> operator/(Cell<T>&& lhs, Cell<T>&& rhs) {
        return Cell([l = lhs.function, r = rhs.function](){return l() / r();});
    }
    
    
    explicit operator T() const {
        return function();
    }
};

template <class T>
class SuperCalc {
private:
    vector<vector<Cell<T>>> table;
public:
    SuperCalc(int m, int n) {
        for (int i = 0; i < m; i++) {
            table.emplace_back(n);
            for (int j = 0; j < n; j++) {
                table[i].emplace_back();
            }
        }
        for(int i = 0; i < m; i++)
            for(int k = 0; k < n; k++)
                table[i][k] = T();
    }
    
    Cell<T>& operator() (int i, int j) {
        return table[i][j];
    }
};

#endif /* supercalc_hpp */
