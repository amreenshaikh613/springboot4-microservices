#output "cluster_name" {
#  value = module.eks.cluster_name
#}

output "ecr_order_url" {
  value = aws_ecr_repository.order.repository_url
}

output "ecr_gateway_url" {
  value = aws_ecr_repository.gateway.repository_url
}

output "ecr_product_url" {
  value = aws_ecr_repository.product.repository_url
}

output "ecr_inventory_url" {
  value = aws_ecr_repository.inventory.repository_url
}

output "ecr_notification_url" {
  value = aws_ecr_repository.notification.repository_url
}
