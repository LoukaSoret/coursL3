#!/bin/bash

mkdir tests
mkdir reponses

for file in `seq 30`
do 
	head -n $(($file * 50)) LesAchats > tests/LesAchats$(($file*50))
done

for file in `ls tests/`
do
	echo $file
	java EssaiJoin tests/$file LesVins reponses/nested-$file reponses/hashed-$file;
done;
