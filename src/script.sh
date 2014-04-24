#!/bin/bash
AGENTS="$@"
for a in $AGENTS; do
	for i in {1..5}; do
		for j in {1..2}; do
			number=$(($RANDOM%20))
			echo "Agente:" ${a} "- Nivel:" $i "- Ejecucion:" $j "- Semilla:" $number
			echo "Agente:" ${a} "- Nivel:" $i "- Ejecucion:" $j "- Semilla:" $number >>  "./instancias/"${a}"_"$i"_"$j"_"$number".txt"
			echo "./instancias/"${a}"_"$i"_"$j"_"$number".txt"
			./ejecutar.sh ${a} $i off g on $number >>  "./instancias/"${a}"_"$i"_"$j"_"$number".txt"
		done
	done
done
