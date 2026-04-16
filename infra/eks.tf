#module "eks" {
#  source  = "terraform-aws-modules/eks/aws"
#  version = "~> 20.0"

#  cluster_name    = "springboot-microservices"
 # cluster_version = "1.29"

  #create_cloudwatch_log_group = false

  #vpc_id     = "vpc-0bea0372b2f650f96"
  #subnet_ids = ["subnet-0903087d4e0c5a9bc", "subnet-071d9400974632c14", "subnet-087bba7cca270f763", "subnet-0e785fff4607816ea", "subnet-0bb6851cfe48bf5b6", "subnet-0bda1d3508d70113d"]

  #eks_managed_node_groups = {
   # default = {
    #  desired_size = 2
     # max_size     = 3
      #min_size     = 1
      #instance_types = ["t3.medium"]
    #}
  #}
#}
