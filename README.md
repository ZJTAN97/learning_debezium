# learning_debezium

The below curl command essentially sends a request to the Debezium Connect to create a connector that captures changes from a PostgreSQL database and publishes them to Kafka topics.

```
curl --location 'http://localhost:8083/connectors' \
   --header 'Accept: application/json' \
   --header 'Content-Type: application/json' \
   --data '{
   "name": "cdc-debezium-connector",
   "config": {
       "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
       "database.hostname": "192.168.1.78",
       "database.port": "5432",
       "database.user": "root",
       "database.password": "root",
       "database.dbname": "postgres",
       "database.server.id": "184054",
       "table.include.list": "public.user",
       "topic.prefix": "cdc-topic"
   }
}'
```

Verify Kafka

```
docker exec cdc-kafka opt/bitnami/kafka/bin/kafka-metadata-quorum.sh --bootstrap-server localhost:9092 describe --status

docker exec cdc-kafka opt/bitnami/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 --list

docker exec cdc-kafka opt/bitnami/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic cdc-using-debezium-topic.public.User --from-beginning | jq '.'

```