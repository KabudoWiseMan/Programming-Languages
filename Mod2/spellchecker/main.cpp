//
//  main.cpp
//  spellchecker
//
//  Created by Vsevolod Molchanov on 08.06.17.
//  Copyright © 2017 Vsevolod Molchanov. All rights reserved.
//


#include <iostream>
#include <fstream>
#include <string>
#include <map>
#include <set>
#include <algorithm>

using namespace std;

map<string, pair<set<string>, int>> dictionary;

set<string> makeBigram(string word) {
    
    set<string> bigrams;
    if(word.length() <= 2) {
        bigrams.insert(word);
        return bigrams;
    }
    
    for(int i = 0; i <= word.length() - 2; i++) {
        bigrams.insert(word.substr(i, 2));
    }
    
    return bigrams;
}

double measureOfSimilarity(set<string> &a, set<string> &b) {
    set<string> inter;
    set_intersection(a.begin(), a.end(), b.begin(), b.end(), inserter(inter, inter.begin()));
    return (double)inter.size() / (a.size() + b.size() - inter.size());
}

string correct(string &word) {
    string answer = word;
    double similarity = -1;
    int frequency = 0;
    set<string> words = makeBigram(word);
    for(auto w : dictionary) {
        double freq = measureOfSimilarity(words, w.second.first);
        if(freq > similarity || (freq == similarity && w.second.second > frequency) ||
           (freq == similarity && w.second.second == frequency && w.first < answer)) {
            similarity = freq;
            answer = w.first;
            frequency = w.second.second;
        }
    }
    return answer;
}

int main(int argc, const char * argv[]) {
    
    ifstream file("/Users/vsevolodmolchanov/Documents/Programming/C++/Mod_2/spellchecker/spellchecker/spellchecker/count_big.txt");
    while(file) {
        string word;
        int frequency;
        file >> word >> frequency;
        dictionary.insert(pair<string, pair<set<string>, int>>
                          (word, pair<set<string>, int>(makeBigram(word), frequency)));
    }
    file.close();
    
    string word;
    while(cin >> word){
        cout << correct(word) << endl;
    }

    return 0;
}
