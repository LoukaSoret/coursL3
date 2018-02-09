java EssaiPipo CRexemple.txt $1 | tr '[:space:]' '[\n*]' | grep -v "^\s*$" | sort | uniq -c | sort -bnr > log.out

cat CRexemple.txt | tr '.' '. *' | tr '[:space:]' '[\n*]' | grep -v "^\s*$" | sort | uniq -c | sort -bnr > obj.out

sed -i 's/^ *//' log.out
tr ' ' '-' <log.out >slog.out
sort -nrk1 slog.out > log.out 

sed -i 's/^ *//' obj.out
tr ' ' '-' <obj.out >sobj.out
sort -nrk1 sobj.out > obj.out

rm sobj.out slog.out

echo "-----------------{OBJECIF}-----------------"
head -15 obj.out
echo "-----------------{RESULTAT}----------------"
head -15 log.out