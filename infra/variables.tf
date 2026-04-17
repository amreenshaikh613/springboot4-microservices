variable "region" {
  default = "us-west-2"
}

variable "cluster_name" {
  default = "springboot-microservices"
}


variable "image_tag" {
  description = "Docker image tag"
  type        = string
}
