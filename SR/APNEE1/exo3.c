#include <stdio.h>
#include "csapp.h"

int Compteur = 0;

void handler1(int sig) // handler
{
    pid_t pid;
    while ((pid = waitpid(-1, NULL, WNOHANG)) > 0){ // si un fils s'st affictivement terminé, on entre dans cette boucle
    	Compteur++; // on incremente le nombre de fils secourus
        printf("Handler reaped child %d\n", (int)pid); // on affiche le pid du fils secouru
    }
    Sleep(2); // on fait attendre le processus quelques secondes pour provoquer le probleme lié au nombre limité de signaux "pending"
    return;
}

int main(int argc, char* argv[])
{
    int i, pid;
    int n = atoi(argv[1]); // on recupere le nombre de fils a creer
	int preuve_activite = 0;

    Signal(SIGCHLD, handler1); // redefinition du handler

    for (i = 0; i < n; i++) { // creation des fils
    	pid = Fork();
        if (pid == 0) {
            printf("Fils créé : %d\n", (int)getpid()); // affichage des pid des fils
            Sleep(i+1);
            exit(0); // arret du fils (envoie de SIGCHILD au pere)
        }

    }
	
    printf("Parent entering loop\n");   
    while (Compteur < n ) { // le pere execute une action en attendant la terminaison des fils
        preuve_activite++;
        //printf("Le pere est bien en cours d'execution : %d",preuve_activite);
    }
 	printf("Tout les enfants ont été secourus\n ");
    exit(0);
}
/* $end signal1 */
