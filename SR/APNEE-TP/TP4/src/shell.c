/*
 * Copyright (C) 2002, Simon Nieuviarts
 */

// Copyleft (C) 2018, Louka Soret

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <errno.h>
#include "readcmd.h"

#define INPUT 1
#define OUTPUT 0
#define MAXPIPES 20

int len (char*** seq){
	int i;
	for(i = 0;seq[i]!=NULL;i++){
		if(i >= MAXPIPES){
			return -1;
		}
	}
	return i;
}

int main()
{
	pid_t pid;
	int status;
	int in = -1;
	int out = -1;
	int pipes[MAXPIPES][2];

	while (1) {

		struct cmdline *l;
		int i, j;

		printf("shell> ");
		l = readcmd();

		/* If input stream closed, normal termination */
		if (!l) {
			printf("exit\n");
			exit(0);
		}

		if (l->err) {
			/* Syntax error, read another command */
			printf("error: %s\n", l->err);
			continue;
		}

		if (l->in) printf("in: %s\n", l->in);
		if (l->out) printf("out: %s\n", l->out);

		/* Display each command of the pipe */
		for (i=0; l->seq[i]!=0; i++) {
			char **cmd = l->seq[i];
			printf("seq[%d]: ", i);
			for (j=0; cmd[j]!=0; j++) {
				printf("%s ", cmd[j]);
			}
			printf("\n");
		}

		if(l->seq[0]){
			if (!strcmp(l->seq[0][0],"quit") || !strcmp(l->seq[0][0],"q")){
				exit(0);
			}
			else{
				
				if(len(l->seq)==-1){
						fprintf(stderr, "error: Oh boy that's a lot of pipes you have right there.\n");
						exit(2);
				}

				/* Opening all the pipes */
				for(i = 0; i < len(l->seq)-1; i++){
					if(pipe(pipes[i]) < -1){
						fprintf(stderr, "error: Can't open pipe number %d.\n",i);
						exit(2);
					}
				}

				/* piped cmds execution */
				for (i = 0; i < len(l->seq); i++){
					pid = fork();
					if (!pid){ // Child
						if (i != len(l->seq)){ // right pipe pluging
							dup2(pipes[i][INPUT], STDOUT_FILENO);
						} else if (l->out){ // output redirection case when i=len(l->seq)-1
							out=open(l->out,O_RDWR | O_CREAT | O_TRUNC, S_IRUSR | S_IWUSR | S_IXUSR | S_IRGRP | S_IWGRP | S_IXGRP | S_IROTH | S_IWOTH | S_IXOTH);
							if(in == -1){
								fprintf(stderr, "error: Can't open output redirection file %s\n", l->out);
								exit(2);
							}
							dup2(out, STDOUT_FILENO);
						}
						if (i){ // left pipe pluging
							dup2(pipes[i-1][OUTPUT], STDIN_FILENO);
						} else if (l->in){ // input redirection case when i=0
							in=open(l->in,O_RDWR | O_CREAT | O_TRUNC, S_IRUSR | S_IWUSR | S_IXUSR | S_IRGRP | S_IWGRP | S_IXGRP | S_IROTH | S_IWOTH | S_IXOTH);
							if(in == -1){
								fprintf(stderr, "error: Can't open input redirection file %s\n", l->out);
								exit(2);
							}
							dup2(in, STDIN_FILENO);
						}
						/* Closing all pipes */
						for (j = 0; j < len(l->seq); j++) {
							close(pipes[j][0]);
							close(pipes[j][1]);
						}
						execvp(l->seq[i][0], l->seq[i]);
						exit(errno);
					} else { //father
						close(pipes[i][1]);
						if(!l->job){ // forground or background test
							for (j = 0; j < len(l->seq); j++){ // waiting all sons to come back
								waitpid(-1, &status , 0);
								if (WIFEXITED(status)) { //Son terminated successfully
									if (strcmp(strerror(WEXITSTATUS(status)),"Success")){
										printf("%s\n",strerror(WEXITSTATUS(status)));
									}
								} else if ( WIFSIGNALED(status)){ // Son terminated abnormally
									psignal(WTERMSIG(status),"terminated abnormally");
								}
							}
						}
					}
				}
				//exit(errno);
			}
		}
	}
}
