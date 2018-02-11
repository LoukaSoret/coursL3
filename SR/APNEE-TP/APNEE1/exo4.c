#include <stdio.h>
#include "csapp.h"
#define INPUT 1
#define OUTPUT 0


int main(int argc, char* argv[]) {
    pid_t pid;
    int fd[2];

    pipe(fd);
    pid = fork();

    if(pid==0) { //fils
        close(fd[INPUT]); //Fermeture de l'entree
        dup2(fd[OUTPUT], STDIN_FILENO); //Redirection vers l'entree standard
        close(fd[OUTPUT]);
        execlp("wc", "wc", "-l",(char*) NULL);
	
    } else { //pere
        pid=fork();
        if(pid==0) {
            close(fd[OUTPUT]); //Fermeture de la sortie
            dup2(fd[INPUT], STDOUT_FILENO); //Redirection vers l'entree standard
            close(fd[INPUT]);
            execlp("ls","ls","-l",(char*) NULL);
        }
        close(fd[OUTPUT]); 
	close(fd[INPUT]);
	wait(NULL); // on attend la terminaison du fils
    }  
    return 0;
}
