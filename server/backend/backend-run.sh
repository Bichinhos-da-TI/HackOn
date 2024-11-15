#!/usr/bin/env sh

if [ ! -f "./Hackon/mvnw" ]; then
  echo "Maven Wrapper não encontrado. Por favor, certifique-se de que ele está configurado corretamente."
  exit 1
fi

cd Hackon

./mvnw clean compile exec:java -Dexec.mainClass=com.hackon.HackonApplication
