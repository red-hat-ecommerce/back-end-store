FROM registry.access.redhat.com/ubi9/openjdk-21-runtime:1.22

ENV LANGUAGE='en_US:en'
ENV TZ='Asia/Jakarta'

COPY --chown=185 target/quarkus-app/lib/ /deployments/lib/
COPY --chown=185 target/quarkus-app/*.jar /deployments/
COPY --chown=185 target/quarkus-app/app/ /deployments/app/
COPY --chown=185 target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185

ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"
ENV GC_CONTAINER_OPTIONS="-XX:+UseShenandoahGC"

ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]