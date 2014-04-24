#!/bin/bash

case $# in
2)
java -cp .:../lib/asm-all-3.3.jar:../lib/jdom.jar:../lib/junit-4.8.2.jar:./ch/idsia/agents/controllers/ ch.idsia.scenarios.Main -ag ch.idsia.agents.controllers.${1} -ld ${2}
;;
3)
java -cp .:../lib/asm-all-3.3.jar:../lib/jdom.jar:../lib/junit-4.8.2.jar:./ch/idsia/agents/controllers/ ch.idsia.scenarios.Main -ag ch.idsia.agents.controllers.${1} -ld ${2} -i ${3}
;;
4)
java -cp .:../lib/asm-all-3.3.jar:../lib/jdom.jar:../lib/junit-4.8.2.jar:./ch/idsia/agents/controllers/ ch.idsia.scenarios.Main -ag ch.idsia.agents.controllers.${1} -ld ${2} -i ${3} -le ${4}
;;
5)
java -cp .:../lib/asm-all-3.3.jar:../lib/jdom.jar:../lib/junit-4.8.2.jar:./ch/idsia/agents/controllers/ ch.idsia.scenarios.Main -ag ch.idsia.agents.controllers.${1} -ld ${2} -i ${3} -le ${4} -lg ${5}
;;
6)
java -cp .:../lib/asm-all-3.3.jar:../lib/jdom.jar:../lib/junit-4.8.2.jar:./ch/idsia/agents/controllers/ ch.idsia.scenarios.Main -ag ch.idsia.agents.controllers.${1} -ld ${2} -i ${3} -le ${4} -lg ${5} -ls ${6}
;;
*)
echo "Uso:"
echo ""
echo "./ejecutar nombre_agente nivel_juego <invencible> <enemigos> <pozos> <semilla>"
echo ""
echo ""
echo "- nombre_agente: Nombre del agente en ch.idsia.agents.controllers"
echo ""
echo "- nivel_juego: Nivel de dificultad del juego (0-45)"
echo ""
echo "- <invencible>: Opcional. Puede tomar los valores on/off y sirve para indicar si Mario es invencible o no. En el caso de on, al chocarse con un enemigo no muere y no se le degrada de modo"
echo ""
echo "- <enemigos>: Opcional. Puede tomar los valores: g: para los goombas. gw: para los goombas voladores. rk: para los koopa rojos. gk: para los koopa verdes. s: para spiky. Por ejemplo, con g aparecerían solo goombas, con gw solo goombas voladores, con gwrkgk aparecerian goombas voladores, koopas rojos y koopas verdes, y asi cualquier combinacion"
echo ""
echo "- <pozos>: Opcional. Puede tomar los valores on/off. Define si se quiere que haya pozos en el nivel"
echo ""
echo ""
echo "Ejemplos:"
echo "./ejecutar.sh human.HumanAgentAA2012 0"
echo "  Juega el humano en el nivel 0"
echo "./ejecutar.sh human.HumanAgentAA2012 1 on"
echo "  Juega el humano en el nivel 1 con Mario invencible"
echo "./ejecutar.sh BasicAAAgent 0 off g"
echo "  Juega el agente BasicAAAgent en el nivel 0, con Mario no invencible y con solo goombas"
echo "./ejecutar.sh BasicAAAgent 1 on g off"
echo "  Juega el agente BasicAAAgent en el nivel 1, con Mario invencible, con solo goombas y sin pozos"
echo ""
esac
