resource "kubernetes_config_map" "common_config" {
  metadata {
    name      = "common-config"
    namespace = "default"
  }

data = {
  ORDER_SERVICE_URL     = "http://order-service.default.svc.cluster.local:8082"
  PRODUCT_SERVICE_URL   = "http://product-service.default.svc.cluster.local:8081"
  INVENTORY_SERVICE_URL = "http://inventory-service.default.svc.cluster.local:8083"

  LOKI_URL = "http://loki.default.svc.cluster.local:3100/loki/api/v1/push"

  MANAGEMENT_ZIPKIN_TRACING_ENDPOINT = "http://tempo.default.svc.cluster.local:9411"

  SPRING_KAFKA_BOOTSTRAP_SERVERS = "broker.default.svc.cluster.local:29092"

  SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER_URI = "http://keycloak.default.svc.cluster.local:8080/realms/spring-microservices-security-realm"

  # MongoDB
  SPRING_MONGODB_URI                     = "mongodb://root:password@mongodb:27017/product_service?authSource=admin"
  SPRING_MONGODB_HOST                    = "mongodb"
  SPRING_MONGODB_PORT                    = "27017"
  SPRING_MONGODB_DATABASE                = "product_service"
  SPRING_MONGODB_USERNAME                = "root"
  SPRING_MONGODB_PASSWORD                = "password"
  SPRING_MONGODB_AUTHENTICATION_DATABASE = "admin"

  # MySQL
  SPRING_DATASOURCE_URL      = "jdbc:mysql://mysql.default.svc.cluster.local:3306/order_service"
  SPRING_DATASOURCE_USERNAME = "amreen"
  SPRING_DATASOURCE_PASSWORD = "mysql"
}
}
