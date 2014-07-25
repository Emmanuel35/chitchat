echo '======  Create OSB Application ======='
mvn archetype:generate -DarchetypeGroupId=com.oracle.servicebus.archetype     -DarchetypeArtifactId=oracle-servicebus-application     -DarchetypeVersion=12.1.3-0-0     -DgroupId=fr.sodifrance.anteo.chitchat     -DartifactId=osb12c-application     -Dversion=1.0-SNAPSHOT     -DprojectName=rest-server

echo '======  Create Coherence Application ======='
mvn archetype:generate     -DarchetypeGroupId=com.oracle.coherence     -DarchetypeArtifactId=maven-gar-archetype     -DarchetypeVersion=12.1.3-0-0     -DgroupId=fr.sodifrance.anteo.chitchat     -DartifactId=coherence12c-application     -Dversion=1.0-SNAPSHOT

echo '======  Create SOA Application ======='
mvn archetype:generate      -DarchetypeGroupId=com.oracle.soa.archetype      -DarchetypeArtifactId=oracle-soa-application      -DarchetypeVersion=12.1.3-0-0      -DgroupId=fr.sodifrance.anteo.chitchat      -DartifactId=soa12c-application      -Dversion=1.0-SNAPSHOT     -DprojectName=rest-server

cd soa12c-application

echo '======  Create Client REST Application ======='
mvn archetype:generate     -DarchetypeGroupId=com.oracle.soa.archetype     -DarchetypeArtifactId=oracle-soa-project     -DarchetypeVersion=12.1.3-0-0     -DgroupId=fr.sodifrance.anteo.chitchat.soa12c-application     -DartifactId=client-rest-server     -Dversion=1.0-SNAPSHOT

echo '======  Create IHM REST Application ======='
mvn archetype:generate     -DarchetypeGroupId=com.oracle.soa.archetype     -DarchetypeArtifactId=oracle-soa-project     -DarchetypeVersion=12.1.3-0-0     -DgroupId=fr.sodifrance.anteo.chitchat.soa12c-application     -DartifactId=ihm-rest-server     -Dversion=1.0-SNAPSHOT