#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main() {
    pid_t pid;
    int tube[2];
    long entier;

    if (pipe(tube) == -1) {
        fprintf(stderr, "Erreur de création du tube\n");
        exit(1);
    }
    pid = fork();
    switch (pid) {
      case -1:
        fprintf(stderr, "Erreur de fork\n");
        exit(2);
      case 0:
        close(tube[1]);
        read(tube[0], &entier, sizeof(entier));
        printf("Je suis le fils, j'ai lu l'entier %ld dans le tube\n", entier);
        close(tube[0]);
        break;
      default:
        close(tube[0]);
        srandom(pid);
        entier = random();
        printf("Je suis le père, j'envoie l'entier %ld à mon fils\n", entier);
        write(tube[1], &entier, sizeof(entier));
        close(tube[1]);
    }
    return 0;
}

