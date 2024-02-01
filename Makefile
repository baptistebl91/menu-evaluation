# Répertoire de sortie
SRC_DIR = src/fr/iutfbleau/sae31_2023
# Répertoire contenant les sources
OUT_DIR = build
# Obtention d'une liste des fichiers .java à compiler.
SRCS := $(wildcard $(SRC_DIR)/*.java)
# Création d'une liste des fichiers .class correspondant aux fichiers .java
CLS := $(SRCS:$(SRC_DIR)/%.java=$(OUT_DIR)/%.class)
.SUFFIXES: .java
# Ajout des règles qui ne créent pas de fichiers portant le même nom qu'elles.
.PHONY: build clean run-stats run-tests
# Règle build qui appelle des règles de compilation pour chaque classe du projet.
build: $(CLS)
# Règle de compilation pour chaque fichier .java en fichier .class.
$(CLS): $(OUT_DIR)/%.class: $(SRC_DIR)/%.java
	javac -encoding utf8 -implicit:none -d $(OUT_DIR)/ -classpath $(SRC_DIR)/ $<
# Règle run qui construit le programme et exécute la classe src.Main.
run-stats: build
	java -classpath $(OUT_DIR):"./lib/mariadb-java-client-3.2.0.jar" MainStats

run-tests: build
	java -classpath $(OUT_DIR):"./lib/mariadb-java-client-3.2.0.jar" MainTests

jar-tests: build
	jar cvfe tests.jar build.MainTests -C build .

jar-stats: build
	jar cvfe stats.jar build.MainStats -C build .

# Règle clean qui supprime l'ensemble des fichiers .class.
clean:
	$(RM) -r $(OUT_DIR)