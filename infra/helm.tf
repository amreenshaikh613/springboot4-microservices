resource "helm_release" "api-gateway" {
  name      = "api-gateway"
  chart     = "../helm/api-gateway"
  namespace = "default"

  set {
    name  = "image.repository"
    value = "${aws_ecr_repository.gateway.repository_url}"
  }

  set {
    name  = "image.tag"
    value = var.image_tag
  }

  set {
    name  = "containerPort"
    value = 8081
  }
}

resource "helm_release" "product-service" {
  name      = "product-service"
  chart     = "../helm/product-service"
  namespace = "default"

  set {
    name  = "image.repository"
    value = "${aws_ecr_repository.product.repository_url}"
  }

  set {
    name  = "image.tag"
    value = var.image_tag
  }

  set {
    name  = "containerPort"
    value = 8083
  }
}

resource "helm_release" "order-service" {
  name      = "order-service"
  chart     = "../helm/order-service"
  namespace = "default"

  set {
    name  = "image.repository"
    value = "${aws_ecr_repository.order.repository_url}"
  }

  set {
    name  = "image.tag"
    value = var.image_tag
  }

  set {
    name  = "containerPort"
    value = 8082
  }
}

resource "helm_release" "inventory-service" {
  name      = "inventory-service"
  chart     = "../helm/inventory-service"
  namespace = "default"

  set {
    name  = "image.repository"
    value = "${aws_ecr_repository.inventory.repository_url}"
  }

  set {
    name  = "image.tag"
    value = var.image_tag
  }

  set {
    name  = "containerPort"
    value = 8084
  }
}

resource "helm_release" "notification-service" {
  name      = "notification-service"
  chart     = "../helm/notification-service"
  namespace = "default"

  set {
    name  = "image.repository"
    value = "${aws_ecr_repository.notification.repository_url}"
  }

  set {
    name  = "image.tag"
    value = var.image_tag
  }

  set {
    name  = "containerPort"
    value = 8085
  }
}

