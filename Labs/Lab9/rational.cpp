//
//  rational.cpp
//  main
//
//  Created by Vsevolod Molnumanov on 25.04.17.
//  Copyright © 2017 Vsevolod Molnumanov. All rights reserved.
//

#include <iostream>
#include <cmath>
#include "rational.hpp"

using namespace std;

int Rational::nod(int x, int y) {
    if(x == 0 || y == 0) {
        return 1;
    }
    while(x != y) {
        if(x > y) {
            x = x - y;
        } else {
            y = y - x;
        }
    }
    return x;
}

Rational::Rational(int x, int y) {
    if(!y || !x) {
        num = 0;
        denom = 1;
    }
    num = x;
    denom = y;
    this->norm();
}

void Rational::norm() {
    int sign = 1;
    if(num * denom < 0) {
        sign = -1;
    }
    
    num = abs(num);
    denom = abs(denom);
    
    int b = nod(num, denom);
    if(num == 0 || denom == 0) {
        denom = 1;
    }
    if(b == 1) {
        return;
    }
    
    num = num / b * sign;
    denom = denom / b;
}

Rational& Rational::operator= (const Rational& rvalue) {
    if (this != &rvalue) {
        this->num = rvalue.num;
        this->denom = rvalue.denom;
    }
    return *this;
}

Rational Rational::operator* (const Rational& rvalue) const {
    Rational temp;
    temp.num = this->num * rvalue.num;
    temp.denom=this->denom * rvalue.denom;
    temp.norm();
    return temp;
}

Rational Rational::operator+ (const Rational& rvalue) const {
    Rational temp;
    temp.num = rvalue.denom * this->num + rvalue.num * this->denom;
    temp.denom = this->denom * rvalue.denom;
    temp.norm();
    return temp;
}

Rational Rational::operator- (const Rational& rvalue) const {
    Rational temp;
    temp.num = rvalue.denom * this->num - rvalue.num * this->denom;
    temp.denom = this->denom * rvalue.denom;
    temp.norm();
    return temp;
}

Rational Rational::operator/ (const Rational& rvalue) const {
    Rational temp;
    temp.num = this->num * rvalue.denom;
    temp.denom = this->denom * rvalue.num;
    temp.norm();
    return temp;
}

istream &operator>> (istream &is, Rational &rvalue) {
    is >> rvalue.num;
    is.ignore();
    is >> rvalue.denom;
    return is;
}

ostream &operator<< (ostream &os, const Rational &rvalue) {
    if(rvalue.num == 0) {
        os << 0;
    } else if(rvalue.num == rvalue.denom) {
        os << 1;
    } else if(rvalue.denom == 1) {
        os << rvalue.num;
    } else {
        os << rvalue.num << "/" << rvalue.denom;
    }
    return os;
}
