//
//  main.cpp
//  javafinder
//
//  Created by Vsevolod Molchanov on 30.05.17.
//  Copyright © 2017 Vsevolod Molchanov. All rights reserved.
//

#include <iostream>
#include <dirent.h>
#include <sys/stat.h>
#include <fstream>

using namespace std;

string intToHex(int a) {
    
    string tmp = "";
    
    while (a) {
        int r = a % 16;
        if (r > 9) {
            r += (int)'A' - 10;
        }
        else {
            r += (int)'0';
        }
        tmp = (char)r + tmp;
        a /= 16;
    }
    
    return tmp;
}

int main(int argc, const char * argv[]) {

    if (argc != 2) {
        cerr << "directory not specified" << endl;
        return 0;
    }
    
    DIR *catalog = opendir(argv[1]);
    if (!catalog) {
        cerr << "directory " << catalog << " must exist" << endl;
        return 0;
    }
    
    struct dirent *counter;
    while ((counter = readdir(catalog))) {
        string path = argv[1];
        path += '/';
        path += counter->d_name;
        struct stat buf;
        lstat(path.c_str(), &buf);
        if (S_ISREG(buf.st_mode)) {
            if (path.substr(path.length() - 5, 5) == ".java") {
                ifstream f(path);
                ofstream n(path.substr(0, path.length() - 5) + "_new.java");
                int ch, c;
                string line = "", tmp;
                while ((ch = f.get()) != EOF) {
                    tmp = "";
                    if (ch == 208) {
                        c = f.get() - 128;
                        if (c == 1) {
                            tmp = "\\u0401";
                        } else {
                            tmp = "\\u04" + intToHex(c);
                        }
                    } else if (ch == 209) {
                        c = f.get() - 64;
                        tmp = "\\u04" + intToHex(c);
                    } else if (ch == 10) {
                        n << line << endl;
                        line = "";
                    } else {
                        tmp = (char)ch;
                    }
                    line += tmp;
                }

                f.close();
                n.close();
            }
        }
    }
    
    return 0;
}
