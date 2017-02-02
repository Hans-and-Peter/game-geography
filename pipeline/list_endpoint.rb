#!/usr/bin/env ruby
require_relative 'lib/shell.rb'

if ARGV.length == 0
  raise "need to pass service_name"
end
service_name = ARGV[0]

# wait for task to become active
wait_service_cmd = "aws ecs wait services-stable --services \"#{service_name}\""
shell(wait_service_cmd)

list_task_cmd = "aws ecs list-tasks --service-name \"#{service_name}\" --output text --query 'taskArns'"
task_id = shell(list_task_cmd)

wait_task_cmd = "aws ecs wait tasks-running --tasks \"#{task_id}\""
shell(wait_task_cmd)

# get container instance of task
desc_task_cmd = "aws ecs describe-tasks --tasks \"#{task_id}\" " +
    "--output text --query 'tasks[*].containerInstanceArn'"
task_container_id = shell(desc_task_cmd)

export task_instance_id=$( aws ecs describe-container-instances --container-instances "$task_container_id" \
--output text --query 'containerInstances[*].ec2InstanceId' )
# get public ip of container instance
export public_host=$( aws ec2 describe-instances --instance-id $task_instance_id \
--output text --query 'Reservations[*].Instances[*].NetworkInterfaces[*].Association.PublicDnsName' )


# # wait for task to become active
# aws ecs wait services-stable --services "$serviceName"
# export task_id=$( aws ecs list-tasks --service-name "$serviceName" --output text --query 'taskArns' )
# echo $task_id
# aws ecs wait tasks-running --tasks "$task_id"
# # get container instance of task
# export task_container_id=$( aws ecs describe-tasks --tasks "$task_id" \
# --output text --query 'tasks[*].containerInstanceArn' )
# export task_instance_id=$( aws ecs describe-container-instances --container-instances "$task_container_id" \
# --output text --query 'containerInstances[*].ec2InstanceId' )
# # get public ip of container instance
# export public_host=$( aws ec2 describe-instances --instance-id $task_instance_id \
# --output text --query 'Reservations[*].Instances[*].NetworkInterfaces[*].Association.PublicDnsName' )
