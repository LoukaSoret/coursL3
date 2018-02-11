#include <stdio.h>
#include "csapp.h"

int main(int argc, const char *argv[]) {
	int pid = 0,i,j,retour;
    int M = atoi(argv[2]); // On recupere les arguments
    int N = atoi(argv[1]);  

	printf("Programme original pid: %d\n", getpid()); // programme "Tige".
	for(i = 1; i <= N ; i++){ // on créé un nombre N de "tiges secondaires", fils de la Tige.
		pid = Fork();
		if(pid == 0){ // child : on affiche le pid, le ppid et on recréé M enfants.
			printf("Fils %d pid : %d, ppid: %d\n", i, getpid(), getppid());
			for(j = 1; j <= M; j++){ // création de M enfants.
				pid = Fork();
				if(pid == 0){ // child : on affiche le pid et le ppid.
					printf("Petit fils %d de %d pid : %d, ppid: %d\n",j, i, getpid(), getppid());
					exit(0); // on tue le fils
				}
				else // parent : on attend que le fils soit terminé.
					waitpid(pid, &retour ,0);
			}
			exit(0); // on tue le processus fils pour qu'il ne boucle pas à son tour.
		}
		else // parent : on attend la terminaison du fils.
			waitpid(pid, &retour ,0);
	}
	exit(0); // on termine.
}
