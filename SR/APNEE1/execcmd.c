#include <stdio.h>
#include "csapp.h"

int main(int argc, char* argv[]){
	execv(argv[1],argv);
	return 0;
}