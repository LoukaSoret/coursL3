/*
 * Copyright (C) 2002, Simon Nieuviarts
 */

#ifndef __READCMD_H
#define __READCMD_H

/* Read a command line from input stream. Return null when input closed.
Display an error and call exit() in case of memory exhaustion. */
struct cmdline *readcmd(void);


/* Structure returned by readcmd() */
struct cmdline {
	char *err;	/* If not null, it is an error message that should be
			   displayed. The other fields are null. */
	char *in;	/* If not null : name of file for input redirection. */
	char *out;	/* If not null : name of file for output redirection. */
	char ***seq;	/* See comment below */
	int job; /* set to 1 if cmdline is a job */ 
};

/* Field seq of struct cmdline :
A command line is a sequence of commands whose output is linked to the input
of the next command by a pipe. To describe such a structure :
A command is an array of strings (char **), whose last item is a null pointer.
A sequence is an array of commands (char ***), whose last item is a null
pointer.
When a struct cmdline is returned by readcmd(), seq[0] is never null.
*/
#endif
