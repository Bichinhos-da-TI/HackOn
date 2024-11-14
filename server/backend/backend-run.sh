
if [ ! -f "./Hackon/mvnw" ]; then
  echo "Maven Wrapper não encontrado. Por favor, certifique-se de que ele está configurado corretamente."
  read -p "Pressione qualquer tecla para sair..."  # Pausa para leitura do erro
  exit 1
fi

cd Hackon

./mvnw clean compile exec:java -Dexec.mainClass=com.hackon.HackonApplication

# Pausa ao final do script para manter o terminal aberto em caso de erro
if [ $? -ne 0 ]; then
  echo "Erro ao executar a aplicação."
  read -p "Pressione qualquer tecla para sair..."  # Aguarda entrada para fechar
fi