#include <stdio.h>
#include "csapp.h"

int main(int argc, char* argv[]){
	execv(argv[1], argv); // on execute la commande 
	//alternative : system(argv[1]);
	return 0;
}
