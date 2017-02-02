#!/usr/bin/env ruby
require_relative 'lib/shell.rb'

# TODO aws: give proper name to instance

# create new Docker container instance to have enough free instances
# ami-e012d48f ... ECS base image for micro, see http://docs.aws.amazon.com/AmazonECS/latest/developerguide/launch_container_instance.html
create_instance_cmd = 'aws ec2 run-instances ' +
    '--count 1 --image-id ami-e012d48f --instance-type t2.micro --security-group-ids sg-e63e338e ' +
    '--subnet-id subnet-43f2632b --iam-instance-profile Arn="arn:aws:iam::081374885386:instance-profile/ecsInstanceRole" ' +
    '--associate-public-ip-address ' +
    "--output text --query 'Instances[*].InstanceId'"
instance_id = shell(create_instance_cmd)

wait_cmd = "aws ec2 wait instance-status-ok --instance-ids \"#{instance_id}\""
shell(wait_cmd)

puts instance_id

# export instance_id=$(aws ec2 run-instances --count 1 --image-id ami-e012d48f --instance-type t2.micro --security-group-ids sg-e63e338e \
# --subnet-id subnet-43f2632b --iam-instance-profile Arn="arn:aws:iam::081374885386:instance-profile/ecsInstanceRole" \
# --associate-public-ip-address \
# --output text --query 'Instances[*].InstanceId')
#
# aws ec2 wait instance-status-ok --instance-ids "$instance_id"
