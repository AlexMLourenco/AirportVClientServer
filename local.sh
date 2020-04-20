bold=$(tput bold)
normal=$(tput sgr0)

echo "${bold}*** Script Local ***${normal}"

###

echo -e "\n${bold}* Compilação do código  *${normal}"

echo -e "\n${bold}->${normal} A compilar a aplicaçao"
cd src/
javac $(find . -name '*.java')
cd ..

###

echo -e "\n${bold}* Execução do código em cada nó *${normal}"

echo -e "\n${bold}->${normal} A executar Repository"
cd src/
java -cp $(pwd) mainProject/RepositoryMain &
cd ..

echo -e "\n${bold}->${normal} A executar Shared Regions"
cd src/
java -cp $(pwd) mainProject/SharedRegionMain ArrivalTerminalExit &
cd ..

wait
sleep 60
echo -e "\n${bold}->${normal} A execução terminou"

